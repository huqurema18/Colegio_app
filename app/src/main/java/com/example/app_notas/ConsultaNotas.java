package com.example.app_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.app_notas.db.helpers.DBContacto;
import com.example.app_notas.db.models.Nota;

import java.util.ArrayList;

public class ConsultaNotas extends AppCompatActivity {
    Spinner spiner2;
    Button btnConsul;
    TextView resumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_notas);

        spiner2=findViewById(R.id.spinner2);
        btnConsul=findViewById(R.id.btnConsul);
        resumen=findViewById(R.id.txtResumen);

        DBContacto dbContacto = new DBContacto(this);
        Nota cali=new Nota();
        ArrayList<Nota> ll=new ArrayList<>();
        ll=dbContacto.mostrarContactos();

        for(int i=0;i<ll.size();i++){
            System.out.println("Nota: "+i+" "+ll.get(i).getCalificacion());

        }



        //Aqui se consulta en la base de datos y se muestra en el texview con el id txtResumen
        //Se muestra un toast que le dice si pasa o no la materia
        btnConsul.setOnClickListener(view -> {



        });

    }
}