package com.example.ejemparcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    protected TextView lblTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        lblTitulo = (TextView)findViewById(R.id.lblTitulo);

        Intent data = getIntent();
        if(data!=null && data.hasExtra("alumno")) {
            Alumno al = data.getParcelableExtra("alumno");
            String texto = "Alumno: " + al.getNombre() + al.getApellido() + String.valueOf(al.getEdad());
            lblTitulo.setText(texto);
        }
        else {
            Toast.makeText(Activity2.this, "No hay datos.", Toast.LENGTH_SHORT).show();
        }
    }
}