package com.politecnicomalaga.tienda;

import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.politecnicomalaga.tienda.controller.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


    //Acciones

    //El botón de añadir producto
    public void listProduct(View v) {
        Controlador.getSingleton(this).listarTodosProductos();
    }
    public void listClients(View v){
        Controlador.getSingleton(this).listarTodosClientes();
    }

    public void reaccionar(String error, String tipo) {

        if (error.isEmpty()) {
            List<Map<String, String>> datos= null;
            if (tipo.equals("clientes")){
                datos = Controlador.getSingleton(this).getDataClientes();
            } else {
                datos = Controlador.getSingleton(this).getDataProductos();
            }
            //Mostrarlos
            ListView miListaEnPantalla = findViewById(R.id.listaProductos);

            ArrayAdapter<String> miAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            for (Map<String, String> res : datos) {
                String resultado="";
                if (tipo.equals("clientes")){
                     resultado = res.get("d") + " - " + res.get("n") + " - " + res.get("a") + " - " + res.get("e")+ " - " + res.get("t")+ " - " + res.get("dir");
                } else {
                     resultado = res.get("id") + " - " + res.get("d") + " - " + res.get("p");

                }
                miAdapter.add(resultado);
            }

            miListaEnPantalla.setAdapter(miAdapter);
        } else {
            ListView miListaEnPantalla = findViewById(R.id.listaProductos);

            ArrayAdapter<String> miAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            miAdapter.add(error);
            miListaEnPantalla.setAdapter(miAdapter);
        }
    }
}