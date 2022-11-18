package ec.edu.insta.movilgc1.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import org.json.JSONObject;

public class CurriculumVitae extends AppCompatActivity {

    private TextView view_cv_ID;
    private ImageView btn_regresar_pefil_empresa_cv;

    private TextView txt_nombres_apellidos, txt_fecha_nacimiento, txt_cedula_ciudadanina, view_direccion, txt_telefono,
            txt_logro_nombre, txt_experiencia_laboral_nombre, txt_referencias_personales_nombre;

    private Button btn_activar, btn_desactivar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_vitae);

        view_cv_ID = findViewById(R.id.view_cv_ID);
        Bundle bundle = getIntent().getExtras();
        view_cv_ID.setText(bundle.getString("id_cv"));

        curriculimVitae(Integer.parseInt(view_cv_ID.getText().toString()));

        btn_regresar_pefil_empresa_cv = findViewById(R.id.btn_regresar_pefil_empresa_cv);
        btn_regresar_pefil_empresa_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extracted();
            }
        });

        btn_activar = findViewById(R.id.btn_activar);
        btn_desactivar = findViewById(R.id.btn_desactivar);

        btn_activar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarEstado(Integer.parseInt(view_cv_ID.getText().toString()), true);
                extracted();
            }
        });

        btn_desactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarEstado(Integer.parseInt(view_cv_ID.getText().toString()), false);
                extracted();
            }
        });

    }

    private void curriculimVitae(int parseInt) {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes/1" + parseInt;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    txt_nombres_apellidos = findViewById(R.id.txt_nombres_apellidos);
                    txt_fecha_nacimiento = findViewById(R.id.txt_fecha_nacimiento);
                    txt_cedula_ciudadanina = findViewById(R.id.txt_cedula_ciudadania);
                    view_direccion = findViewById(R.id.view_direccion);
                    txt_telefono = findViewById(R.id.txt_telefono);
                    txt_logro_nombre = findViewById(R.id.txt_logros_nombres);
                    txt_experiencia_laboral_nombre = findViewById(R.id.txt_experencia_laboral_nombre);
                    txt_referencias_personales_nombre = findViewById(R.id.txt_referencias_personales_nombre);

                    txt_nombres_apellidos.setText(response.getString("nombres") + " " + response.getString("apellidos"));
                    txt_fecha_nacimiento.setText(response.getString("fechaNacimiento"));
                    txt_cedula_ciudadanina.setText(response.getString("cedula"));
                    view_direccion.setText(response.getString("direccion"));
                    txt_telefono.setText(response.getString("telefono"));
                    //txt_logro_nombre.setText(response.getString("logros"));

                    //txt_experiencia_laboral_nombre.setText(response.getString("experienciaLaboral"));
                    //txt_referencias_personales_nombre.setText(response.getString("referenciasPersonales"));

                } catch (Exception e) {
                    Toast.makeText(CurriculumVitae.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CurriculumVitae.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);


    }

    private void modificarEstado(int parseInt, boolean b) {
    }

    private void extracted() {
        Intent intent = new Intent(CurriculumVitae.this, ConsultarCurriculum.class);
        startActivity(intent);
        finish();
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