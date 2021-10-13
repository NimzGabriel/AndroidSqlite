package com.ggomez.myapplicationsqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ggomez.myapplicationsqlite.models.Categoria;
import com.ggomez.myapplicationsqlite.models.Producto;

import java.util.ArrayList;

// Esta clase nos ayudara a hacer la conexion con la clase Producto
public class DBProducto extends DBHelper {
    Context context;

    // Constructor
    public DBProducto(Context context) {
        super(context);

        this.context = context;
    }

    // INSERT
    public long insertarProducto(Producto p) {
        // Abrir la conexion con la DB
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Escribrir en DB

        // Para guardar los valores que se quieren insertar
        ContentValues values = new ContentValues();
        values.put("nombre", p.getNombre());
        values.put("marca", p.getMarca());
        values.put("modelo", p.getModelo());
        values.put("stock", p.getStock());
        values.put("precio", p.getPrecio());
        values.put("categoria_id", p.getCategoria().getId());
        long result = db.insert(DBHelper.TABLE_PRODUCTOS, null, values);

        return result;
    }

    // UPDATE
    public int actualizarProducto(int id, String nombre, String marca, String modelo, int stock) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Aqui van todos los valores a actualizar
        // Se mandan a la db para sobreescribir lo que ya esta ahi
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("marca", marca);
        values.put("modelo", modelo);
        values.put("stock", stock);
        int result = db.update(TABLE_PRODUCTOS, values, "id = ?", new String[] {String.valueOf(id)});

        return result;
    }

    // DELETE
    public int eliminarProducto(int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete(TABLE_PRODUCTOS, "id = ?", new String[] {String.valueOf(id)});

        return result;
    }

    // GET
    public ArrayList<Producto> getProductos() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // Leer en DB

        ArrayList<Producto> productos = new ArrayList<>();

        // Para leer las tablas de la db se usa un Cursor
        Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLE_PRODUCTOS, null);
        DBCategoria dbCategoria = new DBCategoria(context);

        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = dbCategoria.getCategoria(cursor.getInt(6));
                // Se crea un producto y se obtiene los datos a traves del cursor con numero columna
                Producto producto = new Producto(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        categoria
                );
                productos.add(producto);

            } while(cursor.moveToNext());
        }

        return productos;
    }

    public ArrayList<Producto> filtrarPorCategoria(int categoria_id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Producto> productos = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from productos where categoria_id = ?", new String[] {String.valueOf(categoria_id)});

        DBCategoria dbCategoria = new DBCategoria(context);
        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = dbCategoria.getCategoria(cursor.getInt(6));
                Producto producto = new Producto(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        categoria
                );
                productos.add(producto);

            } while(cursor.moveToNext());
        }

        return productos;
    }

}
