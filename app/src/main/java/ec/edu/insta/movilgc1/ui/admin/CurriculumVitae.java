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
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.ui.MainActivity;
import ec.edu.insta.movilgc1.ui.employe.InicioCV;
import org.json.JSONException;
import org.json.JSONObject;
public class CurriculumVitae extends AppCompatActivity {

    private TextView view_cv_ID, view_cv_nombres_apellidos, view_cv_fecha_nacimiento, view_cv_cedula_ciudadanina, view_cv_username,
            view_cv_direccion, view_cv_correo, view_cv_telefono, view_cv_estado_civil, view_cv_rol, view_cv_genero, view_cv_ciudad;
    private ImageView btn_regresar_pefil_empresa_cv;
    private Button btn_activar, btn_desactivar;

    private ImageView imgPersona;
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
                    imgPersona = findViewById(R.id.imgPersona);

                    view_cv_nombres_apellidos.setText(response.getString("nombres") + " " + response.getString("apellidos"));
                    view_cv_fecha_nacimiento.setText(response.get("fechaNacimiento").toString());
                    view_cv_username.setText(response.getJSONObject("usuario").getString("username"));
                    view_cv_cedula_ciudadanina.setText(response.getString("cedula"));
                    view_cv_direccion.setText(response.getString("direccion"));
                    view_cv_telefono.setText(response.getJSONObject("usuario").getString("telefono"));
                    view_cv_estado_civil.setText(response.getString("estadoCivil"));
                    view_cv_rol.setText(response.getJSONObject("usuario").getJSONObject("rol").getString("nombre"));
                    view_cv_genero.setText(response.getString("genero"));
                    view_cv_ciudad.setText(response.getJSONObject("ciudad").getString("nombre"));
                    view_cv_correo.setText(response.getJSONObject("usuario").getString("email"));


                    String urlfoto = "https://pespringboots3bucket.s3.amazonaws.com/" + response.getString("rutaImagen");

                    System.out.println("urlfoto: " + urlfoto);


                    ImageRequest imageRequest = new ImageRequest(urlfoto, response2 -> {
                        imgPersona.setImageBitmap(response2);
                    }, 0, 0, ImageView.ScaleType.CENTER, null, error -> {
                        Toast.makeText(CurriculumVitae.this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                    });

                    requestQueue.add(imageRequest);



                    btn_activar = findViewById(R.id.btn_activar);
                    btn_desactivar = findViewById(R.id.btn_desactivar);
                    if (response.getJSONObject("usuario").getBoolean("estado") == true) {
                        btn_activar.setVisibility(View.GONE);
                        btn_desactivar.setVisibility(View.VISIBLE);
                    } else {
                        btn_activar.setVisibility(View.VISIBLE);
                        btn_desactivar.setVisibility(View.GONE);
                    }
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
    private void modificarEstado(int parseInt, boolean estado) {
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes/" + parseInt;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String URLPUT = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/usuarios/" + response.getJSONObject("usuario").getInt("id");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", response.getJSONObject("usuario").getString("username"));
                    jsonObject.put("email", response.getJSONObject("usuario").getString("email"));
                    jsonObject.put("password", "1234");
                    jsonObject.put("telefono", response.getJSONObject("usuario").getString("telefono"));
                    jsonObject.put("estado", estado);
                    jsonObject.put("rol", response.getJSONObject("usuario").getJSONObject("rol").getString("nombre"));
                    System.out.println(jsonObject.toString());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URLPUT, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(CurriculumVitae.this, "Estado modificado", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CurriculumVitae.this, "Error al modificar: " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CurriculumVitae.this, "Error al modificar: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
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