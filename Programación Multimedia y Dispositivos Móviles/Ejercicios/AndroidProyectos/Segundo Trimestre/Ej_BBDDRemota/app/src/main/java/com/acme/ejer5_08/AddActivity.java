package com.acme.ejer5_08;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AddActivity extends AppCompatActivity {

    // Establecer IP
    public static final String ip = "192.168.56.1";
    EditText editNom, editCiu, editConf, editDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Establecemos el resultado a Cancel y en caso de insertarse el equipo
        // lo modificaremos a OK
        setResult(RESULT_CANCELED);

        // Obtenemos referencias a los elementos
        editNom = (EditText) findViewById(R.id.editNombre);
        editCiu = (EditText) findViewById(R.id.editCiudad);
        editConf = (EditText) findViewById(R.id.editConferencia);
        editDiv = (EditText) findViewById(R.id.editDivision);

        // Botón Insertar
        Button botInsertar = (Button) findViewById(R.id.botInsertar);
        botInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editNom.getText().toString().trim();
                String ciu = editCiu.getText().toString().trim();
                String conf = editConf.getText().toString().trim();
                String div = editDiv.getText().toString().trim();
                if (nom.equals("") || ciu.equals("") || conf.equals("") || div.equals("")) {
                    String mensa = "Faltan Datos";
                    int dura = Toast.LENGTH_SHORT;
                    Toast.makeText(AddActivity.this, mensa, dura).show();
                } else {
                    Equipo nuevoEquipo = new Equipo(nom, ciu, conf, div);
                    String mensa = insertarEquipo(nuevoEquipo);
                    if (mensa.equals("Insertado")) {
                        Intent resulIntent = new Intent();
                        resulIntent.putExtra("equipo", nuevoEquipo);
                        setResult(RESULT_OK, resulIntent);
                    }
                }
                finish();
            }
        });
    }

    protected String insertarEquipo(Equipo equipo) {
        String mensa = "";
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            String myurl = "http://" + ip + "/api.nba.com/v2/insertarEquipo.php";
            Map<String, String> params = new HashMap<>();
            params.put("nombre", equipo.getNombre());
            params.put("ciudad", equipo.getCiudad());
            params.put("conferencia", equipo.getConferencia());
            params.put("division", equipo.getDivision());
            DescargaWeb dw = new DescargaWeb();
            dw.setParams(params);
            try {
                // La llamada a get() tras execute hace que espere hasta que dispongamos
                // de los datos
                dw.execute(myurl).get();
            } catch (ExecutionException | InterruptedException e) {
                Log.d("ErrorBD", e.getMessage());
            }
            mensa = dw.getResultado();
        } else {
            Log.d("ErrorBD", "No se ha podido establecer la conexión");
        }
        return mensa;
    }
}
