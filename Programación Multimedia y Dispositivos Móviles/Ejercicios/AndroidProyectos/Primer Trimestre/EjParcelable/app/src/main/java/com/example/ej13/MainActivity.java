package com.example.ej13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected ListView listDiscos;
    protected ArrayList<Disco> discos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listDiscos = findViewById(R.id.listDiscos);

        discos = new ArrayList<>();
        discos.add(new Disco(R.drawable.img_backinblack, "Back In Pack", "AC/DC"));
        discos.add(new Disco(R.drawable.img_bodyguard, "The Bodyguard", "Whitney Houston"));
        discos.add(new Disco(R.drawable.img_darkside, "The Darkside of the Moon", "Pink Floyd"));
        discos.add(new Disco(R.drawable.img_eaglesgh, "Greatest Hits", "The Eagles"));
        discos.add(new Disco(R.drawable.img_rumors, "Rumors", "Fleetwood Mac"));
        discos.add(new Disco(R.drawable.img_saturdaynight, "Saturday Nigth", "Whigfield"));
        discos.add(new Disco(R.drawable.img_thriller, "Thriller", "Michael Jackson"));

        AdaptadorDisco at = new AdaptadorDisco(this, discos);
        listDiscos.setAdapter(at);

        at.notifyDataSetChanged();
        listDiscos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("disco", discos.get(position));
                startActivity(intent);
            }
        });
    }
}