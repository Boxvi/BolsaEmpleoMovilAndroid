package ec.edu.insta.movilgc1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.model.usuario.ModeloUsuario;
import ec.edu.insta.movilgc1.model.usuario.Usuario;
import ec.edu.insta.movilgc1.ui.admin.InicioAdminBusqueda;
import ec.edu.insta.movilgc1.ui.employe.InicioBuscoEmpleo;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

@Data
public class LoginGeneral extends AppCompatActivity {
    private EditText txtUsuario, txtContrasena;
    private Button btnIngresar, btnRegistrarse;
    private ImageButton btn_regresar_main;
    private TextView txt_busco_empleo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_general);

        Bundle bundle = getIntent().getExtras();
        txt_busco_empleo = findViewById(R.id.txt_busco_empleo);
        txt_busco_empleo.setText(bundle.getString("tipo"));

        txtUsuario = findViewById(R.id.txt_username);
        txtContrasena = findViewById(R.id.txt_password);

        btnIngresar = findViewById(R.id.btn_login);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btn_regresar_main = findViewById(R.id.btn_regresar_main);

        btn_regresar_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginGeneral.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (txt_busco_empleo.getText().equals("ERES ADMIN")) {
            btnRegistrarse.setVisibility(View.GONE);
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEmpleadoPost(txtUsuario.getText().toString(), txtContrasena.getText().toString());
            }
        });


        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginGeneral.this, RegistroEmpleado.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void LoginEmpleadoPost(String usuario, String contrasena) {
        System.out.println(usuario + " " + contrasena);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/auth/login";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", usuario);
            jsonObject.put("password", contrasena);
            System.out.println(jsonObject.toString());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        ModeloUsuario modeloUsuario = new ModeloUsuario();
                        Usuario usuario1 = modeloUsuario.buscarPorUsername(LoginGeneral.this, usuario);
                        Intent intent;
                        switch (response.getJSONArray("authorities").getJSONObject(0).getString("authority")) {


                            case "ROLE_ADMINISTRADOR":

                                if (usuario1.isEstado() == true) {
                                    intent = new Intent(LoginGeneral.this, InicioAdminBusqueda.class);
                                    Bundle budle = new Bundle();
                                    budle.putString("id_usuario", usuario1.getId().toString());

                                    //Bundle budle1 = new Bundle();
                                    intent.putExtras(budle);
                                    startActivity(intent);
                                    Toast.makeText(LoginGeneral.this, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginGeneral.this, "SU ROL DE ADMINISTRADOR FUE DESABILIDATO", Toast.LENGTH_SHORT).show();
                                }

                                break;

                            case "ROLE_ESTUDIANTE":
                                if (usuario1.isEstado() == true) {

                                    Intent intent2 = new Intent(LoginGeneral.this, InicioBuscoEmpleo.class);
                                    Bundle bundle = new Bundle();

                                    bundle.putString("username_id", usuario1.getId().toString());
                                    bundle.putString("username_estudiante", usuario1.getUsername());


                                    intent2.putExtras(bundle);

                                    startActivity(intent2);

                                    finish();
                                    Toast.makeText(LoginGeneral.this, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginGeneral.this, "Usuario no activo", Toast.LENGTH_SHORT).show();
                                }


                                break;
                            case "":
                                System.out.println("entro aki 3");
                                Toast.makeText(LoginGeneral.this, "Usuario no autorizado", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginGeneral.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = super.getHeaders();
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}