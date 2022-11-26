package ec.edu.insta.movilgc1.adapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.model.estudiante.Estudiante;
import ec.edu.insta.movilgc1.ui.admin.CurriculumVitae;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;
public class ListaConsultarCurriculumAdapter extends RecyclerView.Adapter<ListaConsultarCurriculumAdapter.ViewHolder> {
    private ArrayList<Estudiante> listaConsultarCurriculum;
    private ArrayList<Estudiante> listaConsultarCurriculumOriginal;
    Bundle bundle = new Bundle();
    public ListaConsultarCurriculumAdapter(ArrayList<Estudiante> listaConsultarCurriculum) {
        this.listaConsultarCurriculum = listaConsultarCurriculum;
        listaConsultarCurriculumOriginal = new ArrayList<>();
        listaConsultarCurriculumOriginal.addAll(listaConsultarCurriculum);
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_consultar_curriculum, null, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.view_ID_cv.setText(listaConsultarCurriculum.get(position).getId() + "");
        holder.view_nombres_cv.setText(listaConsultarCurriculum.get(position).getNombres());
        holder.view_direcion_alumno_cv.setText(listaConsultarCurriculum.get(position).getDireccion());
        holder.view_estado_civil_cv.setText(listaConsultarCurriculum.get(position).getEstadoCivil());

        holder.btn_ver_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("id_cv", listaConsultarCurriculum.get(position).getId() + "");

                Intent intent = new Intent(v.getContext(), CurriculumVitae.class);
                intent.putExtras(bundle);
                startActivity(v.getContext(), intent, null);
            }
        });

    }
    public void filtrado(String txt_Search){
        txt_Search = txt_Search.toLowerCase();
        listaConsultarCurriculum.clear();
        if (txt_Search.length() == 0){
            listaConsultarCurriculum.addAll(listaConsultarCurriculumOriginal);
        }else {
            for (Estudiante estudiante : listaConsultarCurriculumOriginal){
                if (estudiante.getNombres().toLowerCase().contains(txt_Search)){
                    listaConsultarCurriculum.add(estudiante);
                }
            }
        }
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return listaConsultarCurriculum.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView view_ID_cv, view_nombres_cv, view_direcion_alumno_cv, view_estado_civil_cv;
        private Button btn_ver_mas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view_ID_cv = itemView.findViewById(R.id.view_ID_cv);
            view_nombres_cv = itemView.findViewById(R.id.view_nombres_cv);
            view_direcion_alumno_cv = itemView.findViewById(R.id.view_direccion_alumno_cv);
            view_estado_civil_cv = itemView.findViewById(R.id.view_estado_civil_cv);
            btn_ver_mas = itemView.findViewById(R.id.btn_ver_mas);
        }
    }
}

