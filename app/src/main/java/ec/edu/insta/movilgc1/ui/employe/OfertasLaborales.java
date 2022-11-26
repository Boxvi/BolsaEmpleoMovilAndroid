package ec.edu.insta.movilgc1.ui.employe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.adapter.ListaOfertasLaboralesAdapter;
import ec.edu.insta.movilgc1.model.ofertas.ModeloOferta;
import ec.edu.insta.movilgc1.ui.MainActivity;

public class OfertasLaborales extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private ListaOfertasLaboralesAdapter listaOfertasLaboralesAdapter;
    private RequestQueue requestQueue;
    ModeloOferta modeloOferta = new ModeloOferta();
    private String view_username, view_id_username, usuario, idusuario;
    String id_estudiante;
    private SearchView txt_Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas_laborales);
        usuario = InicioBuscoEmpleo.user;
        idusuario = InicioBuscoEmpleo.iduser;
        recyclerView = findViewById(R.id.recycler_view_ofertas_laborales);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        System.out.println(modeloOferta.read(OfertasLaborales.this));
        listaOfertasLaboralesAdapter = new ListaOfertasLaboralesAdapter(modeloOferta.read(OfertasLaborales.this));
        recyclerView.setAdapter(listaOfertasLaboralesAdapter);
    }
    public void goInicioBuscoEmpleo(View view) {
        Intent intent = new Intent(this, InicioBuscoEmpleo.class);
        Bundle bundle = new Bundle();
        bundle.putString("username_id", InicioBuscoEmpleo.iduser);
        bundle.putString("username_estudiante", InicioBuscoEmpleo.user);
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
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}