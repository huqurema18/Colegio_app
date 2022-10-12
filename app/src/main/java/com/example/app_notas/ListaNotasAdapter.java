package com.example.app_notas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import  com.example.app_notas.ConsultaNotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_notas.R;
//import com.example.app_notas.VerActivity;
import com.example.app_notas.db.models.Nota;
import com.example.app_notas.db.models.NotaEdit;

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

            itemView.setOnClickListener(view -> {
                Context context =view.getContext();
                NotaEdit h=new NotaEdit();
                h.setId(listaNotas.get(getAdapterPosition()).getId());
                h.setNota(listaNotas.get(getAdapterPosition()).getCalificacion());
                Intent intent = new Intent(context,editaNota.class);
                context.startActivity(intent);

                Toast.makeText(context,"hfvjksfvb"+listaNotas.get(getAdapterPosition()).getCalificacion(),Toast.LENGTH_SHORT).show();






            });

        }
    }



}
