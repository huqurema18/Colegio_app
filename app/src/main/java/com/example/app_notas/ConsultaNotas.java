package com.example.app_notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.app_notas.db.helpers.DBContacto;
import com.example.app_notas.db.models.Nota;
import com.example.app_notas.db.models.NotaEdit;

import java.util.ArrayList;

public class ConsultaNotas extends AppCompatActivity {
    Spinner spiner2;
    Button btnConsul;
    Button paraeditar;
    TextView resumen,hintid,notaedit;
    RecyclerView Recycler;
    ListaNotasAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_notas);

        paraeditar=findViewById(R.id.btnparaeditar);
        notaedit=findViewById(R.id.txnotaaeditar);
        hintid=findViewById(R.id.txhintid);
        spiner2=findViewById(R.id.spinner2);
        btnConsul=findViewById(R.id.btnConsul);
        resumen=findViewById(R.id.txtResumen);
        Recycler=findViewById(R.id.Recycler);


        Recycler.setLayoutManager(new LinearLayoutManager(this));
        
        DBContacto dbContacto = new DBContacto(this);
        Nota cali=new Nota();

        ArrayList<Nota> ll=new ArrayList<>();
        ll=dbContacto.mostrarContactos();


        double suma=0;
        int divi=0;
        for(int i=0;i<ll.size();i++){
            System.out.println("Nota: "+i+" "+ll.get(i).getCalificacion());
            suma+=ll.get(i).getCalificacion();
            divi++;
        }
        double respuesta=suma/divi;
        if(respuesta>=3){
            resumen.setText("Vas pasando, tu promedio es: "+respuesta);
        }else{
            resumen.setText("Va perdiendo, tu promedio es: "+respuesta);
        }

        //Aqui se consulta en la base de datos y se muestra en el texview con el id txtResumen
        //Se muestra un toast que le dice si pasa o no la materia
        btnConsul.setOnClickListener(view -> {

            adapter = new ListaNotasAdapter(dbContacto.mostrarContactos());
            Recycler.setAdapter(adapter);


        });
        NotaEdit j=new NotaEdit();
        notaedit.setText(j.getNota()+"");


        paraeditar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View mview= getLayoutInflater().inflate(R.layout.edit_nota,null);

            final EditText EditNota =(EditText) mview.findViewById(R.id.editTxNota);
            final Button EditInflatDelete=(Button) mview.findViewById(R.id.btnDeleteEditNota);
            final Button EditInflatEdit=(Button) mview.findViewById(R.id.btnEditNota);
            NotaEdit jg=new NotaEdit();
            EditNota.setText(jg.getNota()+"");
            builder.setView(mview);
            AlertDialog dialog=builder.create();
            dialog.show();
        });

    }
}