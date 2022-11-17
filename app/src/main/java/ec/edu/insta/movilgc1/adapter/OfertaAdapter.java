package ec.edu.insta.movilgc1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.edu.insta.movilgc1.R;
import ec.edu.insta.movilgc1.model.Oferta;
import ec.edu.insta.movilgc1.model.Usuario;

public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.ViewHolder>{
    private List<Oferta> ofertas;
    private Context context;

    public OfertaAdapter(List<Oferta> ofertas, Context context) {
        this.ofertas = ofertas;
        this.context = context;
    }

    @NonNull
    @Override
    public OfertaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_ofertas_laborales,parent,false);
        return new OfertaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaAdapter.ViewHolder holder, int position) {

        holder.txt_empresa.setText(ofertas.get(position).getEmpresa());
        holder.txt_cargo.setText(ofertas.get(position).getCargo());
        holder.txt_descripcion.setText(ofertas.get(position).getDescripcion());
        holder.txt_area_conocimiento.setText(ofertas.get(position).getArea_conocimiento());
        holder.txt_salario.setText(ofertas.get(position).getSalario());
        holder.txt_jornada.setText(ofertas.get(position).getJornada());
        holder.txt_ciudad.setText(ofertas.get(position).getCiudad());


    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_empresa;
        private TextView txt_cargo;
        private TextView txt_descripcion;
        private TextView txt_area_conocimiento;
        private TextView txt_salario;
        private TextView txt_jornada;
        private TextView txt_ciudad;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_empresa=itemView.findViewById(R.id.txt_empresa);
            txt_cargo=itemView.findViewById(R.id.txt_cargo);
            txt_descripcion=itemView.findViewById(R.id.txt_descripcion);
            txt_area_conocimiento=itemView.findViewById(R.id.txt_area_conocimiento);
            txt_salario=itemView.findViewById(R.id.txt_salario);
            txt_jornada=itemView.findViewById(R.id.txt_jornada);
            txt_ciudad=itemView.findViewById(R.id.txt_ciudad);
        }
    }
}
