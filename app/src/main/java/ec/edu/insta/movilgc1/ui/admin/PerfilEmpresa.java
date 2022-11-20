package ec.edu.insta.movilgc1.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.adapter.ListaPerfilEmpresaAdapter;
import ec.edu.insta.movilgc1.model.empresa.ModeloEmpresa;
import ec.edu.insta.movilgc1.ui.MainActivity;

public class PerfilEmpresa extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private SearchView txt_Search;
    private ListaPerfilEmpresaAdapter listaPerfilEmpresaAdapter;

    ModeloEmpresa modeloEmpresa = new ModeloEmpresa();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_empresa);

        recyclerView =findViewById(R.id.recycler_view_perfil_empresa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modeloEmpresa.read(PerfilEmpresa.this);

        listaPerfilEmpresaAdapter = new ListaPerfilEmpresaAdapter(modeloEmpresa.read(PerfilEmpresa.this));

        recyclerView.setAdapter(listaPerfilEmpresaAdapter);


        System.out.println(modeloEmpresa.read(PerfilEmpresa.this).get(0).getNombre());

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


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listaPerfilEmpresaAdapter.filtrado(newText);
        return false;
    }
}