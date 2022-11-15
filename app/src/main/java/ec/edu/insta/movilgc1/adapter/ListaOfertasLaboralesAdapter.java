package ec.edu.insta.movilgc1.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ec.edu.insta.movilgc1.R;

public class ListaOfertasLaboralesAdapter extends RecyclerView.Adapter<ListaOfertasLaboralesAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_Empresa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_Empresa= itemView.findViewById(R.id.txt_Empresa);

        }
    }
}
