package com.example.ejdescargatxtasincrona;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    protected TextView textURL;
    protected EditText editURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos la referencia al TextView donde se muestra el resultado y establecemos scroll
        textURL = (TextView) findViewById(R.id.textURL);
        textURL.setMovementMethod(new ScrollingMovementMethod());

        // Obtenemos la referencia al EditText donde se recoge la URL y nos situamos al final
        editURL = (EditText) findViewById(R.id.editURL);
        editURL.setSelection(editURL.getText().length());

        // Obtenemos la referencia al Botón Descargar e implementamos su acción
        Button botDescarga = (Button) findViewById(R.id.botDescarga);
        botDescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editURL.getText().toString().trim();
                if (!url.equals("") && URLUtil.isValidUrl(url)) {
                    ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                    if (netInfo != null && netInfo.isConnected()) {
                        new DescargaWeb().execute(url);
                    } else {
                        // Alerta: no se puede establecer la conexión
                        int duracion = Toast.LENGTH_SHORT;
                        String mensa = "No se ha podido establecer la conexión";
                        Toast alerta = Toast.makeText(MainActivity.this, mensa, duracion);
                        alerta.show();
                    }
                } else {
                    // Alerta: falta URL o formato incorrecto
                    int duracion = Toast.LENGTH_SHORT;
                    String mensa = "URL incorrecta";
                    Toast alerta = Toast.makeText(MainActivity.this, mensa, duracion);
                    alerta.show();
                }
            }
        });
    }

    private class DescargaWeb extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return descargaUrl(urls[0]);
            } catch (IOException e) {
                return "URL inexistente";
            }
        }

        private String descargaUrl(String myurl) throws IOException {
            InputStream is = null;
            try {
                // Preparamos la conexión
                URL url = new URL(myurl);
                HttpURLConnection con;
                if (myurl.startsWith("https")) {
                    con = (HttpsURLConnection) url.openConnection();
                } else {
                    con = (HttpURLConnection) url.openConnection();
                }
                con.setReadTimeout(10000); // milisegundos
                con.setConnectTimeout(15000); // milisegundos
                con.setRequestMethod("GET");
                con.setDoInput(true);
                // Descargamos los datos
                con.connect();
                int response = con.getResponseCode();
                is = con.getInputStream();
                // Convertimos los datos obtenidos (InputStream) a String
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int i;
                while ((i = is.read()) != -1) {
                    os.write(i);
                }
                // Convertimos al codigo de caracteres deseado antes de devolverlos
                // String result = os.toString("UTF-8");
                String result = os.toString("iso-8859-1");
                return result;
            } catch (IOException e) {
                return "Error de lectura";
            } finally {
                // Cerramos el InputStream
                if (is != null) {
                    is.close();
                }
            }
        }

        @Override
        protected void onPostExecute(String result) {
            textURL.setText(result);
        }
    }
}