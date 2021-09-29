package com.acme.ejer5_08;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // Establecer IP
    public static final String ip = "192.168.56.1";
    protected static int REQUEST_ADD = 123;
    protected ArrayList<Equipo> equipos;
    protected AdaptadorEquipo adap;
    protected ListView listaEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos la lista de equipos vacía
        equipos = new ArrayList<>();
        equipos = obtenerEquipos();

        // Creamos el adaptador y se lo asociamos al ListView
        adap = new AdaptadorEquipo(this, equipos);
        listaEquipos = (ListView) findViewById(R.id.listEquipos);
        listaEquipos.setAdapter(adap);

        // BotAdd
        ImageButton botAdd = (ImageButton) findViewById(R.id.botAdd);
        botAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(addIntent, REQUEST_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int duracion = Toast.LENGTH_SHORT;
        String mensa;
        if (requestCode == REQUEST_ADD) {
            if (resultCode == RESULT_OK) {
                Equipo nuevoEquipo = data.getParcelableExtra("equipo");
                equipos.add(nuevoEquipo);
                adap.notifyDataSetChanged();
                mensa = "Equipo añadido correctamente";
            } else {
                mensa = "No se ha añadido el equipo";
            }
            Toast.makeText(MainActivity.this, mensa, duracion).show();
        }
    }

    protected ArrayList<Equipo> obtenerEquipos() {
        ArrayList<Equipo> teams = new ArrayList<>();
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            String myurl = "http://" + ip + "/api.nba.com/v2/obtenerEquipos.php";
            DescargaWeb dw = new DescargaWeb();
            try {
                // La llamada a get() tras execute hace que espere hasta que dispongamos
                // de los datos
                dw.execute(myurl).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            teams = dw.getEquipos();
        } else {
            // Alerta: no se puede establecer la conexión
            int duracion = Toast.LENGTH_SHORT;
            String mensa = "No se ha podido establecer la conexión";
            Toast alerta = Toast.makeText(MainActivity.this, mensa, duracion);
            alerta.show();
        }

        return teams;
    }


}
