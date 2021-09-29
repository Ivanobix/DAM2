package com.example.ej15recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Titular> datos;
    private RecyclerView rvTitulares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = new ArrayList<>();
        rvTitulares = (RecyclerView) findViewById(R.id.rvTitulares);
        for (int i = 0; i < 50; i++) {
            datos.add(new Titular("Título " + i, "Subtitulo " + i));
        }

        rvTitulares.setHasFixedSize(true);
        final AdaptadorTitular adap = new AdaptadorTitular(datos);
        rvTitulares.setAdapter(adap);

        // Establecer el LayoutManager
        rvTitulares.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recView.setLayoutManager(new GridLayoutManager(this,3));

        // Añadir separadores
        rvTitulares.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        //recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // Añadir animacion
        rvTitulares.setItemAnimator(new DefaultItemAnimator());

        // Inserta un nuevo elemento al principio de la lista
        Button btnNuevo = (Button) findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.add(0, new Titular("Título2", "Subtítulo2"));
                adap.notifyDataSetChanged();
            }
        });

        //Eliminar el primer elemento de la lista
        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.remove(0);
                adap.notifyDataSetChanged();
            }
        });

        //Intercambiar posición del primer y segundo elemento
        Button btnMover = (Button) findViewById(R.id.btnMover);
        btnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Titular aMover = datos.get(0);
                datos.set(0, datos.get(1));
                datos.set(1, aMover);
                adap.notifyDataSetChanged();
            }
        });

    }
}