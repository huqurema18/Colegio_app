package com.example.app_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnConsultar,btnRegistrar;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsultar=findViewById(R.id.btnConsultar);
        btnRegistrar=findViewById(R.id.btnRegistrar);


        btnRegistrar.setOnClickListener(view -> {
            Intent intent=new Intent(this,RegistrarNotas.class);
            startActivity(intent);
        });

        btnConsultar.setOnClickListener(view -> {
            Intent intent=new Intent(this,ConsultaNotas.class);
            startActivity(intent);
        });
    }

}