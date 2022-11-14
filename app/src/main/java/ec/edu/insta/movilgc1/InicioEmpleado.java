package ec.edu.insta.movilgc1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InicioEmpleado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_empleado);
    }
    public void Ofertas(View view){
        Intent siguiente = new Intent(this,OfertasLaborales.class);
        startActivity(siguiente);

    }
    public void Aplicadas(View view){
        Intent siguiente = new Intent(this,OfertasAplicadas.class);
        startActivity(siguiente);

    }
}