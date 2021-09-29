package com.example.ej_actionbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String app = "";
        switch (item.getItemId()) {
            case R.id.action_settings:
                app = "com.android.settings";
                break;
            case R.id.action_calculadora:
                app = "com.android.calculator2";
                break;
            case R.id.action_calendar:
                app = "com.android.calendar";
                break;
            case R.id.action_camera:
                app = "com.android.camera";
                break;
            case R.id.action_call:
                app = "com.android.dialer";
                break;
        }
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(app);
        if (intent != null) {
            startActivity(intent);
        } else {
            String mensaje = "Aplicación no disponible";
            Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}