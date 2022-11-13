package ec.edu.insta.movilgc1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //ir inicioCV
    public void irInicioCV(View view) {
        Intent intent = new Intent(this, InicioCV.class);
        startActivity(intent);
        finish();
    }

    //if adminBusqueda
    public void irAdminBusqueda(View view) {
        Intent intent = new Intent(this, AdminBusqueda.class);
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
            Toast.makeText(this, "SESION CERRADA CON SATISFACCION", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}