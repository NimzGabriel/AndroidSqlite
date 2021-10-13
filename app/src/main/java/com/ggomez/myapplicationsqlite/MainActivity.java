package com.ggomez.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ggomez.myapplicationsqlite.models.Producto;
import com.ggomez.myapplicationsqlite.sqlite.DBHelper;
import com.ggomez.myapplicationsqlite.sqlite.DBProducto;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        spinner = findViewById(R.id.spinner);

        DBProducto dbProducto = new DBProducto(this);
        ArrayAdapter<Producto> productosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbProducto.getProductos());
        spinner.setAdapter(productosAdapter);

    }
}