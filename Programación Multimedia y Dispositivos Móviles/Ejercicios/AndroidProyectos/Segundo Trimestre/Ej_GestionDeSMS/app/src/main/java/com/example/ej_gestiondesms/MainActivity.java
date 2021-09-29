package com.example.ej_gestiondesms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected static final int REQUEST_SMS = 123;
    protected Button btnEnviarSMS, btnLeerSMS;
    protected EditText txtTelefono, txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos referencias a los controles
        btnEnviarSMS = (Button) findViewById(R.id.btnEnviarSMS);
        btnLeerSMS = (Button) findViewById(R.id.btnLeerSMS);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);

        // Comprobar Permisos
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Agregamos todos los permisos y realizamos una única solicitud
            String[] permisos = new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS};
            ActivityCompat.requestPermissions(this, permisos, REQUEST_SMS);
        }

        // Leer Mensajes (inbox)
        btnLeerSMS.setOnClickListener(view -> {
            Uri uri = Uri.parse("content://sms/inbox");
            Cursor cur = getContentResolver().query(uri, null, null, null, null);
            if (cur.moveToFirst()) {
                do {
                    // Mostrar todos los campos del SMS
                    /*
                    String msg = "";
                    for (int i = 0; i < cur.getColumnCount(); i++) {
                        msg = msg + i + " => " + cur.getColumnName(i) + ":" + cur.getString(i) + "\n";
                    }
                     */

                    // Mostrar telefono y texto del SMS
                    String msg = cur.getString(2) + ": " + cur.getString(12);
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                } while (cur.moveToNext());
            }
            cur.close();
        });

        // Enviar Mensaje
        btnEnviarSMS.setOnClickListener(view -> {
            String mensaje;
            PackageManager pm = getPackageManager();
            if (pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
                // Podemos enviar SMS
                String numTelefono = txtTelefono.getText().toString().trim();
                String textoSMS = txtMensaje.getText().toString().trim();
                if (!numTelefono.equals("") && !textoSMS.equals("")) {
                    SmsManager smsm = SmsManager.getDefault();
                    if (textoSMS.length() <= 160) {
                        smsm.sendTextMessage(numTelefono, null, textoSMS, null, null);
                    } else {
                        ArrayList<String> textoSMSParts = smsm.divideMessage(textoSMS);
                        smsm.sendMultipartTextMessage(numTelefono, null, textoSMSParts, null, null);
                    }
                    // Alerta: SMS enviado
                    mensaje = "SMS enviado";
                } else {
                    // Alerta: Falta número de teléfono o cuerpo del mensaje
                    mensaje = "Debe introducir el teléfono y el mensaje";
                }
            } else {
                // No podemos enviar SMS
                mensaje = "Su dispositivo no puede enviar SMS";
            }
            Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String mensaje;
        if (requestCode == REQUEST_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnEnviarSMS.setVisibility(View.VISIBLE);
                btnLeerSMS.setVisibility(View.VISIBLE);
            } else {
                btnEnviarSMS.setVisibility(View.INVISIBLE);
                btnLeerSMS.setVisibility(View.INVISIBLE);
                mensaje = "No tiene permiso para acceder a SMS";
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            }
        }
    }
}