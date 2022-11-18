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
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.ui.MainActivity;

public class InicioCV extends AppCompatActivity {

    TextView txt_nombres_apellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_cv);
        Bundle bundle = getIntent().getExtras();

        String id_estudiante = bundle.getString("id_estudiante");
        System.out.println("id_estudiante: " + id_estudiante);

        txt_nombres_apellidos = findViewById(R.id.txt_nombres_apellidos);

        txt_nombres_apellidos.setText(id_estudiante);

    }

    public void goInicioBuscoEmpleo(View view) {
        Intent intent = new Intent(this, InicioBuscoEmpleo.class);
        Bundle bundle = new Bundle();
        bundle.putString("id_estudiante", txt_nombres_apellidos.getText().toString());
        intent.putExtras(bundle);
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

}