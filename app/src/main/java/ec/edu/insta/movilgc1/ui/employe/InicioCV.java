package ec.edu.insta.movilgc1.ui.employe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.ui.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class InicioCV extends AppCompatActivity {


    private ImageButton btn_regresar_pefil_empresa_cv;

    private TextView view_cv_ID, view_cv_nombres_apellidos, view_cv_fecha_nacimiento, view_cv_cedula_ciudadanina, view_cv_username,
            view_cv_direccion, view_cv_correo, view_cv_telefono, view_cv_estado_civil, view_cv_rol, view_cv_genero, view_cv_ciudad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_cv);

        Bundle bundle = getIntent().getExtras();

        view_cv_ID = findViewById(R.id.view_cv_ID);
        view_cv_username = findViewById(R.id.view_cv_username);

        view_cv_ID.setText(bundle.getString("username_id"));

        view_cv_username.setText(bundle.getString("username_estudiante"));


        //curriculimVitae(view_cv_ID.getText().toString());
        dameEstudiantes();

        btn_regresar_pefil_empresa_cv = findViewById(R.id.btn_regresar_pefil_empresa_cv);
        btn_regresar_pefil_empresa_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioCV.this, InicioBuscoEmpleo.class);
                Bundle bundle = new Bundle();
                bundle.putString("username_id", view_cv_ID.getText().toString());
                bundle.putString("username_estudiante", view_cv_username.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);

                finish();
            }
        });

    }

    private void dameEstudiantes() {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {

                   // System.out.println("response: " + response.getJSONObject(i).getString("id"));
                    System.out.println("response: " + response.getJSONObject(i).getString("username"));
                    System.out.println(view_cv_username.getText().toString());
                    if (view_cv_username.getText().toString().equals(response.getJSONObject(i).getString("username"))) {


                        String URL2 = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes/" + response.getJSONObject(i).getString("id");

                        RequestQueue requestQueue2 = Volley.newRequestQueue(this);

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL2, null, response1 -> {
                            try {
                                view_cv_nombres_apellidos = findViewById(R.id.view_cv_nombres_apellidos);
                                view_cv_fecha_nacimiento = findViewById(R.id.view_cv_fecha_nacimiento);
                                view_cv_cedula_ciudadanina = findViewById(R.id.view_cv_cedula_ciudadania);
                                view_cv_direccion = findViewById(R.id.view_cv_direccion);
                                view_cv_telefono = findViewById(R.id.view_cv_telefono);
                                view_cv_estado_civil = findViewById(R.id.view_cv_estado_civil);
                                view_cv_rol = findViewById(R.id.view_cv_rol);
                                view_cv_genero = findViewById(R.id.view_cv_genero);
                                view_cv_ciudad = findViewById(R.id.view_cv_ciudad);
                                view_cv_username = findViewById(R.id.view_cv_username);
                                view_cv_correo = findViewById(R.id.view_cv_correo);

                                view_cv_nombres_apellidos.setText(response1.getString("nombres") + " " + response1.getString("apellidos"));
                                view_cv_fecha_nacimiento.setText(response1.get("fechaNacimiento").toString());
                                //view_cv_username.setText(response.getJSONObject("usuario").getString("username"));
                                view_cv_cedula_ciudadanina.setText(response1.getString("cedula"));
                                view_cv_direccion.setText(response1.getString("direccion"));
                                view_cv_telefono.setText(response1.getJSONObject("usuario").getString("telefono"));
                                view_cv_estado_civil.setText(response1.getString("estadoCivil"));
                                view_cv_rol.setText(response1.getJSONObject("usuario").getJSONObject("rol").getString("nombre"));
                                view_cv_genero.setText(response1.getString("genero"));
                                view_cv_ciudad.setText(response1.getJSONObject("ciudad").getString("nombre"));
                                view_cv_correo.setText(response1.getJSONObject("usuario").getString("email"));


                            } catch (Exception e) {
                                Toast.makeText(InicioCV.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                            System.out.println("error: " + error);
                        });
                        requestQueue2.add(jsonObjectRequest);
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }, error -> {
            System.out.println("error: " + error);
        });
        requestQueue.add(jsonArrayRequest);

    }


    private void curriculimVitae(String parseInt) {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes/" + parseInt;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    view_cv_nombres_apellidos = findViewById(R.id.view_cv_nombres_apellidos);
                    view_cv_fecha_nacimiento = findViewById(R.id.view_cv_fecha_nacimiento);
                    view_cv_cedula_ciudadanina = findViewById(R.id.view_cv_cedula_ciudadania);
                    view_cv_direccion = findViewById(R.id.view_cv_direccion);
                    view_cv_telefono = findViewById(R.id.view_cv_telefono);
                    view_cv_estado_civil = findViewById(R.id.view_cv_estado_civil);
                    view_cv_rol = findViewById(R.id.view_cv_rol);
                    view_cv_genero = findViewById(R.id.view_cv_genero);
                    view_cv_ciudad = findViewById(R.id.view_cv_ciudad);
                    view_cv_username = findViewById(R.id.view_cv_username);
                    view_cv_correo = findViewById(R.id.view_cv_correo);

                    view_cv_nombres_apellidos.setText(response.getString("nombres") + " " + response.getString("apellidos"));
                    view_cv_fecha_nacimiento.setText(response.get("fechaNacimiento").toString());
                    //view_cv_username.setText(response.getJSONObject("usuario").getString("username"));
                    view_cv_cedula_ciudadanina.setText(response.getString("cedula"));
                    view_cv_direccion.setText(response.getString("direccion"));
                    view_cv_telefono.setText(response.getJSONObject("usuario").getString("telefono"));
                    view_cv_estado_civil.setText(response.getString("estadoCivil"));
                    view_cv_rol.setText(response.getJSONObject("usuario").getJSONObject("rol").getString("nombre"));
                    view_cv_genero.setText(response.getString("genero"));
                    view_cv_ciudad.setText(response.getJSONObject("ciudad").getString("nombre"));
                    view_cv_correo.setText(response.getJSONObject("usuario").getString("email"));


                } catch (Exception e) {
                    Toast.makeText(InicioCV.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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

}