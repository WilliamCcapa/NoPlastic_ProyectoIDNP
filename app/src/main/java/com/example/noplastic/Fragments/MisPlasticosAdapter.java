package com.example.noplastic.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noplastic.InformacionPlasticos.ListPlasticos;
import com.example.noplastic.R;
import com.example.noplastic.SegundoActivity;
import com.example.noplastic.db.DbHelper;
import com.example.noplastic.db.DbPlastico;

import java.util.ArrayList;

public class MisPlasticosAdapter extends RecyclerView.Adapter<MisPlasticosAdapter.MisPlasticosViewHolder> {
    ArrayList<ListPlasticos> listaP;
    public MisPlasticosAdapter(ArrayList<ListPlasticos>listaP){
        this.listaP=listaP;
    }
    
    @NonNull
    @Override
    public MisPlasticosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_mis_plasticos, null, false);
        return new MisPlasticosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MisPlasticosViewHolder holder, int position) {

        holder.vnombre.setText(listaP.get(position).getNombre());
        holder.vdescripcion.setText(listaP.get(position).getDescripcion());
        holder.vubicacion.setText(listaP.get(position).getUbicacion());
        holder.vfecha.setText(listaP.get(position).getFecha());
        holder.vcategoria.setText(listaP.get(position).getCategoria());
    }

    @Override
    public int getItemCount() {
        return listaP.size();
    }

    public class MisPlasticosViewHolder extends RecyclerView.ViewHolder {
        TextView vnombre, vdescripcion,vubicacion,vfecha,vcategoria;


        public MisPlasticosViewHolder(@NonNull View itemView) {
            super(itemView);
            final DbPlastico dbContactos = new DbPlastico(itemView.getContext());

            vnombre=itemView.findViewById(R.id.tvMisPlasticos);

            vnombre.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_delete_24,0);
            vnombre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(vnombre.getContext());
                    builder.setMessage("¿Desea eliminar este contacto?")
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    if(dbContactos.eliminarContacto(getAdapterPosition())){
                                        Toast.makeText(itemView.getContext(), "Se elimino ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }

            });
            vdescripcion=itemView.findViewById(R.id.tvDescripcion);
            vubicacion=itemView.findViewById(R.id.tvUbicacion);
            vfecha=itemView.findViewById(R.id.tvFecha);
            vcategoria=itemView.findViewById(R.id.tvCategoria);
        }
    }
    
}
