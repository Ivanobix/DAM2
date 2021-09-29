package com.example.ej_intent1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    protected TextView lblUsuario, lblContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        lblUsuario = (TextView)findViewById(R.id.lblUsuario);
        lblContrasena = (TextView)findViewById(R.id.lblContrasena);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("usuario");
            String contrasena = extras.getString("contrasena");

            lblUsuario.setText(nombre);
            lblContrasena.setText(contrasena);
        }
    }
}