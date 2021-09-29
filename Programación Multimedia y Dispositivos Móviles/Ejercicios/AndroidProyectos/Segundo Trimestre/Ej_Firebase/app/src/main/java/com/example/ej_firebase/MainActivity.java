package com.example.ej_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDB;
    private ValueEventListener changeListener;
    private ImageButton btnAnadir;
    private ListView listContactos;
    private ArrayAdapter<Contacto> adap;
    private ArrayList<Contacto> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initHandlers();

    }

    private void initComponents() {
        btnAnadir = (ImageButton) findViewById(R.id.btnAnadir);
        listContactos = (ListView) findViewById(R.id.listContactos);

        adap = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, contactos);
        listContactos.setAdapter(adap);

        mDB = FirebaseDatabase.getInstance().getReference();
        contactos = new ArrayList<>();
    }

    private void initHandlers() {
        changeListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Se han producido cambios
                contactos.clear();
                for (DataSnapshot contacto : dataSnapshot.child("contactos").getChildren()) {
                    contactos.add(contacto.getValue(Contacto.class));
                    adap.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Se ha producido algún error
                String error = databaseError.getMessage();
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        };
        mDB.addValueEventListener(changeListener);

        btnAnadir.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        listContactos.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            intent.putExtra("contacto", contactos.get(position));
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDB.removeEventListener(changeListener);
    }
}