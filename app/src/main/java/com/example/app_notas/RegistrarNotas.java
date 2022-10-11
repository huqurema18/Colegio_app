package com.example.app_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrarNotas extends AppCompatActivity {
    Button btnRegis;
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


        //Cuando se aoprime el boton se envia la nota a la base de datos
        btnRegis.setOnClickListener(view -> {

            float captura= Float.valueOf(txtIngresar.getText().toString());

            if (captura>5){

                Toast.makeText(this,"Por favor ingrese una nota valida",Toast.LENGTH_LONG).show();
                txtIngresar.setText("");
            }

            Nota cali = new Nota();
            cali.setMateria("Ingles");
            cali.setCalificacion(Double.parseDouble(notish.getText().toString()));

            DBContacto dbContacto = new DBContacto(this);
            Long result = dbContacto.insert(cali);
            String msg = result == 0 ? "No se guardo la información" : "Información guardada";
            System.out.println(msg);
            limpiar();

        });

    }
    private void limpiar() {
        notish.setText("");
    }
}