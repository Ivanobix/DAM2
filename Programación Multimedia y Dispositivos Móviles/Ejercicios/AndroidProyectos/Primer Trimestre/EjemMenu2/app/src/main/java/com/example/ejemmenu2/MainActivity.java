package com.example.ejemmenu2;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected TextView lblMenu;
    protected TextView lblMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblMenu = (TextView) findViewById(R.id.lblMenu);
        lblMensaje = (TextView) findViewById(R.id.lblMensaje);
        registerForContextMenu(lblMenu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //En caso de haber varios menús hacer switch(v.getId())
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_eticontextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Para añadir un titulo al menú que se abre usar menu.setHeaderTitle("Titulo")
        switch (item.getItemId()) {
            case R.id.opcBorrar:
                lblMensaje.setText("");
                return true;
            case R.id.opcEscribir:
                lblMensaje.setText("Mensaje");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}