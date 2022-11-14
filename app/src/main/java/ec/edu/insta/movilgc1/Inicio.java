package ec.edu.insta.movilgc1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }
    public void siguiente(View view){
        Intent siguiente = new Intent(this,LoginEmpleado.class);
        startActivity(siguiente);

    }
}