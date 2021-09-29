package com.example.ejempermisos;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 1;
    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSolicitar = (Button) findViewById(R.id.btnSolicitar);
        TextView lblEstado = (TextView) findViewById(R.id.lblEstado);
        thisActivity = MainActivity.this;

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Comprobamos si no disponemos del permiso: READ_CONTACTS
                if (ContextCompat.checkSelfPermission(thisActivity,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Comprobamos si ya se había solicitado el permiso
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            thisActivity, Manifest.permission.READ_CONTACTS)) {
                        // Aquí se mostraría información al usuario sobre la
                        // necesidad del permiso
                        // Añadimos la solicitud para que se nos muestre
                        // después de haberlo rechazado
                        ActivityCompat.requestPermissions(thisActivity,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                REQUEST_READ_CONTACTS);
                    } else {
                        // No es necesario mostrar información adicional
                        ActivityCompat.requestPermissions(thisActivity,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                REQUEST_READ_CONTACTS);
                    }
                } else {
                    // Ya disponíamos del permiso, no es necesario solicitarlo
                    TextView txtEstado = (TextView) findViewById(R.id.lblEstado);
                    txtEstado.setText("Permiso ya concedido");
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[]
            permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_CONTACTS:
                // Si la solicitud se anula el resultado estará vacío
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido, podemos realizar la operación
                    TextView txtEstado = (TextView) findViewById(R.id.lblEstado);
                    txtEstado.setText("Permiso concedido");
                } else {
                    // Permiso denegado, no podemos realizar la operación
                    TextView txtEstado = (TextView) findViewById(R.id.lblEstado);
                    txtEstado.setText("Permiso denegado");
                }
                return;
            // Aquí procesaríamos el resto de solicitudes mediante su
            // requestCode correspondiente.
        }
    }
}