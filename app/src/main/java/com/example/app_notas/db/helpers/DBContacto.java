package com.example.app_notas.db.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBContacto extends DbHelper {

    private Context context;

    public DBContacto(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insert(Nota nota) {
        try {
            DbHelper dbHelper = new DbHelper(this.context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(CalificacionSchema.COLUMN_NAME_MATERIA, nota.getMateria());
            contentValues.put(CalificacionSchema.COLUMN_NAME_CALIFICACION, nota.getCalificacion());
            return  db.insert(CalificacionSchema.TABLE_NAME, null, contentValues);
        } catch (Exception ex) {
            System.err.println(ex);
            return 0;
        }
    }
    public ArrayList<Nota> mostrarContactos(){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Nota> listaNotas = new ArrayList<>();
        Nota nota = null;
        Cursor cursornotas = null;

        cursornotas=db.rawQuery(DbReadersSQL.ContactosSchemaSQL.SQL_SELECT, null);
        if(cursornotas.moveToFirst()){
            do{
                nota=new Nota();
                nota.setId(cursornotas.getInt(0));
                nota.setMateria(cursornotas.getString(1));
                nota.setCalificacion(cursornotas.getDouble(2));
                listaNotas.add(nota);
            }while (cursornotas.moveToNext());
        }
        cursornotas.close();
        return listaNotas;

    }
}
