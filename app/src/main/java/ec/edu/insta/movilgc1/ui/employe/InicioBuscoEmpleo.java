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
    public static String user;
    public static String iduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_busco_empleo);
        view_id_estudiante = findViewById(R.id.view_id_estudiante);
        view_nombre_estudiante = findViewById(R.id.view_nombre_estudiante);
        Bundle bundle = getIntent().getExtras();
        view_id_estudiante.setText(bundle.getString("username_id"));
        view_nombre_estudiante.setText(bundle.getString("username_estudiante"));
        user = view_nombre_estudiante.getText().toString();
        iduser = view_id_estudiante.getText().toString();
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
    public void goCurriculumVitae(View view) {
        Intent intent = new Intent(this, InicioCV.class);
        Bundle bundle = new Bundle();
        bundle.putString("username_id", iduser);
        bundle.putString("username_estudiante", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //ir Ofertas Laborales
    public void goOfertasLaborales(View view) {
        Intent intent = new Intent(this, OfertasLaborales.class);
        Bundle bundle = new Bundle();
        bundle.putString("username_id", iduser);
        bundle.putString("username_estudiante", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void datosAdpter(View view){
        Intent intent = new Intent(this, OfertasLaborales.class);
        Bundle bundle = new Bundle();
        bundle.putString("username_id", iduser);
        bundle.putString("username_estudiante", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }




}