package ec.edu.insta.movilgc1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class InicioBuscoEmpleo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_busco_empleo);
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