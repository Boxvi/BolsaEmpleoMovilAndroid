package ec.edu.insta.movilgc1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.model.estudiante.Estudiante;
import ec.edu.insta.movilgc1.model.estudiante.ModeloEstudiante;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegistroPerfilEmpleado extends AppCompatActivity {
    private Spinner spinnerGenero, spinnerCiudad, spinerEstadoCivil;
    private TextView view_username_r;
    private EditText editTextCedula, editTextNombres, editTextApellidos, editTextDate, editTextDireccion;
    private Button btn_registrare;
    private ImageButton btn_regresar_login;
    ModeloEstudiante modeloEstudiante = new ModeloEstudiante();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_perfil_empleado);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        spinnerCiudad = findViewById(R.id.spinnerCiudad);
        spinerEstadoCivil = findViewById(R.id.spinnerEstadoCivil);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.generos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = dameCiudades();
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiudad.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.estadoCivil, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerEstadoCivil.setAdapter(adapter3);
        view_username_r = findViewById(R.id.view_username_r);
        Bundle bundle = getIntent().getExtras();
        view_username_r.setText(bundle.getString("username"));
        editTextCedula = findViewById(R.id.editTextCedula);
        editTextNombres = findViewById(R.id.editTextNombres);
        editTextApellidos = findViewById(R.id.editTextApellidos);
        editTextDate = findViewById(R.id.editTextDate);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        btn_registrare = findViewById(R.id.btn_registrarse);
        btn_registrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextCedula.getText().toString().isEmpty() || editTextNombres.getText().toString().isEmpty()
                        || editTextApellidos.getText().toString().isEmpty() || editTextDate.getText().toString().isEmpty()
                        || editTextDireccion.getText().toString().isEmpty() || spinnerGenero.getSelectedItem().toString().isEmpty()
                        || spinnerCiudad.getSelectedItem().toString().isEmpty()
                        || spinerEstadoCivil.getSelectedItem().toString().isEmpty() || view_username_r.getText().toString().isEmpty()) {
                    camposVacios();
                    Toast.makeText(RegistroPerfilEmpleado.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    registroEstudiante(view_username_r.getText().toString(), editTextCedula.getText().toString(),
                            editTextNombres.getText().toString(), editTextApellidos.getText().toString(), spinnerGenero.getSelectedItem().toString(),
                            editTextDate.getText().toString(), spinnerCiudad.getSelectedItem().toString(), editTextDireccion.getText().toString(), spinerEstadoCivil.getSelectedItem().toString(), null, null);
                }
            }
        });
        btn_regresar_login = findViewById(R.id.btn_regresar_login);
        btn_regresar_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroPerfilEmpleado.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void camposVacios() {
        ArrayList<Estudiante> estudianteArrayList = modeloEstudiante.read(RegistroPerfilEmpleado.this);
        for (Estudiante estudiante : estudianteArrayList) {
            if (estudiante.getCedula().equals(editTextCedula.getText().toString())) {
                Toast.makeText(this, "Ya existe un estudiante con esa cédula", Toast.LENGTH_SHORT).show();
                editTextCedula.setError("Ya existe un estudiante con esa cédula");
                editTextCedula.requestFocus();
                return;
            }
        }
        if (editTextCedula.getText().toString().isEmpty()) {
            editTextCedula.setError("Ingrese su cedula");
            editTextCedula.requestFocus();
            return;
        }
        if (editTextNombres.getText().toString().isEmpty()) {
            editTextNombres.setError("Ingrese sus nombres");
            editTextNombres.requestFocus();
            return;
        }
        if (editTextApellidos.getText().toString().isEmpty()) {
            editTextApellidos.setError("Ingrese sus apellidos");
            editTextApellidos.requestFocus();
            return;
        }
        if (editTextDate.getText().toString().isEmpty()) {
            editTextDate.setError("Ingrese su fecha de nacimiento");
            editTextDate.requestFocus();
            return;
        }
        if (editTextDireccion.getText().toString().isEmpty()) {
            editTextDireccion.setError("Ingrese su direccion");
            editTextDireccion.requestFocus();
            return;
        }

        if (spinnerGenero.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Seleccione su genero", Toast.LENGTH_SHORT).show();
            return;
        }
        if (spinnerCiudad.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Seleccione su ciudad", Toast.LENGTH_SHORT).show();
            return;
        }
        if (spinerEstadoCivil.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Seleccione su estado civil", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private ArrayAdapter<CharSequence> dameCiudades() {
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/ciudades";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        adapter.add(response.getJSONObject(i).getString("nombre"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        requestQueue.add(jsonArrayRequest);
        return adapter;
    }
    private void registroEstudiante(String username, String cedula, String nombres, String apellidos, String genero,
                                    String fechaNacimiento, String ciudad, String direccion, String estadoCivil,
                                    String rutaImagen, String urlImagen) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes";
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id", "0");
            jsonBody.put("username", username);
            jsonBody.put("cedula", cedula);
            jsonBody.put("nombres", nombres);
            jsonBody.put("apellidos", apellidos);
            switch (genero) {
                case "Masculino":
                    jsonBody.put("genero", "M");
                    break;
                case "Femenino":
                    jsonBody.put("genero", "F");
                    break;
            }
            jsonBody.put("fechaNacimiento", fechaNacimiento);
            jsonBody.put("ciudad", ciudad);
            jsonBody.put("direccion", direccion);
            jsonBody.put("estadoCivil", estadoCivil);
            jsonBody.put("rutaImagen", rutaImagen);
            jsonBody.put("urlImagen", urlImagen);
            System.out.println(jsonBody);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(RegistroPerfilEmpleado.this, "SUS DATOS SERAN VALIDADOS POR UN ADMINISTRADOR PARA QUE PUEDA USAR EL APLICATIVO", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroPerfilEmpleado.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}