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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.adapter.ListaRazonSocialAdapter;
import ec.edu.insta.movilgc1.model.razonsocial.Ofertas;
import ec.edu.insta.movilgc1.ui.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class RazonSocial extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaRazonSocialAdapter listaRazonSocialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razon_social);

        verOfertas();
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

    public void verOfertas() {
        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/ofertas";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        ArrayList<Ofertas> listaOfertas = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Ofertas ofertas = new Ofertas();


                for (int i = 0; i < response.length(); i++) {
                    try {
                        ofertas.setCiudad(response.getJSONObject(i).getString("ciudad"));

                        ofertas.setId(response.getJSONObject(i).getInt("id"));
                        ofertas.setEmpresa(response.getJSONObject(i).getString("empresa"));
                        ofertas.setSalario(response.getJSONObject(i).getString("salario"));
                        ofertas.setCargo(response.getJSONObject(i).getString("cargo"));
                        ofertas.setCiudad(response.getJSONObject(i).getString("ciudad"));

                        listaOfertas.add(ofertas);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                recyclerView = findViewById(R.id.recycler_view_razon_social);
                recyclerView.setLayoutManager(new LinearLayoutManager(RazonSocial.this));

                listaRazonSocialAdapter = new ListaRazonSocialAdapter(listaOfertas);
                recyclerView.setAdapter(listaRazonSocialAdapter);

            }
        }, error -> {
            System.out.println("Error: " + error.getMessage());
        });
        requestQueue.add(jsonArrayRequest);

    }


}