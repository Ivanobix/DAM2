package com.example.ej_contentreceivercontactos;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected final int REQUEST_READ_CONTACTS = 123;
    protected final int REQUEST_SEND_SMS = 124;
    protected EditText txtFiltro;
    protected EditText txtMensaje;
    protected ListView listaContactos;
    protected Button btnFiltrar;
    protected Button btnEliminarFiltro;
    protected ArrayAdapter<Contacto> adaptador;
    protected ArrayList<Contacto> contactos;
    protected Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFiltro = (EditText) findViewById(R.id.txtFiltro);
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        listaContactos = (ListView) findViewById(R.id.listaContactos);
        btnFiltrar = (Button) findViewById(R.id.btnFiltrar);
        btnEliminarFiltro = (Button) findViewById(R.id.btnEliminarFiltro);

        contactos = new ArrayList<>();
        adaptador = new AdaptadorContacto(MainActivity.this, contactos);
        listaContactos.setAdapter(adaptador);

        // Comprobamos el permiso READ_CONTACTS y obtenemos los contactos
        if ((android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        } else {
            obtenerContactos(null);
        }

        // Enviar mensaje a contacto seleccionado
        listaContactos.setOnItemClickListener((adapterView, view, position, id) -> {
            contacto = (Contacto) listaContactos.getItemAtPosition(position);
            if ((android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
                    && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
            } else {
                enviarSMS(contacto);
            }
        });

        // Filtrar lista
        btnFiltrar.setOnClickListener(v -> obtenerContactos(txtFiltro.getText().toString()));

        //Eliminar filtro lista
        btnEliminarFiltro.setOnClickListener(v -> obtenerContactos(null));


    }

    public void obtenerContactos(String filtrado) {
        ContentResolver cr = getContentResolver();
        String[] cols = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_URI
        };

        String where = null;
        if (filtrado != null) {
            where = ContactsContract.Contacts.DISPLAY_NAME + " like('%" + filtrado + "%')";
        }

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, cols, where, null, null);
        if (cur.getCount() > 0) {
            contactos.clear();
            int posName = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int posTlf = cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            int posPerfil = cur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI);
            while (cur.moveToNext()) {
                if (Integer.parseInt(cur.getString(posTlf)) > 0) {
                    String nombreContacto = cur.getString(posName);
                    String uriImagenString = cur.getString(posPerfil);
                    
                    //Si no tiene imagen deja el ImageView en blanco
                    if (uriImagenString == null) {
                        uriImagenString = "";
                    }
                    Uri fotoPerfil = Uri.parse(uriImagenString);
                    contactos.add(new Contacto(nombreContacto, fotoPerfil));
                }
            }
            adaptador.notifyDataSetChanged();
        } else {
            Toast.makeText(MainActivity.this, "No se han encontrado contactos.", Toast.LENGTH_SHORT).show();
        }
        cur.close();
    }

    private void enviarSMS(Contacto contacto) {
        String nombre = contacto.getNombre();
        String mensaje = ((EditText) findViewById(R.id.txtMensaje)).getText().toString();
        String alerta;

        if (mensaje.equals("")) {
            alerta = "No ha escrito el mensaje";
            Toast.makeText(getApplicationContext(), alerta, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), alerta, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            alerta = nombre + ": mensaje no enviado";
                            Toast.makeText(getApplicationContext(), alerta, Toast.LENGTH_SHORT).show();
                        }
                    }
                    curTlf.close();
                }
                cur.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    obtenerContactos(null);
                } else {
                    String mensa = "No dispone de permisos para leer los contactos";
                    Toast.makeText(getApplicationContext(), mensa, Toast.LENGTH_SHORT).show();
                }
            case REQUEST_SEND_SMS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enviarSMS(contacto);
                } else {
                    String mensa = "No dispone de permisos para enviar SMS";
                    Toast.makeText(getApplicationContext(), mensa, Toast.LENGTH_SHORT).show();
                }
        }
    }

}