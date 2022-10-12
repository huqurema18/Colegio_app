package com.example.app_notas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_notas.R;
//import com.example.app_notas.VerActivity;
import com.example.app_notas.db.models.Nota;

import java.util.ArrayList;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotasViewHolder> {

    ArrayList<Nota> listaNotas;
    ArrayList<Nota> listaOriginal;

    public ListaNotasAdapter(ArrayList<Nota> listaNotas) {
        this.listaNotas = listaNotas;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaNotas);
    }

    @NonNull
    @Override
    public NotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diseno_notas, null, false);
        return new NotasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotasViewHolder holder, int position) {
        holder.viewNotas.setText( listaNotas.get(position).getCalificacion()+"");
        holder.idtext.setText(listaNotas.get(position).getId()+"");
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public class NotasViewHolder extends RecyclerView.ViewHolder {

        TextView  viewNotas,idtext;

        public NotasViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNotas = itemView.findViewById(R.id.txtNota);
            idtext=itemView.findViewById(R.id.textid);


        }
    }

}
