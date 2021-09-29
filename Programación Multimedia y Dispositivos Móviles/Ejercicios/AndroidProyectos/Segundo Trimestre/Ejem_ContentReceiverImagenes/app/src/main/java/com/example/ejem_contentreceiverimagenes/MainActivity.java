package com.example.ejem_contentreceiverimagenes;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected static final int REQUEST_READ_EXTERNAL_STORAGE = 101;
    protected LinearLayout layoutImg;
    protected ImageView imgFoto, imgPrev;
    protected ImageButton botSig, botPrev;
    protected Button botSeleccionar;
    protected ArrayList<String> fotos;
    protected int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos las referencias d elos objetos
        layoutImg = (LinearLayout) findViewById(R.id.layoutImg);
        imgPrev = (ImageView) findViewById(R.id.imgPrev);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        botSeleccionar = (Button) findViewById(R.id.botSeleccionar);
        botSig = (ImageButton) findViewById(R.id.botSig);
        botPrev = (ImageButton) findViewById(R.id.botPrev);

        // Iniciamos las variables para el manejo del array
        fotos = null;
        pos = 0;

        imgFoto.setOnClickListener(v -> {
            if (fotos == null) {
                // Comprobamos el permiso READ_EXTERNAL_STORAGE y obtenemos las imagenes
                if ((android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
                        && ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
                } else {
                    obtenerImagenes();
                }
            } else {
                layoutImg.setVisibility(View.VISIBLE);
                botSeleccionar.setVisibility(View.VISIBLE);
                mostrarImagen(imgPrev, pos);
            }

        });

        botSig.setOnClickListener(v -> {
            pos++;
            if (pos == fotos.size()) {
                pos = 0;
            }
            mostrarImagen(imgPrev, pos);
        });

        botPrev.setOnClickListener(v -> {
            pos--;
            if (pos < 0) {
                pos = fotos.size() - 1;
            }
            mostrarImagen(imgPrev, pos);
        });

        botSeleccionar.setOnClickListener(v -> {
            mostrarImagen(imgFoto, pos);
            layoutImg.setVisibility(View.INVISIBLE);
            botSeleccionar.setVisibility(View.INVISIBLE);
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerImagenes();
            } else {
                String mensa = "No dispone de permisos para acceder a las imagenes";
                Toast.makeText(getApplicationContext(), mensa, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void obtenerImagenes() {
        fotos = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor curImg = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        if (curImg != null) {
            while (curImg.moveToNext()) {
                String rutaImg = curImg.getString(0);
                fotos.add(rutaImg);
            }
            curImg.close();
        } else {
            Toast.makeText(MainActivity.this, "No hay imagenes", Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrarImagen(ImageView marco, int pos) {
        marco.setImageBitmap(BitmapFactory.decodeFile(fotos.get(pos)));
        Toast.makeText(MainActivity.this, fotos.get(pos), Toast.LENGTH_SHORT).show();
    }

}