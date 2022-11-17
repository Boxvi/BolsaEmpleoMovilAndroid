package ec.edu.insta.movilgc1.adapter;

import android.annotation.SuppressLint;
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
import ec.edu.insta.movilgc1.model.empresa.Empresa;
import ec.edu.insta.movilgc1.ui.admin.DatosPerfilEmpresa;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class ListaPerfilEmpresaAdapter extends RecyclerView.Adapter<ListaPerfilEmpresaAdapter.ViewHolder> {

    private ArrayList<Empresa> listaPerfilEmpresa;
    private ArrayList<Empresa> listaPerfilEmpresaOriginal;

    Bundle bundle = new Bundle();

    public ListaPerfilEmpresaAdapter(ArrayList<Empresa> listaPerfilEmpresa) {
        this.listaPerfilEmpresa = listaPerfilEmpresa;

        listaPerfilEmpresaOriginal = new ArrayList<>();
        listaPerfilEmpresaOriginal.addAll(listaPerfilEmpresa);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_perfil_empresa, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_id_empresa.setText(listaPerfilEmpresa.get(position).getId() + "");

        holder.txt_nombre_empresa.setText(listaPerfilEmpresa.get(position).getNombre());

        holder.txt_tipo_empresa.setText(listaPerfilEmpresa.get(position).getTipoEmpresa());
        holder.txt_departamento.setText(listaPerfilEmpresa.get(position).getDepartamento());
        holder.txt_ciudad.setText(listaPerfilEmpresa.get(position).getCiudad());

        holder.btn_mas_detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("id_empresa", listaPerfilEmpresa.get(position).getId() + "");

                Intent intent = new Intent(v.getContext(), DatosPerfilEmpresa.class);
                intent.putExtras(bundle);
                startActivity(v.getContext(), intent, bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPerfilEmpresa.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_id_empresa, txt_nombre_empresa, txt_tipo_empresa, txt_departamento, txt_ciudad;
        private Button btn_mas_detalles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_id_empresa = itemView.findViewById(R.id.txt_id_empresa);
            txt_nombre_empresa = itemView.findViewById(R.id.txt_nombre_empresa);
            txt_tipo_empresa = itemView.findViewById(R.id.view_tipo_empresa);
            txt_departamento = itemView.findViewById(R.id.txt_departamento);
            txt_ciudad = itemView.findViewById(R.id.txt_ciudad);

            btn_mas_detalles = itemView.findViewById(R.id.btn_mas_detalles);

        }
    }
}
