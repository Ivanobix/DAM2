package com.example.ej_permisos;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    protected EditText txtTelefono;
    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisActivity = MainActivity.this;
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        Button btnDial = (Button) findViewById(R.id.btnDial);
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String telefono = "tel: " + txtTelefono.getText().toString();
                intent.setData(Uri.parse(telefono));
                startActivity(intent);
            }
        });

        Button btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Comprobamos si no disponemos del permiso: READ_CONTACTS
                if (ContextCompat.checkSelfPermission(thisActivity,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Comprobamos si ya se había solicitado el permiso
                    ActivityCompat.requestPermissions(thisActivity,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_CALL);
                } else {
                    // Ya disponíamos del permiso, no es necesario solicitarlo
                    llamar();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL:
                // Si la solicitud se anula el resultado estará vacío
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido, podemos realizar la operación
                    llamar();
                } else {
                    // Permiso denegado, no podemos realizar la operación
                    Toast.makeText(MainActivity.this, "No se puede realizar la operación.", Toast.LENGTH_SHORT).show();
                }
                return;
            // Aquí procesaríamos el resto de solicitudes mediante su
            // requestCode correspondiente.
        }
    }

    private void llamar() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String telefono = "tel: " + txtTelefono.getText().toString();
        intent.setData(Uri.parse(telefono));
        startActivity(intent);
    }
}