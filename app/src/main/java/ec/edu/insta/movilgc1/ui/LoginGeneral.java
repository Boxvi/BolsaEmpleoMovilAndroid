package ec.edu.insta.movilgc1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

@Data
public class LoginGeneral extends AppCompatActivity {
    private EditText txtUsuario, txtContrasena;
    private Button btnIngresar;
    private TextView txt_busco_empleo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_general);

        txtUsuario = findViewById(R.id.txt_username);
        txtContrasena = findViewById(R.id.txt_password);
        btnIngresar = findViewById(R.id.btn_login);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEmpleadoPost(txtUsuario.getText().toString(), txtContrasena.getText().toString());
                // listarDatos();
            }
        });


        txt_busco_empleo = findViewById(R.id.txt_busco_empleo);

        Bundle bundle = getIntent().getExtras();
        txt_busco_empleo.setText(bundle.getString("tipo"));

    }

    // ir main activity
    public void goMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //LOGIN EMPLEADO
    public void LoginEmpleadoPost(String usuario, String contrasena) {
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
                    Toast.makeText(LoginGeneral.this, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
                    try {

                        switch (response.getJSONArray("authorities").getJSONObject(0).getString("authority")) {
                            case "ROLE_EMPRESA":
                                System.out.println("entro aki 1");
                                Intent intent = new Intent(LoginGeneral.this, InicioAdminBusqueda.class);
                                startActivity(intent);
                                // finish();
                                break;

                            case "ROLE_ESTUDIANTE":
                                System.out.println("entro aki 2");
                                Intent intent2 = new Intent(LoginGeneral.this, InicioBuscoEmpleo.class);
                                startActivity(intent2);
                                finish();
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

    /*
    //volley metho get
    public void listarDatos() {
        String url = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/roles";
        System.out.println("url: " + url);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            JSONObject jsonObject = null;

            @Override
            public void onResponse(String response) {
                try {
                    jsonObject = new JSONObject(response);
                    System.out.println("json: " + jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        Volley.newRequestQueue(this).add(request);
    }*/

