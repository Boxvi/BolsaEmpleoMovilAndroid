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
import ec.edu.insta.movilgc1.model.Usuario;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {

    private List<Usuario>usuarios;
    private Context context;

    public UsuarioAdapter(List<Usuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_ofertas_laborales,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       // holder.txt_Usuario.setText(usuarios.get(position).getUsername());
      //  holder.txt_Email.setText(usuarios.get(position).getEmail());
      //  holder.txt_Telefono.setText(usuarios.get(position).getTelefono());
      //  holder.txt_Rol.setText(usuarios.get(position).getRol());


    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      //  private TextView txt_Usuario;
      // private TextView txt_Email;
      //  private TextView txt_Rol;
      //  private TextView txt_Telefono;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // txt_Usuario=itemView.findViewById(R.id.txt_Usuario);
           // txt_Email=itemView.findViewById(R.id.txt_Email);
           // txt_Rol=itemView.findViewById(R.id.txt_Rol);
            //txt_Telefono=itemView.findViewById(R.id.txt_Telefono);
        }
    }
}
