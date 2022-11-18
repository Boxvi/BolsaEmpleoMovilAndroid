package ec.edu.insta.movilgc1.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.adapter.ListaConsultarCurriculumAdapter;
import ec.edu.insta.movilgc1.model.estudiante.ModeloEstudiante;
import ec.edu.insta.movilgc1.ui.MainActivity;

public class ConsultarCurriculum extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaConsultarCurriculumAdapter listaConsultarCurriculumAdapter;
    ModeloEstudiante modeloEstudiante = new ModeloEstudiante();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_curriculum);

        recyclerView = findViewById(R.id.recycler_view_consultar_curriculum);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modeloEstudiante.read(ConsultarCurriculum.this);

        listaConsultarCurriculumAdapter = new ListaConsultarCurriculumAdapter(modeloEstudiante.read(ConsultarCurriculum.this));

        recyclerView.setAdapter(listaConsultarCurriculumAdapter);


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

    //ir a inicio admin busqeuda
    public void goInicioAdminBusqueda(View view) {
        Intent intent = new Intent(this, InicioAdminBusqueda.class);
        startActivity(intent);
        finish();
    }


}