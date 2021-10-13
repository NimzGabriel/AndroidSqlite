package com.ggomez.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ggomez.myapplicationsqlite.adapters.ProductoAdapter;
import com.ggomez.myapplicationsqlite.models.Categoria;
import com.ggomez.myapplicationsqlite.models.Producto;
import com.ggomez.myapplicationsqlite.sqlite.DBCategoria;
import com.ggomez.myapplicationsqlite.sqlite.DBProducto;

import java.util.ArrayList;

public class ListadoProducto extends AppCompatActivity {
    RecyclerView recyclerView;
    Spinner spinnerFiltro;

    public void cargarSpinner() {
        DBCategoria dbCategoria = new DBCategoria(getApplicationContext());
        ArrayList<Categoria> categorias = dbCategoria.getCategorias();

        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                categorias
        );

        if(categorias != null) {
            spinnerFiltro.setAdapter(adapter);
        }
    }

    public void cargarFiltro() {
        DBProducto dbProducto = new DBProducto(getApplicationContext());

        DBCategoria dbCategoria = new DBCategoria(getApplicationContext());
        ArrayList<Categoria> categorias = dbCategoria.getCategorias();
        int categoria_id = 0;

        String categoriaSeleccionada = spinnerFiltro.getSelectedItem().toString();
        for(Categoria c : categorias) {
            if(c.getNombre().equals(categoriaSeleccionada)) {
                categoria_id = c.getId();
                break;
            }
        }

        ArrayList<Producto> productos = dbProducto.filtrarPorCategoria(categoria_id);

        if(productos.isEmpty() == false) {
            ProductoAdapter productoAdapter = new ProductoAdapter(productos);
            recyclerView.setAdapter(productoAdapter);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_producto);

        spinnerFiltro = findViewById(R.id.spinnerFiltro);
        cargarSpinner();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        spinnerFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                cargarFiltro();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}