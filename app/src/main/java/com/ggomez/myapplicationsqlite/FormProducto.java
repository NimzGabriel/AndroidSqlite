package com.ggomez.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ggomez.myapplicationsqlite.models.Categoria;
import com.ggomez.myapplicationsqlite.models.Producto;
import com.ggomez.myapplicationsqlite.sqlite.DBCategoria;
import com.ggomez.myapplicationsqlite.sqlite.DBProducto;

public class FormProducto extends AppCompatActivity {
    EditText editTextNombreProducto, editTextMarcaProducto, editTextModeloProducto, editTextPrecioProducto, editTextStockProducto;
    Button buttonGuardarProducto;
    Spinner spinnerCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_producto);

        editTextNombreProducto = findViewById(R.id.editTextNombreProducto);
        editTextMarcaProducto = findViewById(R.id.editTextMarcaProducto);
        editTextModeloProducto = findViewById(R.id.editTextModeloProducto);
        editTextPrecioProducto = findViewById(R.id.editTextPrecioProducto);
        editTextStockProducto = findViewById(R.id.editTextStockProducto);
        buttonGuardarProducto = findViewById(R.id.buttonGuardarProducto);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        cargarSpinner();

        buttonGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombreProducto.getText().toString();
                String marca = editTextMarcaProducto.getText().toString();
                String modelo = editTextModeloProducto.getText().toString();
                int precio = Integer.parseInt(editTextPrecioProducto.getText().toString());
                int stock = Integer.parseInt(editTextStockProducto.getText().toString());
                Categoria categoria = (Categoria) spinnerCategorias.getSelectedItem();

                Producto p = new Producto(nombre, marca, modelo, stock, precio, categoria);
                DBProducto dbProducto = new DBProducto(getApplicationContext());
                long id = dbProducto.insertarProducto(p);

                if(id >= 0) {
                    Toast.makeText(getApplicationContext(), "Nuevo producto insertado exitosamente", Toast.LENGTH_LONG).show();
                    editTextNombreProducto.setText("");
                    editTextMarcaProducto.setText("");
                    editTextModeloProducto.setText("");
                    editTextPrecioProducto.setText("");
                    editTextStockProducto.setText("");
                    spinnerCategorias.setSelection(0);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error al insertar", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void cargarSpinner() {
        DBCategoria dbCategoria = new DBCategoria(getApplicationContext());
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                dbCategoria.getCategorias()
        );

        if(dbCategoria.getCategorias() != null) {
            spinnerCategorias.setAdapter(adapter);
        }
    }
}