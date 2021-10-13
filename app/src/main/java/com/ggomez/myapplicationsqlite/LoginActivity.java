package com.ggomez.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.myapplicationsqlite.models.Usuario;
import com.ggomez.myapplicationsqlite.sqlite.DBUsuario;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextClave;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextClave = findViewById(R.id.editTextClave);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sacar datos de campos
                String email = editTextEmail.getText().toString();
                String clave = editTextClave.getText().toString();

                // Comparar con la DB Usuarios
                DBUsuario dbUsuario = new DBUsuario(getApplicationContext());
                Usuario usuario = dbUsuario.login(email, clave);

                if(usuario != null) {
                    Toast.makeText(getApplicationContext(), "Bienvenido, " + usuario.getNombres(), Toast.LENGTH_LONG).show();
                    if(usuario.getTipo().equals("admin")) {
                        // Abrir pantalla Admin
                    }
                    else if(usuario.getTipo().equals("cliente")) {
                        // Abrir pantalla Cliente
                        startActivity(new Intent(getApplicationContext(), ListadoProducto.class));
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Credenciales no válidas", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}