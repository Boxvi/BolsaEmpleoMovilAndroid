package ec.edu.insta.movilgc1.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
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
import ec.edu.insta.movilgc1.model.ofertas.Oferta;
import ec.edu.insta.movilgc1.ui.employe.DatosOfertasLaborales;

import java.util.ArrayList;

public class ListaOfertasLaboralesAdapter extends RecyclerView.Adapter<ListaOfertasLaboralesAdapter.ViewHolder> {

    private ArrayList<Oferta> ofertaArrayList;
    private ArrayList<Oferta> ofertaArrayListOriginal;
    private Context context;
    private Intent intent;
    Bundle bundle = new Bundle();


    public ListaOfertasLaboralesAdapter(ArrayList<Oferta> ofertaArrayList) {
        this.ofertaArrayList = ofertaArrayList;
        this.context = context;
        ofertaArrayListOriginal=new ArrayList<>();
        ofertaArrayListOriginal.addAll(ofertaArrayList);
    }

    @NonNull
    @Override
    public ListaOfertasLaboralesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_ofertas_laborales,parent,false);
        return new ListaOfertasLaboralesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {




        holder.txt_id.setText(ofertaArrayList.get(position).getId()+40+"");
        holder.txt_cargo.setText(ofertaArrayList.get(position).getCargo());
        holder.txt_descripcion.setText(ofertaArrayList.get(position).getDescripcion());
        holder.txt_area_conocimiento.setText(ofertaArrayList.get(position).getArea_conocimiento());
        holder.txt_salario.setText(ofertaArrayList.get(position).getSalario());

        holder.btn_verMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("idOferta", ofertaArrayList.get(position).getId() + 40+"");
                Intent intent = new Intent(v.getContext(), DatosOfertasLaborales.class);
                intent.putExtras(bundle);
                startActivity(v.getContext(), intent, bundle);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ofertaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_id;
        private TextView txt_cargo;
        private TextView txt_descripcion;
        private TextView txt_area_conocimiento;
        private TextView txt_salario;
        private Button btn_verMas;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id=itemView.findViewById(R.id.view_id);
            txt_cargo=itemView.findViewById(R.id.view_cargo);
            txt_descripcion=itemView.findViewById(R.id.txt_descripcion);
            txt_area_conocimiento=itemView.findViewById(R.id.txt_area_conocimiento);
            txt_salario=itemView.findViewById(R.id.txt_salario);
            btn_verMas=itemView.findViewById(R.id.btn_verMas);

        }
    }
}