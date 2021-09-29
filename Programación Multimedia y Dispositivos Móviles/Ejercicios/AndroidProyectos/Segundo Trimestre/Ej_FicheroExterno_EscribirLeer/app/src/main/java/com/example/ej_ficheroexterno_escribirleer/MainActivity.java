package com.example.ej_ficheroexterno_escribirleer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE = 1;
    private static final int REQUEST_READ = 2;
    private EditText txtContenidoArchivo;
    private Button btnGuardar, btnRecuperar, btnEliminar;

    //Este ejercicio guarda y carga un archivo en el almacenamiento publico, en la carpeta descargas,
    // pero ojo, se guarda en sdcard y en storage (no se si está bien)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtContenidoArchivo = (EditText) findViewById(R.id.txtContenidoArchivo);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnRecuperar = (Button) findViewById(R.id.btnRecuperar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnGuardar.setOnClickListener(v -> {
            // Comprobamos si no disponemos del permiso: READ_CONTACTS
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Comprobamos si ya se había solicitado el permiso
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE);
            } else {
                // Ya disponíamos del permiso, no es necesario solicitarlo
                guardar();
            }
        });

        btnEliminar.setOnClickListener(v -> {
            txtContenidoArchivo.setText("");
            Toast.makeText(MainActivity.this, "Texto borrado.", Toast.LENGTH_SHORT).show();
        });

        btnRecuperar.setOnClickListener(v -> {
            // Comprobamos si no disponemos del permiso: READ_CONTACTS
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Comprobamos si ya se había solicitado el permiso
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ);
            } else {
                // Ya disponíamos del permiso, no es necesario solicitarlo
                recuperar();
            }


        });
    }

    private void guardar() {
        String texto = txtContenidoArchivo.getText().toString().trim();
        if (!texto.equals("")) {
            String nombreArchivo = "miArchivo.txt";
            try {
                String ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                File f = new File(ruta, nombreArchivo);
                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
                fout.write(texto);
                fout.close();

                Toast.makeText(MainActivity.this, "Archivo guardado: " + nombreArchivo, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "No se ha podido guardar: " + nombreArchivo, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "El texto está vacío.", Toast.LENGTH_SHORT).show();
        }
    }

    private void recuperar() {
        String nombreArchivo = "miArchivo.txt";
        try {
            String ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            File f = new File(ruta, nombreArchivo);
            InputStreamReader fin = new InputStreamReader(new FileInputStream(f));
            char[] buffer = new char[1024];
            StringBuilder texto = new StringBuilder();
            while ((fin.read(buffer)) != -1) {
                texto.append(new String(buffer));
            }
            txtContenidoArchivo.setText(texto);
            fin.close();

            Toast.makeText(MainActivity.this, "Archivo recuperado: " + nombreArchivo, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "No se ha podido recuperar: " + nombreArchivo, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE:
                // Si la solicitud se anula el resultado estará vacío
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido, podemos realizar la operación
                    guardar();
                } else {
                    // Permiso denegado, no podemos realizar la operación
                    Toast.makeText(MainActivity.this, "No se puede realizar la operación.", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_READ:
                // Si la solicitud se anula el resultado estará vacío
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido, podemos realizar la operación
                    recuperar();
                } else {
                    // Permiso denegado, no podemos realizar la operación
                    Toast.makeText(MainActivity.this, "No se puede realizar la operación.", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

}