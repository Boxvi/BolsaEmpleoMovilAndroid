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

    //ir login empleado
    public void goLoginEmpleado(View view) {
        Intent intent = new Intent(this, LoginEmpleado.class);
        startActivity(intent);
        finish();
    }

    //ir login admin
    public void goLoginAdmin(View view) {
        Intent intent = new Intent(this, LoginAdmin.class);
        startActivity(intent);
        finish();
    }






}