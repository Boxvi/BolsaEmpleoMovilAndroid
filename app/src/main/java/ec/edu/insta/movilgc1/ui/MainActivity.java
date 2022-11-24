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
import ec.edu.insta.movilgc1.model.empresa.Empresa;
import ec.edu.insta.movilgc1.model.empresa.ModeloEmpresa;
import ec.edu.insta.movilgc1.model.estudiante.Estudiante;
import ec.edu.insta.movilgc1.model.estudiante.ModeloEstudiante;
import ec.edu.insta.movilgc1.model.ofertas.ModeloOferta;
import ec.edu.insta.movilgc1.model.ofertas.Oferta;
import ec.edu.insta.movilgc1.model.usuario.ModeloUsuario;
import ec.edu.insta.movilgc1.model.usuario.Usuario;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button btn_busco_empleo, btn_eres_admin;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarDatosUsuario();

        cargarDatosPerfilEmpresa();
        cargarConsultarCurriculum();
        cargarDatosOfertas();

        btn_busco_empleo = findViewById(R.id.btn_busco_empleo);
        btn_eres_admin = findViewById(R.id.btn_eres_admin);

        btn_busco_empleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tipo", "BUSCO EMPLEO");
                intent = new Intent(MainActivity.this, LoginGeneral.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        btn_eres_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tipo", "ERES ADMIN");
                intent = new Intent(MainActivity.this, LoginGeneral.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    public void cargarDatosUsuario() {
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/usuarios";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
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


    public void cargarDatosPerfilEmpresa() {
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/empresas";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ModeloEmpresa modeloEmpresa = new ModeloEmpresa();

                    ArrayList<Empresa> usuarioArrayList = modeloEmpresa.read(MainActivity.this);

                    if (usuarioArrayList == null) {
                        for (int i = usuarioArrayList.size(); i < response.length(); i++) {
                            modeloEmpresa = getModeloEmpresa(response, i);
                            modeloEmpresa.create(MainActivity.this);
                        }
                        Toast.makeText(MainActivity.this, "DATOS ACTUALIZADOS", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = usuarioArrayList.size(); i < response.length(); i++) {
                            modeloEmpresa = getModeloEmpresa(response, i);
                            modeloEmpresa.create(MainActivity.this);
                        }
                        for (int i = 0; i < response.length(); i++) {
                            modeloEmpresa = getModeloEmpresa(response, i);
                            modeloEmpresa.update(MainActivity.this, response.getJSONObject(i).getInt("id"));
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
    private ModeloEmpresa getModeloEmpresa(JSONArray response, int i) throws JSONException {
        ModeloEmpresa modeloEmpresa;
        modeloEmpresa = new ModeloEmpresa(
                response.getJSONObject(i).getInt("id"),
                response.getJSONObject(i).getString("sectorEmpresarial"),
                response.getJSONObject(i).getString("ruc"),
                response.getJSONObject(i).getString("nombre"),
                response.getJSONObject(i).getString("tipoEmpresa"),
                response.getJSONObject(i).getString("razonSocial"),
                response.getJSONObject(i).getString("departamento"),
                response.getJSONObject(i).getString("ciudad"),
                response.getJSONObject(i).getString("direccion"),
                response.getJSONObject(i).getString("sitioWeb"),
                false);

        return modeloEmpresa;
    }

    public void cargarConsultarCurriculum() {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ModeloEstudiante modeloEstudiante = new ModeloEstudiante();

                    ArrayList<Estudiante> usuarioArrayList = modeloEstudiante.read(MainActivity.this);

                    if (usuarioArrayList == null) {
                        for (int i = usuarioArrayList.size(); i < response.length(); i++) {
                            modeloEstudiante = getModeloEstudiante(response, i);
                            modeloEstudiante.create(MainActivity.this);
                        }
                        Toast.makeText(MainActivity.this, "DATOS ACTUALIZADOS", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = usuarioArrayList.size(); i < response.length(); i++) {
                            modeloEstudiante = getModeloEstudiante(response, i);
                            modeloEstudiante.create(MainActivity.this);
                        }
                        for (int i = 0; i < response.length(); i++) {
                            modeloEstudiante = getModeloEstudiante(response, i);
                            modeloEstudiante.update(MainActivity.this, response.getJSONObject(i).getInt("id"));
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

            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    @NotNull
    private ModeloEstudiante getModeloEstudiante(JSONArray response, int i) throws JSONException {

        ModeloEstudiante modeloEstudiante;
        modeloEstudiante = new ModeloEstudiante(
                response.getJSONObject(i).getInt("id"),
                response.getJSONObject(i).getString("cedula"),
                response.getJSONObject(i).getString("nombres"),
                response.getJSONObject(i).getString("apellidos"),
                response.getJSONObject(i).getString("genero"),
                response.getJSONObject(i).getString("fechaNacimiento"),
                response.getJSONObject(i).getString("ciudad"),
                response.getJSONObject(i).getString("direccion"),
                response.getJSONObject(i).getString("estadoCivil"),
                response.getJSONObject(i).getString("rutaImagen"),
                response.getJSONObject(i).getString("urlImagen"),
                true);

        return modeloEstudiante;

    }

    public void cargarDatosOfertas() {
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/ofertas";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ModeloOferta modeloOferta = new ModeloOferta();

                    ArrayList<Oferta> ofertaArrayList = modeloOferta.read(MainActivity.this);

                    if (ofertaArrayList == null) {
                        for (int i = ofertaArrayList.size(); i < response.length(); i++) {
                            modeloOferta = getModeloOferta(response, i);
                            modeloOferta.create(MainActivity.this);
                        }
                        Toast.makeText(MainActivity.this, "DATOS ACTUALIZADOOOOOOOOOOOOOOS", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = ofertaArrayList.size(); i < response.length(); i++) {
                            modeloOferta = getModeloOferta(response, i);
                            modeloOferta.create(MainActivity.this);
                        }
                        for (int i = 0; i < response.length(); i++) {
                            modeloOferta = getModeloOferta(response, i);
                            modeloOferta.update(MainActivity.this, response.getJSONObject(i).getInt("id"));
                        }
                        Toast.makeText(MainActivity.this, "DATOS SINCRONIZADOOOOOOOOOOOOOOOOS", Toast.LENGTH_SHORT).show();

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
    private ModeloOferta getModeloOferta(JSONArray response, int i) throws JSONException {
        ModeloOferta modeloOferta;
        modeloOferta = new ModeloOferta(
                response.getJSONObject(i).getInt("id"),
                response.getJSONObject(i).getString("cargo"),
                response.getJSONObject(i).getString("descripcion"),
                response.getJSONObject(i).getString("area_conocimiento"),
                response.getJSONObject(i).getString("salario"),
                response.getJSONObject(i).getString("jornada"),
                response.getJSONObject(i).getString("requisitos_academicos"),
                response.getJSONObject(i).getString("experiencia"),
                response.getJSONObject(i).getString("ubicacion"),
                response.getJSONObject(i).getString("fecha_inicio"),
                response.getJSONObject(i).getString("fecha_fin"),
                response.getJSONObject(i).getString("empresa"),
                response.getJSONObject(i).getString("ciudad"),
                false);
        System.out.println(response.getJSONObject(i).getInt("id"));
        return modeloOferta;
    }


}
