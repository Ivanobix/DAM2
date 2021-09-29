package com.example.ej_estilos;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtApellidos;
    EditText txtTelefono;
    EditText txtEmail;
    EditText txtCurso;
    EditText txtNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre2);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos2);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono2);
        txtEmail = (EditText) findViewById(R.id.txtEmail2);
        txtCurso = (EditText) findViewById(R.id.txtCurso2);
        txtNacimiento = (EditText) findViewById(R.id.txtFecha2);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = comprobarDatos();
                if (resultado.equals("")) {
                    String mensaje = txtNombre.getText().toString() + " // " + txtApellidos.getText().toString();
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                    alerta.setTitle("Resumen");
                    alerta.setMessage(mensaje);
                    alerta.setPositiveButton(android.R.string.ok, null);
                    alerta.show();
                } else {
                    String mensaje = "Te faltan los siguientes datos: " + resultado;
                    Snackbar.make(v, mensaje, Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String comprobarDatos() {
        String aDevolver = "";
        if (txtNombre.getText().toString().equals("")) {
            aDevolver = "Nombre ";
        }
        if (txtApellidos.getText().toString().equals("")) {
            aDevolver += "Apellidos ";
        }
        if (txtTelefono.getText().toString().equals("")) {
            aDevolver += "Telefono ";
        }
        if (txtEmail.getText().toString().equals("")) {
            aDevolver += "Email ";
        }
        if (txtNacimiento.getText().toString().equals("")) {
            aDevolver += "Fecha de nacimiento ";
        }
        if (txtCurso.getText().toString().equals("")) {
            aDevolver += "Curso";
        }
        return aDevolver;
    }
}