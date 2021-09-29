package com.example.ejemficheroexterno;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    protected TextView lblDirectorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblDirectorio = (TextView) findViewById(R.id.lblDirectorio);

        //Comprobar el estado del almacenamiento externo
        String estadoAE = Environment.getExternalStorageState();
        String mensa = "Almacenamiento Externo: ";
        switch (estadoAE) {
            case Environment.MEDIA_MOUNTED:
                // Disponible para lectura/excritura
                mensa += "Disponible Escritura y Lectura";
                //Obtener ruta de almacen externo privado
                //File archivo = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                //String dir = archivo.getAbsolutePath();

                //Obtener ruta de almacen externo compartido (raiz)
                //File archivo = Environment.getExternalStorageDirectory();
                //String dir = archivo.getAbsolutePath();

                //Obtener ruta de almacen externo compartido (especifico)
                File archivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                String dir = archivo.getAbsolutePath();

                lblDirectorio.setText(dir);
                break;
            case Environment.MEDIA_MOUNTED_READ_ONLY:
                // Disponible sólo lectura
                mensa += "Disponible Lectura";
                break;
            case Environment.MEDIA_REMOVED:
                // No disponible
                mensa += "No Disponible";
                break;
            default:
                // Resto de casos
                mensa += "Otros";
                break;
        }

        Toast.makeText(MainActivity.this, mensa, Toast.LENGTH_SHORT).show();
    }
}