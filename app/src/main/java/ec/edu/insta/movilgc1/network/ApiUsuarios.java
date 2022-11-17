package ec.edu.insta.movilgc1.network;

import java.util.List;

import ec.edu.insta.movilgc1.model.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiUsuarios {
    @GET("usuarios")
    Call<List<Usuario>> getUsuarios();
}
