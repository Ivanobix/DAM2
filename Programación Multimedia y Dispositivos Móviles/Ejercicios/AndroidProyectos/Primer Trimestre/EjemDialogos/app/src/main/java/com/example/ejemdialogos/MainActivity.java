package com.example.ejemdialogos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    protected EditText txtNombre;
    protected TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        lblResultado = (TextView) findViewById(R.id.lblResultado);

        Button btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString().trim();
                if (nombre.equals("")) {
                    String mensaje = "Debe introducir su nombre.";
                    //Toast
                    //Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();

                    //AlertDialog
                    /*
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                    alerta.setTitle("Atención");
                    alerta.setMessage(mensaje);
                    alerta.setPositiveButton(android.R.string.ok, null);
                    alerta.show();
                    */

                    //Snackbar
                    Snackbar.make(v, mensaje, Snackbar.LENGTH_SHORT).show();
                } else {
                    lblResultado.setText("Bienvenido, " + nombre);
                }
            }
        });
    }
}