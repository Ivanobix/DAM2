package com.example.ej_ficheroexterno_obtenerinfo;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    protected TextView lblEstado, lblDirectorio, lblDirectorioFotos, lblDirectorioDescargas, lblDirectorioCompartido;
    protected Button btnObtenerinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblEstado = (TextView) findViewById(R.id.lblEstado);
        lblDirectorio = (TextView) findViewById(R.id.lblDirectorio);
        lblDirectorioFotos = (TextView) findViewById(R.id.lblDirectorioFotos);
        lblDirectorioDescargas = (TextView) findViewById(R.id.lblDirectorioDescargas);
        lblDirectorioCompartido = (TextView) findViewById(R.id.lblDirectorioCompartido);
        btnObtenerinfo = (Button) findViewById(R.id.btnObtenerInfo);

        btnObtenerinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerInfo();
            }
        });

    }

    private void obtenerInfo() {
        //Estado
        String informacion = Environment.getExternalStorageState();
        switch (informacion) {
            case Environment.MEDIA_MOUNTED:
                // Disponible para lectura/excritura
                informacion = "Disponible Escritura y Lectura";
                break;
            case Environment.MEDIA_MOUNTED_READ_ONLY:
                // Disponible sólo lectura
                informacion = "Disponible Lectura";
                break;
            case Environment.MEDIA_REMOVED:
                // No disponible
                informacion = "No Disponible";
                break;
            default:
                // Resto de casos
                informacion = "Otros";
                break;
        }
        lblEstado.setText(informacion);

        //Directorio
        File archivo = getExternalFilesDir(null);
        informacion = archivo.getAbsolutePath();
        lblDirectorio.setText(informacion);

        //Directorio App Fotos
        archivo = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        informacion = archivo.getAbsolutePath();
        lblDirectorioFotos.setText(informacion);

        //Directorio App Descargas
        archivo = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        informacion = archivo.getAbsolutePath();
        lblDirectorioDescargas.setText(informacion);

        //Directorio Compartido
        archivo = Environment.getExternalStorageDirectory();
        informacion = archivo.getAbsolutePath();
        lblDirectorioCompartido.setText(informacion);


    }
}