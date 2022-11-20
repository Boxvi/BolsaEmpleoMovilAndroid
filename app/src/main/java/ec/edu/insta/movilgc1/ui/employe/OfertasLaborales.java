package ec.edu.insta.movilgc1.ui.employe;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.ui.MainActivity;

public class OfertasLaborales extends AppCompatActivity {

    String id_estudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas_laborales);

        Bundle bundle = getIntent().getExtras();
        id_estudiante = bundle.getString("id_estudiante");
    }

    public void goInicioBuscoEmpleo(View view) {
        Intent intent = new Intent(this, InicioBuscoEmpleo.class);
        Bundle bundle = new Bundle();
        bundle.putString("id_estudiante", id_estudiante);
        intent.putExtras(bundle);
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