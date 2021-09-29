package com.example.ejem_firebase;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    protected DatabaseReference mDB;
    protected ValueEventListener changeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseReference.CompletionListener cl = (dbError, dbRef) -> {
            if (dbError != null) {
                // Error de escritura
                Toast.makeText(MainActivity.this, dbError.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                // Escritura correcta
                Toast.makeText(MainActivity.this, "Contacto almacenado", Toast.LENGTH_SHORT).show();
            }
        };


        changeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Se han producido cambios
                for (DataSnapshot contacto : dataSnapshot.child("contactos").getChildren()) {
                    Contacto c = contacto.getValue(Contacto.class);
                    String texto = c.nombre + " " + c.apellido;
                    Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Se ha producido algún error
                String error = databaseError.getMessage();
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        };


        mDB = FirebaseDatabase.getInstance().getReference();
        mDB.addValueEventListener(changeListener);

        Contacto c = new Contacto("Prueba", "Pruebaa", 601010518);
        mDB = FirebaseDatabase.getInstance().getReference("/contactos").push();
        mDB.setValue(c, cl);
        String contactoId = mDB.getKey();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDB.removeEventListener(changeListener);
    }
}