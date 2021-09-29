package com.example.recursosdos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        String[] days = res.getStringArray(R.array.workdays);

        TextView items[] = {
                (TextView) findViewById(R.id.txtUno),
                (TextView) findViewById(R.id.txtDos),
                (TextView) findViewById(R.id.txtTres),
                (TextView) findViewById(R.id.txtCuatro),
                (TextView) findViewById(R.id.txtCinco)
        };

        for (int i = 0; i < items.length; i++) {
            items[i].setText(days[i]);
        }
    }
}