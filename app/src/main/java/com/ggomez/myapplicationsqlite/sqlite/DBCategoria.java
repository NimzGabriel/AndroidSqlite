package com.ggomez.myapplicationsqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ggomez.myapplicationsqlite.models.Categoria;

import java.util.ArrayList;

public class DBCategoria extends DBHelper {
    Context context;

    public DBCategoria(Context context) {
        super(context);

        this.context = context;
    }

    // GET - Para obtener una categoria
    public Categoria getCategoria(int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLE_CATEGORIAS + " where id = ?", new String[] {String.valueOf(id)});

        if(cursor.moveToFirst()) {
            Categoria categoria = new Categoria(
                    cursor.getInt(0),
                    cursor.getString(1)
            );

            return categoria;
        }
        else {
            return null;
        }
    }

    public ArrayList<Categoria> getCategorias() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Categoria> categorias = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLE_CATEGORIAS, null);

        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = new Categoria(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                categorias.add(categoria);
            } while(cursor.moveToNext());

            return categorias;
        }
        else {
            return null;
        }
    }

    // INSERT
    public long insertarCategoria(Categoria c) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", c.getNombre());
        long idResult = db.insert(DBHelper.TABLE_CATEGORIAS, null, values);

        return idResult;
    }


}
