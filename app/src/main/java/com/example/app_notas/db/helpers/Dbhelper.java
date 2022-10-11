package com.example.app_notas.db.helpers;

import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelperHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Calificaciones.db";

    public DbHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ContactosSchemaSQL.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ContactosSchemaSQL.SQL_DELETE);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onUpgrade(sqLiteDatabase, i, i1);
    }
}
