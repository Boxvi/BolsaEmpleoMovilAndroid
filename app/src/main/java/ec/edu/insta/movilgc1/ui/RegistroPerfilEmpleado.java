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

    private     ImageButton btn_regresar_login;

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
                registrarPerfilEmpleado();
            }
        });

        btn_regresar_login= findViewById(R.id.btn_regresar_login);
        btn_regresar_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroPerfilEmpleado.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registrarPerfilEmpleado() {
        String cedula = editTextCedula.getText().toString();
        String nombres = editTextNombres.getText().toString();
        String apellidos = editTextApellidos.getText().toString();
        String fechaNacimiento = editTextDate.getText().toString();
        String direccion = editTextDireccion.getText().toString();
        String genero = spinnerGenero.getSelectedItem().toString();
        String ciudad = spinnerCiudad.getSelectedItem().toString();
        String estadoCivil = spinerEstadoCivil.getSelectedItem().toString();
        String username = view_username_r.getText().toString();

        ArrayList<Estudiante> estudianteArrayList = modeloEstudiante.read(RegistroPerfilEmpleado.this);

        for (Estudiante estudiante : estudianteArrayList) {
            if (estudiante.getCedula().equals(cedula)) {
                Toast.makeText(this, "Ya existe un estudiante con esa cédula", Toast.LENGTH_SHORT).show();
                editTextCedula.setError("Ya existe un estudiante con esa cédula");
                editTextCedula.requestFocus();
                return;
            }

        }


        if (cedula.isEmpty()) {
            editTextCedula.setError("Ingrese su cedula");
            editTextCedula.requestFocus();
            return;
        }
        if (nombres.isEmpty()) {
            editTextNombres.setError("Ingrese sus nombres");
            editTextNombres.requestFocus();
            return;
        }
        if (apellidos.isEmpty()) {
            editTextApellidos.setError("Ingrese sus apellidos");
            editTextApellidos.requestFocus();
            return;
        }
        if (fechaNacimiento.isEmpty()) {
            editTextDate.setError("Ingrese su fecha de nacimiento");
            editTextDate.requestFocus();
            return;
        }
        if (direccion.isEmpty()) {
            editTextDireccion.setError("Ingrese su direccion");
            editTextDireccion.requestFocus();
            return;
        }

        if (genero.isEmpty()) {
            Toast.makeText(this, "Seleccione su genero", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ciudad.isEmpty()) {
            Toast.makeText(this, "Seleccione su ciudad", Toast.LENGTH_SHORT).show();
            return;
        }
        if (estadoCivil.isEmpty()) {
            Toast.makeText(this, "Seleccione su estado civil", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || fechaNacimiento.isEmpty() || direccion.isEmpty() || genero.isEmpty() || ciudad.isEmpty() || estadoCivil.isEmpty() || username.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        System.out.println("cedula: " + cedula + " nombres: " + nombres + " apellidos: " + apellidos + " fechaNacimiento: " + fechaNacimiento + " direccion: " + direccion + " genero: " + genero + " ciudad: " + ciudad + " estadoCivil: " + estadoCivil + " username: " + username);

        Estudiante estudiante = new Estudiante(0, cedula, nombres, apellidos, genero, fechaNacimiento, ciudad, direccion, estadoCivil, null, null, false);

        registroEstudiante(username, estudiante);
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

    private void registroEstudiante(String username, Estudiante estudiante) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/estudiantes";

        JSONObject jsonBody = new JSONObject();

        try {

            switch(estudiante.getGenero()) {
                case "Masculino":
                    estudiante.setGenero("M");
                    break;
                case "Femenino":
                    estudiante.setGenero("F");
                    break;
            }


            jsonBody.put("username", username);
            jsonBody.put("cedula", estudiante.getCedula());
            jsonBody.put("nombres", estudiante.getNombres());
            jsonBody.put("apellidos", estudiante.getApellidos());
            jsonBody.put("genero", estudiante.getGenero());
            jsonBody.put("fechaNacimiento", estudiante.getFechaNacimiento());
            jsonBody.put("direccion", estudiante.getDireccion());
            jsonBody.put("ciudad", estudiante.getCiudad());
            jsonBody.put("estadoCivil", estudiante.getEstadoCivil());
            jsonBody.put("rutaImagen", estudiante.getRutaImagen());
            jsonBody.put("urlImagen", estudiante.getUrlImagen());

            System.out.println(jsonBody);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(RegistroPerfilEmpleado.this, "SUS DATOS SERAN VALIDADOS POR UN ADMINISTRADOR PARA QUE PUEDE USAR EL APLICATIVO", Toast.LENGTH_SHORT).show();
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