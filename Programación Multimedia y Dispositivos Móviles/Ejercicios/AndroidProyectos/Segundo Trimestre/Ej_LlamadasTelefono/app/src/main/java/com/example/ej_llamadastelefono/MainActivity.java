package com.example.ej_llamadastelefono;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
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
        // Gestión de llamadas entrantes
        MyPhoneListener phoneListener = new MyPhoneListener();
        TelephonyManager tm =
                (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        // Realizar llamada
        Button botTelefono = (Button) findViewById(R.id.botTelefono);
        botTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm = getPackageManager();
                if (pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
                    EditText eTlf = (EditText) findViewById(R.id.editTelefono);
                    String numTel = eTlf.getText().toString().trim();
                    if (!numTel.equals("")) {
                        Uri tlf = Uri.parse("tel:" + numTel);
                        Intent call = new Intent(Intent.ACTION_DIAL, tlf);
                        startActivity(call);
                    } else {
                        int duracion = Toast.LENGTH_SHORT;
                        String mensa = "Debe introducir el número de teléfono";
                        Toast t = Toast.makeText(MainActivity.this, mensa, duracion);
                        t.show();
                    }
                } else {
                    int duracion = Toast.LENGTH_SHORT;
                    String mensa = "Su dispositivo no puede realizar llamadas";
                    Toast t = Toast.makeText(getApplicationContext(), mensa, duracion);
                    t.show();
                }
            }
        });
    }

    private class MyPhoneListener extends PhoneStateListener {
        private boolean onCall = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            String mensa;
            int duracion = Toast.LENGTH_LONG;
            Toast toast;
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    // Sonando
                    mensa = "Llamada Sonando";
                    toast = Toast.makeText(MainActivity.this, mensa, duracion);
                    toast.show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // Descolgado/Comunicando
                    mensa = "Llamada Comunicando";
                    toast = Toast.makeText(MainActivity.this, mensa, duracion);
                    toast.show();
                    onCall = true;
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    // Inactivo
                    if (onCall) {
                        // Inactivo tras llamada
                        mensa = "Reinicio de aplicacion tras llamada";
                        toast = Toast.makeText(MainActivity.this, mensa, duracion);
                        toast.show();
                        onCall = false;
                        // Reiniciamos la aplicación
                        Intent restart = getBaseContext().getPackageManager().
                                getLaunchIntentForPackage(getBaseContext().getPackageName());
                        restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(restart);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}