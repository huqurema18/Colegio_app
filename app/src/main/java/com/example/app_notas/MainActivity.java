package com.example.app_notas;

import androidx.appcompat.app.AppCompatActivity;

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
    }

}