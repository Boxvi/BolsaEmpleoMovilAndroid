package ec.edu.insta.movilgc1;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
    }

    // ir main activity
    public void goMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //ir a Inicio Admin Busqueda
    public void goInicioAdminBusqueda(View view) {
        Intent intent = new Intent(this, InicioAdminBusqueda.class);
        startActivity(intent);
        finish();
    }

}