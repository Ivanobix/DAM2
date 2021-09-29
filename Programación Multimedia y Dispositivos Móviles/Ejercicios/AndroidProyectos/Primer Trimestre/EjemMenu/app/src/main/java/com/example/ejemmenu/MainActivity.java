package com.example.ejemmenu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int MNU_OPC1 = 1;
    private static final int MNU_OPC2 = 2;
    private static final int SMNU_OPC31 = 31;
    private static final int SMNU_OPC32 = 32;
    private static final int MNU_OPC3 = 3;

    protected TextView lblTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblTexto = (TextView) findViewById(R.id.lblTexto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Para hacer el menú basado en el xml menu_main que he creado (siempre las mismas opcioens)
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        // Para hacer el menú dinámico (distintas opciones cada vez)
        menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion1");
        menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, "Opcion2");
        SubMenu smnu = menu.addSubMenu(Menu.NONE, MNU_OPC3, Menu.NONE, "Opcion3");
        smnu.add(Menu.NONE, SMNU_OPC31, Menu.NONE, "Opcion 3.1");
        smnu.add(Menu.NONE, SMNU_OPC32, Menu.NONE, "Opcion 3.2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Usar las constantes como MNU_OPC1 cuando se crea dinámicamente con Java
        // Si lo genero por XML cambiar las constantes por R.menu.id.menuOpc1
        switch (item.getItemId()) {
            case MNU_OPC1:
                lblTexto.setText("Opción 1 seleccionada");
                break;
            case MNU_OPC2:
                lblTexto.setText("Opción 2 seleccionada");
                break;
            case MNU_OPC3:
                lblTexto.setText("Opción 3 seleccionada");
                break;
            case SMNU_OPC31:
            case SMNU_OPC32:
                lblTexto.setText("Opción no implementada");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}