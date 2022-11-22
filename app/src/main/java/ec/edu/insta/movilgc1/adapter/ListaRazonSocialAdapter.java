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
import ec.edu.insta.movilgc1.model.razonsocial.RazonSocialC;
import ec.edu.insta.movilgc1.ui.admin.AdminBusqueda;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class ListaRazonSocialAdapter extends RecyclerView.Adapter<ListaRazonSocialAdapter.ViewHolder> {

    private ArrayList<RazonSocialC> listaRazonSocial;
    private ArrayList<RazonSocialC> listaRazonSocialOriginal;

    Bundle bundle = new Bundle();

    public ListaRazonSocialAdapter(ArrayList<RazonSocialC> listaRazonSocial) {
        this.listaRazonSocial = listaRazonSocial;
        listaRazonSocialOriginal = new ArrayList<>();
        listaRazonSocialOriginal.addAll(listaRazonSocial);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_razon_social, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.view_rs_id.setText(listaRazonSocial.get(position).getId() + "");
        holder.view_rs_nombre.setText(listaRazonSocial.get(position).getEmpresa());
        holder.view_rs_salario.setText(listaRazonSocial.get(position).getSalario());
        holder.view_rs_cargo.setText(listaRazonSocial.get(position).getCargo());
        holder.view_rs_ubicacion.setText(listaRazonSocial.get(position).getCiudad());

        holder.btn_ver_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("id_rs", listaRazonSocial.get(position).getId() + "");

                Intent intent = new Intent(v.getContext(), AdminBusqueda.class);
                intent.putExtras(bundle);
                startActivity(v.getContext(), intent, null);
            }
        });



    }
    public void filtrado(String txt_Search){
        txt_Search = txt_Search.toLowerCase();
        listaRazonSocial.clear();
        if(txt_Search.length() == 0){
            listaRazonSocial.addAll(listaRazonSocialOriginal);
        }else{
            for(RazonSocialC razonSocialC : listaRazonSocialOriginal){
                if(razonSocialC.getEmpresa().toLowerCase().contains(txt_Search)){
                    listaRazonSocial.add(razonSocialC);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return listaRazonSocial.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView view_rs_id, view_rs_nombre, view_rs_salario, view_rs_cargo, view_rs_ubicacion;
        Button btn_ver_mas;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view_rs_id = itemView.findViewById(R.id.view_rs_id);
            view_rs_nombre = itemView.findViewById(R.id.view_rs_nombre);
            view_rs_salario = itemView.findViewById(R.id.view_rs_salario);
            view_rs_cargo = itemView.findViewById(R.id.view_rs_cargo);
            view_rs_ubicacion = itemView.findViewById(R.id.view_rs_ubicacion);

            btn_ver_mas = itemView.findViewById(R.id.btn_ver_mas);


        }
    }

}

