package com.example.ej_almacenamientointerno;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText txtContenidoArchivo;
    private Button btnGuardar, btnRecuperar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtContenidoArchivo = (EditText) findViewById(R.id.txtContenidoArchivo);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnRecuperar = (Button) findViewById(R.id.btnRecuperar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnGuardar.setOnClickListener(v -> {
            String texto = txtContenidoArchivo.getText().toString().trim();
            if (!texto.equals("")) {
                FileOutputStream fos;
                String nombreArchivo = "miArchivo.txt";
                try {
                    fos = openFileOutput(nombreArchivo, Context.MODE_PRIVATE);
                    fos.write(texto.getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "Archivo guardado: " + nombreArchivo, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "No se ha podido guardar: " + nombreArchivo, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "El texto está vacío.", Toast.LENGTH_SHORT).show();
            }

        });

        btnEliminar.setOnClickListener(v -> {
            txtContenidoArchivo.setText("");
            Toast.makeText(MainActivity.this, "Texto borrado.", Toast.LENGTH_SHORT).show();
        });

        btnRecuperar.setOnClickListener(v -> {
            FileInputStream fis;
            String nombreArchivo = "miArchivo.txt";
            try {
                fis = openFileInput(nombreArchivo);
                byte[] buffer = new byte[1024];
                StringBuilder texto = new StringBuilder();
                while ((fis.read(buffer)) != -1) {
                    texto.append(new String(buffer));
                }
                txtContenidoArchivo.setText(texto);
                Toast.makeText(MainActivity.this, "Archivo recuperado: " + nombreArchivo, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "No se ha podido recuperar: " + nombreArchivo, Toast.LENGTH_SHORT).show();
            }

        });
    }
}