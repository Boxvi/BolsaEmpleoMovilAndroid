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

public class InicioBuscoEmpleo extends AppCompatActivity {

    private TextView view_nombre_estudiante, view_id_estudiante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_busco_empleo);

        view_id_estudiante = findViewById(R.id.view_id_estudiante);
        Bundle bundle = getIntent().getExtras();
        view_id_estudiante.setText(bundle.getString("id_estudiante"));

       /// Bundle bundle1 = getIntent().getExtras();

        view_nombre_estudiante = findViewById(R.id.view_nombre_estudiante);
        view_nombre_estudiante.setText(bundle.getString("token"));


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

    //ir Curriculum vitae
    public void goCurriculumVitae(View view) {
        Intent intent = new Intent(this, InicioCV.class);
        startActivity(intent);

    }

    //ir Ofertas Laborales
    public void goOfertasLaborales(View view) {
        Intent intent = new Intent(this, OfertasLaborales.class);
        startActivity(intent);

    }

    //if Ofertas aplicadas
    public void goOfertasAplicadas(View view) {
        Intent intent = new Intent(this, OfertasAplicadas.class);
        startActivity(intent);

    }





}