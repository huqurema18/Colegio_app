package com.example.sqlite_mio.db.contracts;

import android.provider.BaseColumns;

public class DbReaderContract {
    private DbReaderContract(){}

    public static class CalificacionSchema implements BaseColumns {
        public static final String TABLE_NAME= "calificaciones";
        public  static final String COLUMN_NAME_MATERIA = "materia";
        public  static final String COLUMN_NAME_CALIFICACION = "calificacion";

    }
}