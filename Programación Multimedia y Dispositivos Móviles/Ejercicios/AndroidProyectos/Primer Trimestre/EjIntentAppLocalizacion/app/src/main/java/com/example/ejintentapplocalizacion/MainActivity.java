package com.example.ejintentapplocalizacion;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtLatitud = (EditText) findViewById(R.id.txtLatitud);
        EditText txtLongitud = (EditText) findViewById(R.id.txtLogitud);

        Button btnViajar = (Button) findViewById(R.id.btnViajar);
        btnViajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String longitud = txtLongitud.getText().toString();
                String latitud = txtLatitud.getText().toString();
                String enlace = "http://maps.google.es/?q=" + latitud + "," + longitud;
                intent.setData(Uri.parse(enlace));
                //Enlace http://maps.google.es/?q=42.5987263,-5.5670959
                PackageManager pm = getPackageManager();
                ComponentName compo = intent.resolveActivity(pm);
                String titulo = "Viajar";
                if (compo != null) {
                    startActivity(Intent.createChooser(intent, titulo));
                } else {
                    Toast.makeText(MainActivity.this, "No se puede realizar la acción: " + titulo, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}