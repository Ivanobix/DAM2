package com.example.ejem_contentreceivercontactos;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected final int REQUEST_READ_CONTACTS = 123;
    protected final int REQUEST_SEND_SMS = 124;
    protected ListView lvContactos;
    protected ArrayAdapter<String> adaptador;
    protected ArrayList<String> listacontactos;
    protected String contacto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listacontactos = new ArrayList<>();
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listacontactos);
        lvContactos = (ListView) findViewById(R.id.lvContactos);
        lvContactos.setAdapter(adaptador);

        // Comprobamos el permiso READ_CONTACTS y obtenemos los contactos
        if ((android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        } else {
            obtenerContactos();
        }
        // Enviar mensaje a contacto seleccionado
        lvContactos.setOnItemClickListener((adapterView, v, i, l) -> {
            TextView txtContacto = (TextView) v;
            contacto = txtContacto.getText().toString();
            if ((android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
                    && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
            } else {
                enviarSMS(contacto);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    obtenerContactos();
                } else {
                    int dura = Toast.LENGTH_SHORT;
                    String mensa = "No dispone de permisos para leer los contactos";
                    Toast.makeText(getApplicationContext(), mensa, dura).show();
                }
            case REQUEST_SEND_SMS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enviarSMS(contacto);
                } else {
                    int dura = Toast.LENGTH_SHORT;
                    String mensa = "No dispone de permisos para enviar SMS";
                    Toast.makeText(getApplicationContext(), mensa, dura).show();
                }
        }
    }

    public void obtenerContactos() {
        ContentResolver cr = getContentResolver();
        String[] cols = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_URI
        };
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, cols, null, null, "2");
        if (cur.getCount() > 0) {
            listacontactos.clear();
            int posName = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int posTlf = cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            while (cur.moveToNext()) {
                String name = cur.getString(posName);
                if (Integer.parseInt(cur.getString(posTlf)) > 0) {
                    listacontactos.add(name);
                }
            }
            adaptador.notifyDataSetChanged();
        }
        cur.close();
    }

    private void enviarSMS(String nombre) {
        int duracion = Toast.LENGTH_SHORT;
        String mensaje = ((EditText) findViewById(R.id.txtSMS)).getText().toString();
        String alerta;

        if (mensaje.equals("")) {
            alerta = "No ha escrito el mensaje";
            Toast.makeText(getApplicationContext(), alerta, duracion).show();
        } else {
            ContentResolver cr = getContentResolver();
            String[] colId = {ContactsContract.Contacts._ID};
            String selName = ContactsContract.Contacts.DISPLAY_NAME + " = ?";
            String[] argsName = {nombre};
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, colId, selName, argsName, null);
            if (cur.getCount() > 0) {
                SmsManager smsManager = SmsManager.getDefault();
                Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                int posId = cur.getColumnIndex(ContactsContract.Contacts._ID);
                while (cur.moveToNext()) {
                    String id = cur.getString(posId);
                    String selId = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
                    String[] argsId = {id};
                    Cursor curTlf = cr.query(phoneUri, null, selId, argsId, null);
                    int posTlf = curTlf.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                    while (curTlf.moveToNext()) {
                        String telefono = curTlf.getString(posTlf);
                        try {
                            smsManager.sendTextMessage(telefono, null, mensaje, null, null);
                            alerta = nombre + ": " + mensaje;
                            Toast.makeText(getApplicationContext(), alerta, duracion).show();
                        } catch (Exception e) {
                            alerta = nombre + ": mensaje no enviado";
                            Toast.makeText(getApplicationContext(), alerta, duracion).show();
                        }
                    }
                    curTlf.close();
                }
                cur.close();
            }
        }
    }
}