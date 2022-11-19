package ec.edu.insta.movilgc1.ui.employe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.ui.MainActivity;
import ec.edu.insta.movilgc1.ui.admin.DatosPerfilEmpresa;
import org.json.JSONException;
import org.json.JSONObject;

public class InicioCV extends AppCompatActivity {
    TextView view_cv_ID,view_cv_username, view_cv_nombres_apellidos, view_cv_fecha_nacimiento, view_cv_cedula_ciudadania, view_cv_direccion, view_cv_telefono, view_cv_estado_civil, view_cv_rol, view_cv_genero, view_cv_correo, view_cv_ciudad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_cv);
        Bundle bundle = getIntent().getExtras();
        view_cv_ID = findViewById(R.id.view_cv_ID);
        String id_usuario = bundle.getString("id_usuario");
        view_cv_ID.setText(id_usuario);
        perfilEstudiante(Integer.parseInt(view_cv_ID.getText().toString()));
    }

    public void goInicioBuscoEmpleo(View view) {
        Intent intent = new Intent(this, InicioBuscoEmpleo.class);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cerrar_sesion) {
            Toast.makeText(this, "Cerrar Sesión", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void perfilEstudiante(int id) {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes/1" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("RESPUESTA: " + response);
                    view_cv_ID = findViewById(R.id.view_cv_ID);
                    view_cv_ID.setText(response.getString("id"));

                    view_cv_username = findViewById(R.id.view_cv_username);
                    view_cv_username.setText(response.getString("usuario"));

                    view_cv_nombres_apellidos = findViewById(R.id.view_cv_nombres_apellidos);
                    view_cv_nombres_apellidos.setText(response.getString("nombres"));

                    view_cv_fecha_nacimiento = findViewById(R.id.view_cv_fecha_nacimiento);
                    view_cv_fecha_nacimiento.setText(response.getString("fechaNacimiento"));

                    view_cv_cedula_ciudadania = findViewById(R.id.view_cv_cedula_ciudadania);
                    view_cv_cedula_ciudadania.setText(response.getString("cedula"));

                    view_cv_direccion = findViewById(R.id.view_cv_direccion);
                    view_cv_direccion.setText(response.getString("direccion"));

                    view_cv_telefono = findViewById(R.id.view_cv_telefono);
                    view_cv_telefono.setText(response.getJSONObject("usuario").getString("telefono"));

                    view_cv_estado_civil = findViewById(R.id.view_cv_estado_civil);
                    view_cv_estado_civil.setText(response.getJSONObject("usuario").getString("telefono"));

                    view_cv_rol = findViewById(R.id.view_cv_rol);
                    view_cv_rol.setText(response.getString("departamento"));

                    view_cv_genero = findViewById(R.id.view_cv_genero);
                    view_cv_genero.setText(response.getString("genero"));

                    view_cv_correo = findViewById(R.id.view_cv_correo);
                    view_cv_correo.setText(response.getString("email"));

                    view_cv_ciudad = findViewById(R.id.view_cv_ciudad);
                    view_cv_ciudad.setText(
                            response.getJSONObject("ciudad").getString("nombre") + ", Provincia " +
                                    response.getJSONObject("ciudad").getJSONObject("provincia").getString("nombre"));


                    System.out.println(response.getJSONObject("usuario").getBoolean("estado"));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InicioCV.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}