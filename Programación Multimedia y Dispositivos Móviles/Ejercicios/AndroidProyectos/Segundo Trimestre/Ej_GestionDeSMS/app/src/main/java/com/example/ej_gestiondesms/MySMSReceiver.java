package com.example.ej_gestiondesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object[] pdus = (Object[]) extras.get("pdus");
            SmsMessage sms;
            for (int i = 0; i < pdus.length; i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = extras.getString("format");
                    sms = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    sms = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                String numTelefono = sms.getDisplayOriginatingAddress();
                String textoSMS = sms.getDisplayMessageBody();
                String mensaje = numTelefono + ": " + textoSMS;
                Toast toast = Toast.makeText(context, mensaje, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}