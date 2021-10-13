package com.ggomez.myapplicationsqlite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "tiendaDB";
    public static final int DB_VERSION = 3; // Cambiar version al modificar campos de tabla o al crear nueva tabla
    public static final String TABLE_PRODUCTOS = "productos";
    public static final String TABLE_CATEGORIAS = "categorias";
    public static final String TABLE_USERS = "usuarios";

    // Constructor
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override // Sirve para crear las tablas
    public void onCreate(SQLiteDatabase db) {

        // Categorias
        db.execSQL("create table " + TABLE_CATEGORIAS + "(id integer primary key autoincrement, nombre text not null)");

        // Productos
        db.execSQL("create table " + TABLE_PRODUCTOS + "(id integer primary key autoincrement, nombre text not null, marca text not null, modelo text not null, stock integer not null, precio integer not null, categoria_id integer not null, foreign key (categoria_id) references categorias(id))");

        // Usuarios
        db.execSQL("create table " + TABLE_USERS + "(id integer primary key autoincrement, nombres text not null, apellidos text not null, email text not null, clave text not null, tipo text not null)");
    }

    @Override // Sirve para cuando haya nueva version
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_PRODUCTOS);
        db.execSQL("drop table if exists " + TABLE_CATEGORIAS);
        db.execSQL("drop table if exists " + TABLE_USERS);

        onCreate(db);
    }
}
