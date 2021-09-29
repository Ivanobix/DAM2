package com.example.ej_mensajestelefono;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ESTE EJERCICIO NO FUNCIONA

    private static final int REQUEST_READ_SMS = 1;
    private EditText txtMensaje, txtTelefono;
    private Button btnEnviar, btnLeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnLeer = (Button) findViewById(R.id.btnLeer);

        btnEnviar.setOnClickListener(v -> {
            String tlf = txtTelefono.getText().toString();
            String mensa = txtMensaje.getText().toString();
            SmsManager smsm = SmsManager.getDefault();
            if (mensa.length() > 160) {
                ArrayList<String> textoDividido = smsm.divideMessage(mensa);
                smsm.sendMultipartTextMessage(tlf, null, textoDividido, null, null);
            } else {
                smsm.sendTextMessage(tlf, null, mensa, null, null);
                Toast.makeText(MainActivity.this, "Mensaje enviado.", Toast.LENGTH_SHORT).show();
            }
        });

        btnLeer.setOnClickListener(v -> {
            // Comprobamos si no disponemos del permiso: READ_CONTACTS
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, REQUEST_READ_SMS);
            } else {
                // Ya disponíamos del permiso, no es necesario solicitarlo
                leerMensajes();
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_SMS) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, podemos realizar la operación
                leerMensajes();
            } else {
                // Permiso denegado, no podemos realizar la operación
                Toast.makeText(MainActivity.this, "No se tienen los permisos necesarios.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void leerMensajes() {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uri, null, null, null, null);
        if (cur.moveToFirst()) {
            do {
                // Mostrar todos los campos del SMS
                String msg = "";
                for (int i = 0; i < cur.getColumnCount(); i++) {
                    msg = msg + i + " => " + cur.getColumnName(i) + ": " + cur.getString(i) + "\n";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            } while (cur.moveToNext());
            cur.close();
        }
    }
}