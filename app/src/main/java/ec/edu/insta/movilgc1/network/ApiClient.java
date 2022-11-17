package ec.edu.insta.movilgc1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
