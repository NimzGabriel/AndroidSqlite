package com.ggomez.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.myapplicationsqlite.models.Categoria;
import com.ggomez.myapplicationsqlite.sqlite.DBCategoria;

public class FormCategoria extends AppCompatActivity {
    EditText editTextNombre;
    Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria);

        editTextNombre = findViewById(R.id.editTextNombre);
        buttonGuardar = findViewById(R.id.buttonGuardar);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombre.getText().toString();
                Categoria c = new Categoria(nombre);
                DBCategoria dbCategoria = new DBCategoria(getApplicationContext());
                long id = dbCategoria.insertarCategoria(c);

                if(id >= 0) {
                    Toast.makeText(getApplicationContext(), "Categoria " + nombre + " insertada exitosamente", Toast.LENGTH_LONG).show();
                    editTextNombre.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error al insertar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}