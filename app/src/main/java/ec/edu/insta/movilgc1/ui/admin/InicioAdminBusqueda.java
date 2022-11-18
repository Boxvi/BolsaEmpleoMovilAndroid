package ec.edu.insta.movilgc1.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.ui.MainActivity;

public class InicioAdminBusqueda extends AppCompatActivity {

    TextView txtconsultar_oferta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_admin_busqueda);

        txtconsultar_oferta = findViewById(R.id.txtconsultar_oferta);




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

    //ir a consultar curriculum
    public void goConsultarCurriculum(View view) {
        Intent intent = new Intent(this, ConsultarCurriculum.class);
        startActivity(intent);
        finish();
    }

    //ir a perfil de empresa
    public void goPerfilEmpresa(View view) {
        Intent intent = new Intent(this, PerfilEmpresa.class);
        startActivity(intent);
        finish();
    }

    //ir a razon social
    public void goRazonSocial(View view) {
        Intent intent = new Intent(this, RazonSocial.class);
        startActivity(intent);
        finish();
    }
}