package com.example.app_notas.db.helpers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sqlite_mio.db.contracts.DbReaderContract.CalificacionSchema;
import com.example.app_notas.db.models.Nota;
import com.example.app_notas.db.sqls.DbReadersSQL;

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
    public Nota verContactos(int id){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Nota nota = null;
        Cursor cursornotas;
        cursornotas=db.rawQuery("SELECT * FROM calificaciones WHERE _id = " +id+ " LIMIT 1", null);
        if(cursornotas.moveToFirst()){
                nota=new Nota();
                nota.setId(cursornotas.getInt(0));
                nota.setMateria(cursornotas.getString(1));
                nota.setCalificacion(cursornotas.getDouble(2));

        }
        cursornotas.close();
        return nota;

    }
    public ArrayList<Nota> mostrarNotaConMateria(String mat){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Nota> listaNotas = new ArrayList<>();
        Nota nota = null;
        Cursor cursornotas = null;

        cursornotas=db.rawQuery("SELECT * FROM calificaciones WHERE materia = '" +mat+"'", null);
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

    public boolean editNota(Nota nota) {
        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(DbReadersSQL.ContactosSchemaSQL.SQL_UPDATE+nota.getCalificacion()+" WHERE _id='"+nota.getId()+"'");
            correcto=true;
        } catch (Exception ex) {
            System.err.println(ex);
            correcto=false;

        }finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarNota(int id ) {
        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db1 = dbHelper.getWritableDatabase();
        String k=DbReadersSQL.ContactosSchemaSQL.SQL_DELETE_ROW+id+"'";
        System.out.println(k);
        db1.execSQL(k);
        try {

            correcto=true;
        } catch (Exception ex) {
            System.err.println(ex);
            correcto=false;

        }finally {
            db1.close();
        }
        return correcto;
    }

}