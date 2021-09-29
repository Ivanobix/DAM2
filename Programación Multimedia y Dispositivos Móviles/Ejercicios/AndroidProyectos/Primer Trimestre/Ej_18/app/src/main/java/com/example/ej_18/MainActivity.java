package com.example.ej_18;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView rvDiscos;
    protected ArrayList<Disco> discos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvDiscos = findViewById(R.id.rvDiscos);

        discos = new ArrayList<>();
        discos.add(new Disco(R.drawable.img_backinblack, "Back In Pack", "AC/DC"));
        discos.add(new Disco(R.drawable.img_bodyguard, "The Bodyguard", "Whitney Houston"));
        discos.add(new Disco(R.drawable.img_darkside, "The Darkside of the Moon", "Pink Floyd"));
        discos.add(new Disco(R.drawable.img_eaglesgh, "Greatest Hits", "The Eagles"));
        discos.add(new Disco(R.drawable.img_rumors, "Rumors", "Fleetwood Mac"));
        discos.add(new Disco(R.drawable.img_saturdaynight, "Saturday Nigth", "Whigfield"));
        discos.add(new Disco(R.drawable.img_thriller, "Thriller", "Michael Jackson"));

        rvDiscos.setHasFixedSize(true);
        final AdaptadorDisco adap = new AdaptadorDisco(discos);
        rvDiscos.setAdapter(adap);

        // Establecer el LayoutManager
        rvDiscos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //rvDiscos.setLayoutManager(new GridLayoutManager(this, 3));

        // Añadir separadores
        rvDiscos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //rvDiscos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        // Añadir animacion
        rvDiscos.setItemAnimator(new DefaultItemAnimator());
    }
}