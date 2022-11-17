package ec.edu.insta.movilgc1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.model.usuario.ModeloUsuario;
import ec.edu.insta.movilgc1.model.usuario.Usuario;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegistroEmpleado extends AppCompatActivity {

    private EditText editTextEmail, editTextUsername, editTextPassword, editTextConfirmPassword, editTextTelefono;
    private Button btn_siguiente;
    private ImageButton btn_regresar_login;

    private ModeloUsuario modeloUsuario = new ModeloUsuario();

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empleado);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextTelefono = findViewById(R.id.editTextTelefono);

        btn_siguiente = findViewById(R.id.btn_siguiente);
        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroUsuario();
                Intent intent = new Intent(RegistroEmpleado.this, RegistroPerfilEmpleado.class);
                bundle.putString("username", editTextUsername.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        btn_regresar_login = findViewById(R.id.btn_regresar_login);
        btn_regresar_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroEmpleado.this, LoginGeneral.class);
                startActivity(intent);
                finish();
            }
        });


    }


    private void RegistroUsuario() {
        String email = editTextEmail.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();
        String telefono = editTextTelefono.getText().toString();

        if (email.isEmpty()) {
            editTextEmail.setError("El email es requerido");
            editTextEmail.requestFocus();
            return;
        }

        ArrayList<Usuario> usuarios = modeloUsuario.read(RegistroEmpleado.this);

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                editTextEmail.setError("El email ya existe");
                editTextEmail.requestFocus();
                return;
            }
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                editTextUsername.setError("El username ya existe");
                editTextUsername.requestFocus();
                return;
            }
        }


        if (username.isEmpty()) {
            editTextUsername.setError("El nombre de usuario es requerido");
            editTextUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("La contraseña es requerida");
            editTextPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("La confirmación de la contraseña es requerida");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (telefono.isEmpty()) {
            editTextTelefono.setError("El teléfono es requerido");
            editTextTelefono.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Las contraseñas no coinciden");
            editTextConfirmPassword.requestFocus();
            return;
        }

        Usuario usuario = new Usuario(null, username, password, email, telefono, false, null, "ROLE_ESTUDIANTE");

        registroUsuario(usuario);

        System.out.println("Email: " + email + " Username: " + username + " Password: " + password + " ConfirmPassword: " + confirmPassword + " Telefono: " + telefono);
    }

    private void registroUsuario(Usuario usuario) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/auth/signup";

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username", usuario.getUsername());
            jsonObject.put("password", usuario.getPassword());
            jsonObject.put("email", usuario.getEmail());
            jsonObject.put("telefono", usuario.getTelefono());
            jsonObject.put("estado", usuario.isEstado());
            jsonObject.put("rol", usuario.getRol());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, response -> {
                try {
                    System.out.println("Response: " + response.getString("message"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                System.out.println("Error: " + error.getMessage());
            });

            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}