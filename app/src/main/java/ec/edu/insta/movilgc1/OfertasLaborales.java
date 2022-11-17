package ec.edu.insta.movilgc1;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< Updated upstream
=======
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
>>>>>>> Stashed changes

import android.os.Bundle;

import java.util.List;

import ec.edu.insta.movilgc1.adapter.OfertaAdapter;
import ec.edu.insta.movilgc1.model.Oferta;
import ec.edu.insta.movilgc1.network.ApiClient;
import ec.edu.insta.movilgc1.network.ApiOfertas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfertasLaborales extends AppCompatActivity {

    private List<Oferta>ofertas;
    private RecyclerView recyclerView;
    private OfertaAdapter ofertaAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas_laborales);

        recyclerView=findViewById(R.id.rv_usuario);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        showOfertas();
    }
    public void showOfertas(){
        Call<List<Oferta>> call = ApiClient.getClient().create(ApiOfertas.class).getOfertas();
        call.enqueue(new Callback<List<Oferta>>() {
            @Override
            public void onResponse(Call<List<Oferta>> call, Response<List<Oferta>> response) {
                if (response.isSuccessful()){
                    ofertas=response.body();
                    ofertaAdapter=new OfertaAdapter(ofertas,getApplicationContext());
                    recyclerView.setAdapter(ofertaAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Oferta>> call, Throwable t) {

                Toast.makeText(OfertasLaborales.this,"Error de conexion",Toast.LENGTH_SHORT).show();
            }
        });
    }
}