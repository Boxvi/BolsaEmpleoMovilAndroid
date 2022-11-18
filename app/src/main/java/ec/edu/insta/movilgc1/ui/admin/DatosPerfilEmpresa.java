package ec.edu.insta.movilgc1.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import org.json.JSONException;
import org.json.JSONObject;

public class DatosPerfilEmpresa extends AppCompatActivity {


    private TextView view_ID, view_departamento, view_ciudad, view_nombres_empresa, view_ruc, view_tipo_empresa, view_direccion, view_sector_empresa, view_razon_social, view_correo_empresa, view_telefono_empresa;

    private ImageButton btn_regresar_pefil_empresa;

    private Button btn_activar, btn_desactivar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_perfil_empresa);

        view_ID = findViewById(R.id.view_ID);
        Bundle bundle = getIntent().getExtras();
        view_ID.setText(bundle.getString("id_empresa"));

        perfilEmpresas(Integer.parseInt(view_ID.getText().toString()));

        btn_regresar_pefil_empresa = findViewById(R.id.btn_regresar_pefil_empresa);
        btn_regresar_pefil_empresa.setOnClickListener(new View.OnClickListener() {
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
                modificarEstado(Integer.parseInt(view_ID.getText().toString()), true);
                extracted();
            }
        });

        btn_desactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarEstado(Integer.parseInt(view_ID.getText().toString()), false);
                extracted();
            }
        });


    }

    private void extracted() {
        Intent intent = new Intent(DatosPerfilEmpresa.this, PerfilEmpresa.class);
        startActivity(intent);
        finish();
    }


    public void goPerfilEmpresa(View view) {
        Intent intent = new Intent(this, InicioAdminBusqueda.class);
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

    public void perfilEmpresas(int id) {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/empresas/" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    view_nombres_empresa = findViewById(R.id.view_nombres_empresa);
                    view_ruc = findViewById(R.id.view_ruc);
                    view_tipo_empresa = findViewById(R.id.view_tipo_empresa);
                    view_direccion = findViewById(R.id.view_direccion);
                    view_sector_empresa = findViewById(R.id.view_sector_empresa);
                    view_ciudad = findViewById(R.id.view_ciudad);
                    view_correo_empresa = findViewById(R.id.view_correo_empresa);
                    view_telefono_empresa = findViewById(R.id.view_telefono_empresa);
                    view_departamento = findViewById(R.id.view_departamento);
                    view_razon_social = findViewById(R.id.view_razon_social);


                    view_nombres_empresa.setText(response.getString("nombre"));
                    view_ruc.setText(response.getString("ruc"));
                    view_tipo_empresa.setText(response.getString("tipoEmpresa"));
                    view_direccion.setText(response.getString("direccion"));
                    view_sector_empresa.setText(response.getJSONObject("sectorEmpresarial").getString("nombre"));
                    view_ciudad.setText(
                            response.getJSONObject("ciudad").getString("nombre") + ", Provincia " +
                                    response.getJSONObject("ciudad").getJSONObject("provincia").getString("nombre"));
                    view_razon_social.setText(response.getString("razonSocial"));
                    view_correo_empresa.setText(response.getString("sitioWeb"));
                    view_telefono_empresa.setText(response.getJSONObject("usuario").getString("telefono"));
                    view_departamento.setText(response.getString("departamento"));



                    btn_activar = findViewById(R.id.btn_activar);
                    btn_desactivar = findViewById(R.id.btn_desactivar);


                    if (response.getJSONObject("usuario").getBoolean("estado") == true) {
                        btn_activar.setVisibility(View.GONE);
                        btn_desactivar.setVisibility(View.VISIBLE);
                    } else {
                        btn_activar.setVisibility(View.VISIBLE);
                        btn_desactivar.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DatosPerfilEmpresa.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void modificarEstado(int id, Boolean estado) {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/empresas/" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String URLPUT = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/usuarios/" + response.getJSONObject("usuario").getInt("id");

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("username", response.getJSONObject("usuario").getString("username"));

                    jsonObject.put("password", "1234");

                    //modificar estado pero no contrasena
                    //jsonObject.put("password", response.getJSONObject("usuario").getString("password"));
                    //jsonObject.put("password", null);

                    jsonObject.put("email", response.getJSONObject("usuario").getString("email"));
                    jsonObject.put("telefono", response.getJSONObject("usuario").getString("telefono"));
                    jsonObject.put("estado", estado);
                    jsonObject.put("rol", response.getJSONObject("usuario").getJSONObject("rol").getString("nombre"));

                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.PUT, URLPUT, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(DatosPerfilEmpresa.this, "Estado Modificado", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DatosPerfilEmpresa.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(jsonObjectRequest1);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(DatosPerfilEmpresa.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);


    }
}
