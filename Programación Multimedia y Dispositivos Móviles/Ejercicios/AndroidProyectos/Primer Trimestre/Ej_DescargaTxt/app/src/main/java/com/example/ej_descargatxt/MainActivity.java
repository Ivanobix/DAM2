package com.example.ej_descargatxt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Este ejercicio no funciona, olvídalo, quitado de Android.
        TextView textURL = (TextView) findViewById(R.id.textURL);
        Button botDescarga = (Button) findViewById(R.id.botDescarga);
        EditText editURL = (EditText) findViewById(R.id.editURL);

        botDescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    URL url = new URL(editURL.getText().toString().trim());
                    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                    con.setReadTimeout(10000);
                    con.setConnectTimeout(15000);
                    con.setRequestMethod("GET");
                    con.setDoInput(true);
                    con.connect();
                    /*
                    InputStream is = con.getInputStream();
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    int i;
                    while ((i = is.read()) != -1) {
                        os.write(i);
                    }
                    String result = os.toString("iso-8859-1");
                    textURL.setText(result);
                    is.close();

                     */
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Algo ha ido mal", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}