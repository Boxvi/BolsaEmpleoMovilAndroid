package ec.edu.insta.movilgc1.ui.admin;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ec.edu.insta.movilgc1.R;

public class CurriculumVitae extends AppCompatActivity {

    private TextView view_cv_ID;
    private ImageView btn_regresar_pefil_empresa_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_vitae);

        view_cv_ID = findViewById(R.id.view_cv_ID);
        Bundle bundle = getIntent().getExtras();
        view_cv_ID.setText(bundle.getString("id_cv"));

        btn_regresar_pefil_empresa_cv = findViewById(R.id.btn_regresar_pefil_empresa_cv);
        btn_regresar_pefil_empresa_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extracted();
            }
        });

    }

    private void extracted() {
        Intent intent = new Intent(CurriculumVitae.this, ConsultarCurriculum.class);
        startActivity(intent);
        finish();
    }
}