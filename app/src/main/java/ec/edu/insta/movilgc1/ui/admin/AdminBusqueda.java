package ec.edu.insta.movilgc1.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import ec.edu.insta.movilgc1.R;

public class AdminBusqueda extends AppCompatActivity {

    private ImageView btn_regresar_inicio_admin_busqueda;

    private TextView view_rs_id, view_descripcion, view_cargo, view_area_conocimiento, view_salario, view_jornada, view_requisitos_academicos, view_experiencia, view_ubicacion, view_fecha_inicio_convocatoria, view_fecha_fin_convocatoria, view_nombre_empresa, view_ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_busqueda);

        Bundle bundle = getIntent().getExtras();

        view_rs_id = findViewById(R.id.view_rs_id);
        view_rs_id.setText(bundle.getString("id_rs"));

        System.out.println("id_rs: " + bundle.getString("id_rs"));

        System.out.println(view_rs_id.getText().toString());

        verMasDetallesOFertas(view_rs_id.getText().toString());


        btn_regresar_inicio_admin_busqueda = findViewById(R.id.btn_regresar_inicio_admin_busqueda);
        btn_regresar_inicio_admin_busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminBusqueda.this, RazonSocial.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void verMasDetallesOFertas(String id) {

        String URL = "http://springgc1-env.eba-mf2fnuvf.us-east-1.elasticbeanstalk.com/ofertas/" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, response -> {
            try {
                view_cargo = findViewById(R.id.view_cargo);
                view_descripcion = findViewById(R.id.view_descripcion);
                view_area_conocimiento = findViewById(R.id.view_area_conocimiento);
                view_salario = findViewById(R.id.view_salario);
                view_jornada = findViewById(R.id.view_jornada);
                view_requisitos_academicos = findViewById(R.id.view_requisitos_academicos);
                view_experiencia = findViewById(R.id.view_experiencia);
                view_ubicacion = findViewById(R.id.view_ubicacion);
                view_fecha_inicio_convocatoria = findViewById(R.id.view_fecha_inicio_convocatoria);
                view_fecha_fin_convocatoria = findViewById(R.id.view_fecha_fin_convocatoria);
                view_nombre_empresa = findViewById(R.id.view_nombre_empresa);
                view_ciudad = findViewById(R.id.view_ciudad);

                view_cargo.setText(response.getString("cargo"));
                view_descripcion.setText(response.getString("descripcion"));
                view_area_conocimiento.setText(response.getString("area_conocimiento"));
                view_salario.setText(response.getString("salario"));
                view_jornada.setText(response.getString("jornada"));
                view_requisitos_academicos.setText(response.getString("requisitos_academicos"));
                view_experiencia.setText(response.getString("experiencia"));
                view_ubicacion.setText(response.getString("ubicacion"));
                view_fecha_inicio_convocatoria.setText(response.getString("fecha_inicio"));
                view_fecha_fin_convocatoria.setText(response.getString("fecha_fin"));
                view_nombre_empresa.setText(response.getJSONObject("empresa").getString("nombre"));
                view_ciudad.setText(response.getJSONObject("empresa").getJSONObject("ciudad"). getString("nombre"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> {
            System.out.println("error: " + error);
        });
        requestQueue.add(jsonObjectRequest);

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