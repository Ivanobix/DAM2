package com.example.ejemwebview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected WebView webMarco;
    protected EditText txtUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuerda añadir los permisos de internet en el manifiesto
        webMarco = (WebView) findViewById(R.id.webMarco);
        txtUrl = (EditText) findViewById(R.id.txtUrl);
        Button btnMostrar = (Button) findViewById(R.id.btnMostrar);

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = txtUrl.getText().toString().trim();
                if (!url.equals("")) {
                    //Para ocultar el teclado y que no moleste;
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //Para cargar la web
                    webMarco.loadUrl(url);
                } else {
                    String mensaje = "Debes introducir la URL";
                    Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}