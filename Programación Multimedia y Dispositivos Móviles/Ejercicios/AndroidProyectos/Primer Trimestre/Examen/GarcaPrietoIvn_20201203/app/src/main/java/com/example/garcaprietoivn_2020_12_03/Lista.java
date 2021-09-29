package com.example.garcaprietoivn_2020_12_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    private static final int REQUEST_NUEVA_MASCOTA = 40;
    private ArrayList<Mascota> mascotas;
    private AdaptadorMascota at;
    private String usuario;
    private int ultimaMascotaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ultimaMascotaSeleccionada = 0;
        ImageButton btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> finish());
        ImageButton btnNuevo = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(v -> {
            Intent intent = new Intent(Lista.this, NuevaMascota.class);
            startActivityForResult(intent, REQUEST_NUEVA_MASCOTA);
        });

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("usuario")) {
                usuario = intent.getStringExtra("usuario");
            }
        }
        if (!usuario.equals("admin")) {
            btnNuevo.setVisibility(View.INVISIBLE);
        }

        mascotas = new ArrayList<>();
        mascotas.add(new Mascota("Simba", "Alaska", "Adopción", 2002, R.drawable.simba));
        mascotas.add(new Mascota("Sultán", "Bulldog Francés", "Acogida", 2002, -1));
        mascotas.add(new Mascota("Niebla", "Galgo", "Adoptado", 2002, -1));
        mascotas.add(new Mascota("Ringo", "Galgo", "Acogido", 2002, R.drawable.ringo));
        mascotas.add(new Mascota("Estela", "Alaska", "Adopción", 2002, -1));

        ListView listaMascotas = findViewById(R.id.listMascotas);
        at = new AdaptadorMascota(this, mascotas);
        listaMascotas.setAdapter(at);
        registerForContextMenu(listaMascotas);
        listaMascotas.setOnItemLongClickListener((parent, view, position, id) -> {
            ultimaMascotaSeleccionada = position;
            return false;
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        if (usuario.equals("admin")) {
            inflater.inflate(R.menu.menu_admin, menu);
        } else {
            inflater.inflate(R.menu.menu_normal, menu);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_NUEVA_MASCOTA) {
            if (resultCode == NuevaMascota.RESULT_MASCOTA_ANADIDA) {
                assert data != null;
                Mascota mascotaNueva = data.getParcelableExtra("mascota");
                mascotas.add(mascotaNueva);
                String mensaje = mascotaNueva.getNombre() + ": Añadido.";
                Toast.makeText(Lista.this, mensaje, Toast.LENGTH_SHORT).show();
                at.notifyDataSetChanged();
            } else {
                Toast.makeText(Lista.this, "Operación canelada.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcEliminar:
                mascotas.remove(ultimaMascotaSeleccionada);
                Toast.makeText(Lista.this, "La mascota ha sido eliminada.", Toast.LENGTH_SHORT).show();
                at.notifyDataSetChanged();
                return true;
            case R.id.opcAdoptar:
                Mascota mascotaAAdoptar = mascotas.get(ultimaMascotaSeleccionada);
                if (mascotaAAdoptar.getEstado().equals("Adopción")) {
                    mascotaAAdoptar.setEstado("Adoptado");
                    String mensaje = mascotaAAdoptar.getNombre() + ": Adoptado.";
                    Toast.makeText(Lista.this, mensaje, Toast.LENGTH_SHORT).show();
                    at.notifyDataSetChanged();
                } else {
                    Toast.makeText(Lista.this, "Esta mascota no está disponible para la adopción.", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.opcAcoger:
                Mascota mascotaAAcoger = mascotas.get(ultimaMascotaSeleccionada);
                if (mascotaAAcoger.getEstado().equals("Acogida")) {
                    mascotaAAcoger.setEstado("Acogido");
                    String mensaje = mascotaAAcoger.getNombre() + ": Acogido.";
                    Toast.makeText(Lista.this, mensaje, Toast.LENGTH_SHORT).show();
                    at.notifyDataSetChanged();
                } else {
                    Toast.makeText(Lista.this, "Esta mascota no está disponible para la acogida.", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}