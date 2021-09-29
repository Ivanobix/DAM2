package com.example.ejemtelefonia;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_PHONE_STATE = 0;
    protected TextView lblTipoTelefono, lblTipoRed, lblOperador, lblNumeroTelefono, lblImei;
    protected TelephonyManager tm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblNumeroTelefono = (TextView) findViewById(R.id.lblNumeroDeTelefono);
        lblTipoRed = (TextView) findViewById(R.id.lblTipoRed);
        lblTipoTelefono = (TextView) findViewById(R.id.lblTipoTelefono);
        lblOperador = (TextView) findViewById(R.id.lblOperador);
        lblImei = (TextView) findViewById(R.id.lblImei);

        // Obtenemos el gestor de telefonía
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        //Tipo de teléfono
        String tipoTelefono = "Tipo Teléfono: ";
        int phoneType = tm.getPhoneType();
        switch (phoneType) {
            case TelephonyManager.PHONE_TYPE_NONE:
                tipoTelefono += "Ninguno";
                break;
            case TelephonyManager.PHONE_TYPE_CDMA:
                tipoTelefono += "CDMA";
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                tipoTelefono += "GSM";
                break;
            case TelephonyManager.PHONE_TYPE_SIP:
                tipoTelefono += "SIP";
                break;
            default:
                tipoTelefono += "N/A";
                break;
        }
        lblTipoTelefono.setText(tipoTelefono);

        //Operador
        String operador = "Operador: " + tm.getNetworkOperatorName();
        lblOperador.setText(operador);

        //Número de teléfono y tipo de red
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_PHONE_STATE)) {
                Toast.makeText(this, "Permiso necesario para acceder a la info del teléfono.", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
            }
        } else {
            mostrarInfo();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mostrarInfo();
                } else {
                    Toast.makeText(this, "No dispone de permisos para acceder a la info.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    protected void mostrarInfo() {
        //Número de teléfono
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            String numeroTelefono = "Número de teléfono: " + tm.getLine1Number();
            lblNumeroTelefono.setText(numeroTelefono);
        }

        //Tipo de red de datos
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            int dataType = tm.getDataNetworkType();
            String tipoRed = "Tipo de Red: ";
            switch (dataType) {
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    tipoRed += "Desconocido";
                    break;
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    tipoRed += "GPRS";
                    break;
                case TelephonyManager.NETWORK_TYPE_GSM:
                    tipoRed += "GSM";
                    break;
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    tipoRed += "CDMA";
                    break;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    tipoRed += "LTE";
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    tipoRed += "UMTS";
                    break;
                default:
                    tipoRed += "Otra";
                    break;
            }
            lblTipoRed.setText(tipoRed);
        }
        //IMEI
        String imei;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            imei = tm.getDeviceId();
        } else if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q) {
            imei = tm.getImei();
        } else {
            imei = "IMEI Desconocido";
        }
        lblImei.setText(imei);


    }
}