package com.ggomez.myapplicationsqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ggomez.myapplicationsqlite.models.Usuario;

public class DBUsuario extends DBHelper {
    Context context;

    public DBUsuario(Context context) {
        super(context);

        this.context = context;
    }

    public Usuario login(String email, String clave) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios where email = ? and clave = ?", new String[] {email, clave});

        if(cursor.moveToFirst()) {
            Usuario usuario = new Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            return usuario;
        }
        else {
            return null;
        }
    }


}
