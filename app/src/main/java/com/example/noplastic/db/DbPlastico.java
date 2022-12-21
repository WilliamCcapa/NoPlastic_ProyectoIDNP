package com.example.noplastic.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.noplastic.InformacionPlasticos.ListPlasticos;

import java.util.ArrayList;

public class DbPlastico extends DbHelper{

    Context  context;

    public DbPlastico(@Nullable Context context) {
        super(context);
  this.context = context;
    }

    public long registrarPlastico(String nombre, String fecha, String origen, String ubicacion, String descripcion, String categoriaPL) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //funcion para insertar valores
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("fecha", fecha);
            values.put("origen", origen);
            values.put("ubicacion", ubicacion);
            values.put("descripcion", descripcion);
            values.put("categoriaPL", categoriaPL);

            // metodo insert devuelve el id del object insertado a la tabla
            id = db.insert(TABLE_PLASTIC, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }
    public ArrayList<ListPlasticos> mostrarPlasticos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<ListPlasticos> listaPlasticos = new ArrayList<>();
        ListPlasticos misPlasticos;
        Cursor cursorPlasticos;

        cursorPlasticos= db.rawQuery("SELECT * FROM " + TABLE_PLASTIC , null);

        if (cursorPlasticos.moveToFirst()) {
            do {
                misPlasticos = new ListPlasticos();
                misPlasticos.setId(cursorPlasticos.getInt(0));
                misPlasticos.setNombre(cursorPlasticos.getString(1));
                misPlasticos.setFecha(cursorPlasticos.getString(2));
                misPlasticos.setOrigen(cursorPlasticos.getString(3));
                misPlasticos.setUbicacion(cursorPlasticos.getString(4));
                misPlasticos.setDescripcion(cursorPlasticos.getString(5));
                misPlasticos.setCategoria(cursorPlasticos.getString(6));
                listaPlasticos.add(misPlasticos);
            } while (cursorPlasticos.moveToNext());
        }

        cursorPlasticos.close();

        return listaPlasticos;

    }
    public ListPlasticos verContacto(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ListPlasticos plastico = null;
        Cursor cursorplasticos;

        cursorplasticos = db.rawQuery("SELECT * FROM " + TABLE_PLASTIC + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorplasticos.moveToFirst()) {
            plastico = new ListPlasticos();
            plastico.setId(cursorplasticos.getInt(0));
            plastico.setNombre(cursorplasticos.getString(1));
        }

        cursorplasticos.close();

        return plastico;
    }

    public boolean eliminarContacto(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PLASTIC + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        mostrarPlasticos();
        return correcto;
    }
}
