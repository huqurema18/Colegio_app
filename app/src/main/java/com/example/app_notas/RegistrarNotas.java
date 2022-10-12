package com.example.app_notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_notas.db.helpers.DBContacto;
import com.example.app_notas.db.models.Nota;
import com.example.app_notas.db.models.NotaEdit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RegistrarNotas extends AppCompatActivity {
    Button btnRegis,btnNuevaMateria;
    Spinner spiner;
    EditText txtIngresar;

    float captura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_notas);

        btnRegis=findViewById(R.id.btnRegis);
        spiner=findViewById(R.id.spinner);
        txtIngresar=findViewById(R.id.txtIngresar);
        btnNuevaMateria=findViewById(R.id.btnanadirmateria);
        NotaEdit anadir=new NotaEdit();
        anadir.setListMaterias(llenarMaterias());

        SharedPreferences preferences=getSharedPreferences("usur",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(preferences.getString("nueva","none")!="none"){
            String aux=preferences.getString("nueva","none");
            anadir.setNodoMaterias(aux);
        }
        System.out.println(anadir.getListMaterias());
        ArrayList<String>spin=new ArrayList<>();
        spin.addAll(anadir.getListMaterias());
        ArrayAdapter<String>adapter=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,anadir.getListMaterias());
        spiner.setAdapter(adapter);


        btnNuevaMateria.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View mview= getLayoutInflater().inflate(R.layout.anadirmateria,null);

            final EditText addMateria =(EditText) mview.findViewById(R.id.txaddmateria);
            final Button btnadd=(Button) mview.findViewById(R.id.btnaddmateria);


            builder.setView(mview);
            AlertDialog dialog=builder.create();
            dialog.show();
            btnadd.setOnClickListener(view1 -> {
                anadir.setNodoMaterias(addMateria.getText().toString());
                editor.putString("nueva",addMateria.getText().toString());
                editor.commit();
                Intent intent=new Intent(this,RegistrarNotas.class);
                startActivity(intent);
                System.out.println(anadir.getListMaterias());
            });

        });

        //Cuando se aoprime el boton se envia la nota a la base de datos
        btnRegis.setOnClickListener(view -> {

            float captura= Float.valueOf(txtIngresar.getText().toString());

            if (captura>5){

                Toast.makeText(this,"Por favor ingrese una nota valida",Toast.LENGTH_LONG).show();
                txtIngresar.setText("");
            }
            else {

                Nota cali = new Nota();
                cali.setMateria("Ingles");
                cali.setCalificacion(Double.parseDouble(txtIngresar.getText().toString()));

                DBContacto dbContacto = new DBContacto(this);
                Long result = dbContacto.insert(cali);
                String msg = result == 0 ? "No se guardo la información" : "Información guardada";
                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
                System.out.println(msg);
                limpiar();
            }


        });

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

    private void limpiar() {
        txtIngresar.setText("");
    }
}