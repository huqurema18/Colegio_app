package com.example.app_notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.app_notas.db.helpers.DBContacto;
import com.example.app_notas.db.models.Nota;
import com.example.app_notas.db.models.NotaEdit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ConsultaNotas extends AppCompatActivity {
    Spinner spiner2;
    Button btnConsul;
    //Button paracalcular;
    TextView resumen,notaacalcular;
    RecyclerView Recycler;
    ListaNotasAdapter adapter;
    String materia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_notas);

        //paracalcular=findViewById(R.id.btnparacalcular);
        notaacalcular=findViewById(R.id.txnotaacalcular);
        spiner2=findViewById(R.id.spinner2);
        btnConsul=findViewById(R.id.btnConsul);
        resumen=findViewById(R.id.txtResumen);
        Recycler=findViewById(R.id.Recycler);


        Recycler.setLayoutManager(new LinearLayoutManager(this));
        
        DBContacto dbContacto = new DBContacto(this);
        Nota cali=new Nota();




        /*paracalcular.setOnClickListener(view -> {
            ArrayList<Nota> ll=new ArrayList<>();
            ll=dbContacto.mostrarNotaConMateria(materia);
            double suma=0;
            int divi=0;
            for(int i=0;i<ll.size();i++){
                System.out.println("Nota: "+i+" "+ll.get(i).getCalificacion());
                suma+=ll.get(i).getCalificacion();
                divi++;
            }
            double respuesta=suma/divi;
            respuesta= Double.parseDouble(String.format("%.2f", respuesta));

            notaacalcular.setText(respuesta+"");
            if(respuesta>=3){
                resumen.setText("Vas pasando, tu promedio es: "+respuesta);
            }else{
                resumen.setText("Va perdiendo, tu promedio es: "+respuesta);
            }
        });*/

        //Aqui se consulta en la base de datos y se muestra en el texview con el id txtResumen
        //Se muestra un toast que le dice si pasa o no la materia
        btnConsul.setOnClickListener(view -> {
            adapter = new ListaNotasAdapter(dbContacto.mostrarNotaConMateria(materia));
            Recycler.setAdapter(adapter);

            calcular();


        });


        /*
        paraeditar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View mview= getLayoutInflater().inflate(R.layout.edit_nota,null);

            final EditText EditNota =(EditText) mview.findViewById(R.id.editTxNota);
            final Button EditInflatDelete=(Button) mview.findViewById(R.id.btnDeleteEditNota);
            final Button EditInflatEdit=(Button) mview.findViewById(R.id.btnEditNota);

            builder.setView(mview);
            AlertDialog dialog=builder.create();
            dialog.show();
            NotaEdit jg=new NotaEdit();
            EditNota.setText(jg.getNota()+"");
        });*/
        NotaEdit anadir=new NotaEdit();
        anadir.setListMaterias(llenarMaterias());
        System.out.println(anadir.getListMaterias());
        ArrayList<String>spin=new ArrayList<>();
        spin.addAll(anadir.getListMaterias());
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,anadir.getListMaterias());
        spiner2.setAdapter(adapter2);

        spiner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                materia= (String) adapterView.getSelectedItem();
                adapter = new ListaNotasAdapter(dbContacto.mostrarNotaConMateria(materia));
                Recycler.setAdapter(adapter);
                calcular();
                System.out.println("seleccionado es: "+materia);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void calcular() {
        DBContacto dbContacto3=new DBContacto(this);
        ArrayList<Nota> ll=new ArrayList<>();
        ll=dbContacto3.mostrarNotaConMateria(materia);
        double suma=0;
        int divi=0;
        for(int i=0;i<ll.size();i++){
            System.out.println("Nota: "+i+" "+ll.get(i).getCalificacion());
            suma+=ll.get(i).getCalificacion();
            divi++;
        }
        double respuesta=suma/divi;
        respuesta= Double.parseDouble(String.format("%.2f", respuesta));

        notaacalcular.setText(respuesta+"");
        if(respuesta>=3){
            resumen.setText("Vas pasando, tu promedio es: "+respuesta);
        }else{
            resumen.setText("Va perdiendo, tu promedio es: "+respuesta);
        }
    }

    private ArrayList<String> llenarMaterias() {
        DBContacto dbContacto2=new DBContacto(this);
        ArrayList<Nota> mate=new ArrayList<>();
        ArrayList<String> matefinal=new ArrayList<>();


        mate=dbContacto2.mostrarContactos();
        for(int i=0;i<mate.size();i++){
            matefinal.add(mate.get(i).getMateria());
        };

        Set<String> miHashSet = new HashSet<>();
        miHashSet.addAll(matefinal);
        matefinal.clear();
        matefinal.addAll(miHashSet);

        return matefinal;
    }
}