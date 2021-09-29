package com.example.ejemintentappimplicito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEjecutar = (Button)findViewById(R.id.btnEjecutar);
        btnEjecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para seleccionar que el tipo de acción sea abrir el teléfono.
                //Intent intent = new Intent(Intent.ACTION_DIAL);
                //Para pasarle un número directamente.
                //intent.setData(Uri.parse("tel: 601100518"));


                //Para seleccionar que el  tipo de acción sea para enviar un sms.
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                //Para pasarle un número directamente.
                intent.setData(Uri.parse("smsto: 601100518"));
                //Para ponerle un cuerpo al mensaje.
                intent.putExtra("sms_body", "Bla bla bla");


                PackageManager pm = getPackageManager();
                ComponentName compo =  intent.resolveActivity(pm);
                String titulo = "Telefono";
                if (compo != null) {
                    startActivity(Intent.createChooser(intent, titulo));
                } else {
                    Toast.makeText(MainActivity.this, "No se puede realizar la acción: " + titulo, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}