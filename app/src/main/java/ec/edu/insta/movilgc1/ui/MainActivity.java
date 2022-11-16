package ec.edu.insta.movilgc1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.model.usuario.ModeloUsuario;
import ec.edu.insta.movilgc1.model.usuario.Usuario;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Bundle bundle = new Bundle();

    Button btn_busco_empleo, btn_empleador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_busco_empleo = findViewById(R.id.btn_busco_empleo);

        btn_busco_empleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos();

            }
        });
    }

    //ir login empleado
    public void goLoginEmpleado(View view) {
        bundle.putString("tipo", "BUSCO EMPLEO");
        Intent intent = new Intent(this, LoginGeneral.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    //ir login admin
    public void goLoginAdmin(View view) {
        bundle.putString("tipo", "ERES ADMIN?");
        Intent intent = new Intent(this, LoginGeneral.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void datos() {
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/usuarios";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ModeloUsuario modeloUsuario = new ModeloUsuario();

                    ArrayList<Usuario> usuarioArrayList = modeloUsuario.read(MainActivity.this);

                    if (usuarioArrayList == null) {
                        for (int i = usuarioArrayList.size(); i < response.length(); i++) {
                            modeloUsuario = getModeloUsuario(response, i);
                            modeloUsuario.create(MainActivity.this);
                        }
                        Toast.makeText(MainActivity.this, "DATOS ACTUALIZADOS", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = usuarioArrayList.size(); i < response.length(); i++) {
                            modeloUsuario = getModeloUsuario(response, i);
                            modeloUsuario.create(MainActivity.this);
                        }
                        for (int i = 0; i < response.length(); i++) {
                            modeloUsuario = getModeloUsuario(response, i);
                            modeloUsuario.update(MainActivity.this, response.getJSONObject(i).getInt("id"));
                        }
                        Toast.makeText(MainActivity.this, "DATOS SINCRONIZADOS", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }

    @NotNull
    private ModeloUsuario getModeloUsuario(JSONArray response, int i) throws JSONException {
        ModeloUsuario modeloUsuario;
        modeloUsuario = new ModeloUsuario(
                response.getJSONObject(i).getInt("id"),
                response.getJSONObject(i).getString("username"),
                response.getJSONObject(i).getString("password"),
                response.getJSONObject(i).getString("email"),
                response.getJSONObject(i).getString("telefono"),
                response.getJSONObject(i).getBoolean("estado"),
                response.getJSONObject(i).getString("fechaCreacion"),
                response.getJSONObject(i).getString("rol"));
        return modeloUsuario;
    }

}
