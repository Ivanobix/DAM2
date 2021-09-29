package com.example.ejemcomparable;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listVideojuegos = (ListView) findViewById(R.id.listVideojuegos);

        ArrayList<Videojuego> videojuegos = new ArrayList<>();
        videojuegos.add(new Videojuego("GTA", "Rockstar", "2005", "1245"));
        videojuegos.add(new Videojuego("Minecraft", "Mojang", "2009", "367"));
        videojuegos.add(new Videojuego("ONI", "Klei", "2018", "34"));
        videojuegos.add(new Videojuego("League Of Legends", "Riot", "2001", "500"));
        videojuegos.add(new Videojuego("World Of Warcraft", "Riot", "2001", "356"));

        Collections.sort(videojuegos);
        String[] items = new String[videojuegos.size()];
        int cont = 0;
        for (Videojuego v : videojuegos) {
            String texto = v.toString();
            items[cont] = texto;
            cont++;
        }
        ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listVideojuegos.setAdapter(adap);
    }
}