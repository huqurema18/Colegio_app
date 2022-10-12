package com.example.app_notas;

import android.content.Context;
import android.content.DialogInterface;
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
import com.example.app_notas.db.helpers.DBContacto;
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

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
                View mview = layoutInflaterAndroid.inflate(R.layout.edit_nota, null);
                final EditText EditNota =(EditText) mview.findViewById(R.id.editTxNota);
                final TextView textid =(TextView) mview.findViewById(R.id.textidextract);
                final Button EditInflatDelete=(Button) mview.findViewById(R.id.btnDeleteEditNota);
                final Button EditInflatEdit=(Button) mview.findViewById(R.id.btnEditNota);

                EditInflatDelete.setOnClickListener(view1 -> {
                    System.out.println("caremonda");
                });
                EditInflatEdit.setOnClickListener(view1 -> {
                    Nota nota1=new Nota();
                    DBContacto dbContacto=new DBContacto(context);
                    nota1.setId(listaNotas.get(getAdapterPosition()).getId());
                    nota1.setMateria(listaNotas.get(getAdapterPosition()).getMateria());
                    nota1.setCalificacion(Double.parseDouble(EditNota.getText().toString()));
                    Boolean resultado=dbContacto.editNota(nota1);
                    if(resultado){
                        Toast.makeText(view.getContext(),"Nota Editada Exitosamente",Toast.LENGTH_SHORT).show();
                    }
                    System.out.println(resultado);
                });
                EditInflatDelete.setOnClickListener(view1 -> {
                    AlertDialog.Builder builder1= new AlertDialog.Builder(context);
                    builder1.setMessage("Desea elminiar este contacto?").setPositiveButton("SI",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DBContacto dbContacto=new DBContacto(context);
                                    if(dbContacto.eliminarNota(listaNotas.get(getAdapterPosition()).getId())){
                                        Toast.makeText(view.getContext(),"Eliminado Exitosamente",Toast.LENGTH_SHORT).show();
                                    }
                                    Intent intent =new Intent(view1.getContext(),ConsultaNotas.class);
                                    context.startActivity(intent);

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent =new Intent(view1.getContext(),ConsultaNotas.class);
                                    context.startActivity(intent);
                                }
                            }).show();
                });

                //EditNota.setText(listaNotas.get(getAdapterPosition()).getCalificacion()+"");
                textid.setText(listaNotas.get(getAdapterPosition()).getId()+"");

                Nota nota=new Nota();
                builder.setView(mview);
                AlertDialog dialog=builder.create();
                dialog.show();
                DBContacto dbContacto=new DBContacto(context);
                nota=dbContacto.verContactos(listaNotas.get(getAdapterPosition()).getId());
                if(nota!=null){
                    EditNota.setText(nota.getCalificacion()+"");

                }


            });

        }


    }



}
