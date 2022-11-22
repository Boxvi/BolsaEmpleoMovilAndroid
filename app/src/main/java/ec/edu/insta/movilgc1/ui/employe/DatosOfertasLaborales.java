package ec.edu.insta.movilgc1.ui.employe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatosOfertasLaborales extends AppCompatActivity {

    private TextView view_id_labores, view_cargo, view_descripcion, view_areaConocimiento, view_salario, view_jornada, view_requisitos, view_experiencia, view_ubicaci, view_fechaInicio, view_fechafin, view_empresa, view_ciudad_laborales;

    private String cedula;
    private Button btn_boton;

    private String usuario, idusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_ofertas_laborales);

        view_id_labores = findViewById(R.id.view_id_labore);
        Bundle bundle = getIntent().getExtras();
        view_id_labores.setText(bundle.getString("idOferta"));
        usuario = InicioBuscoEmpleo.user;
        idusuario = InicioBuscoEmpleo.iduser;
        System.out.println(view_id_labores.getText());
        getEstudiante(idusuario);
        cedula = bundle.getString("cedula");
        PerfilOfertas(Integer.parseInt(view_id_labores.getText().toString()));

    }

    public void getEstudiante(String id) {
        System.out.println("hola");
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/usuarios/" + id;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String URLget = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes/";

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLget, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response1) {

                        for (int i = 0; i < response1.length(); i++) {
                            try {
                                JSONObject jsonObject = new JSONObject(response1.get(i).toString());
                                String u = jsonObject.getString("username");
                                if (u.equalsIgnoreCase(usuario)) {
                                    cedula = jsonObject.getString("cedula");
                                    System.out.println(cedula + " " + u);
                                    btn_boton = findViewById(R.id.botonAplicar);
                                    btn_boton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {
                                                postPostulacion(Integer.parseInt(view_id_labores.getText().toString()), cedula);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    break;
                                } else {
                                    System.out.println("no soy");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        System.out.println("No entre estudiante");
                    }
                });
                requestQueue.add(jsonArrayRequest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                System.out.println("No entre usuario");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void postPostulacion(int id, String cedulaPost) throws JSONException {
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/ofertas/" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String URLPOST = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/postulaciones";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("estado", "En Proceso");
                    jsonObject.put("ofertalaboral_id", response.getInt("id"));
                    jsonObject.put("cedula", cedulaPost);
                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, URLPOST, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                System.out.println("RESPUESTA: " + response);
                                Toast.makeText(DatosOfertasLaborales.this, "Creado Postulacion", Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DatosOfertasLaborales.this, "Cliente ya aplicado " + error.toString(), Toast.LENGTH_SHORT).show();
                            System.out.println(error);
                        }
                    });
                    requestQueue.add(jsonObjectRequest1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DatosOfertasLaborales.this, "Cliente ya aplicado " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void PerfilOfertas(int id) {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/ofertas/" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    view_cargo = findViewById(R.id.view_cargo);
                    view_descripcion = findViewById(R.id.view_descripcion);
                    view_salario = findViewById(R.id.view_salario);
                    view_areaConocimiento = findViewById(R.id.view_areaConocimiento);
                    view_jornada = findViewById(R.id.view_jornada);
                    view_experiencia = findViewById(R.id.view_experiencia);
                    view_requisitos = findViewById(R.id.view_requisitos);
                    view_ubicaci = findViewById(R.id.view_ubicaci);
                    view_fechaInicio = findViewById(R.id.view_fechaInicio);
                    view_fechafin = findViewById(R.id.view_fechafin);
                    view_empresa = findViewById(R.id.view_empresa);
                    view_ciudad_laborales = findViewById(R.id.view_ciudad_laborales);
                    view_cargo.setText(response.getString("cargo"));
                    view_descripcion.setText(response.getString("descripcion"));
                    view_areaConocimiento.setText(response.getString("area_conocimiento"));
                    view_salario.setText(response.getString("salario"));
                    view_jornada.setText(response.getString("jornada"));
                    view_requisitos.setText(response.getString("requisitos_academicos"));
                    view_experiencia.setText(response.getString("experiencia"));
                    view_ubicaci.setText(response.getString("ubicacion"));
                    view_fechaInicio.setText(response.getString("fecha_inicio"));
                    view_fechafin.setText(response.getString("fecha_fin"));
                    view_empresa.setText(response.getJSONObject("empresa").getString("nombre"));
                    view_ciudad_laborales.setText(response.getJSONObject("ciudad").getString("nombre"));


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DatosOfertasLaborales.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

}