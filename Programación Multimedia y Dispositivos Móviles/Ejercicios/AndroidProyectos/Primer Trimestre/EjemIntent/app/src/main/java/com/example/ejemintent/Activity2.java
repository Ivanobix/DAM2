package com.example.ejemintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    protected TextView lblNombre, lblEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //lblNombre = (TextView)findViewById(R.id.lblNombre);
        //lblEdad = (TextView)findViewById(R.id.lblEdad);

        //Recoger valores: Método 1
        /*
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("nombre")) {
                String nombre = intent.getStringExtra("nombre");
                lblNombre.setText(nombre);
            }
            if (intent.hasExtra("edad")) {
                int edad = intent.getIntExtra("edad", 0);
                lblEdad.setText(String.valueOf(edad));
            }
        }

         */


        //Recoger valores: Método 2
        /*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombre");
            int edad = extras.getInt("edad", 0);
            lblNombre.setText(nombre);
            lblEdad.setText(String.valueOf(edad));
        }
         */

        Button btnDos = (Button) findViewById(R.id.btnDos);
        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent();
                intentResult.putExtra("nombre", "juan");
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
    }
}