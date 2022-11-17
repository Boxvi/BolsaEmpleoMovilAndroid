package ec.edu.insta.movilgc1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< Updated upstream
=======
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
>>>>>>> Stashed changes
import android.view.View;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

<<<<<<< Updated upstream
=======


    //ir login empleado
    public void goLoginEmpleado(View view) {
        Intent intent = new Intent(this, LoginEmpleado.class);
        startActivity(intent);

    }

    //ir login admin
    public void goLoginAdmin(View view) {
        Intent intent = new Intent(this, LoginAdmin.class);
        startActivity(intent);
    }



>>>>>>> Stashed changes



}