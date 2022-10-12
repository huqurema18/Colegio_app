package com.example.app_notas.db.sqls;

import com.example.sqlite_mio.db.contracts.DbReaderContract;
import com.example.sqlite_mio.db.contracts.DbReaderContract.CalificacionSchema;

public final class DbReadersSQL {
    private DbReadersSQL() {
    }

    public static class ContactosSchemaSQL {
        public static final String SQL_CREATE = "CREATE TABLE " + DbReaderContract.CalificacionSchema.TABLE_NAME + "("
                + DbReaderContract.CalificacionSchema._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbReaderContract.CalificacionSchema.COLUMN_NAME_MATERIA + " TEXT,"
                + DbReaderContract.CalificacionSchema.COLUMN_NAME_CALIFICACION + " TEXT"
                + ")";
        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + DbReaderContract.CalificacionSchema.TABLE_NAME;
        public static final String SQL_SELECT = "SELECT * FROM "+ DbReaderContract.CalificacionSchema.TABLE_NAME;
        public static final String SQL_UPDATE = "UPDATE "+ DbReaderContract.CalificacionSchema.TABLE_NAME+ " SET "
                + DbReaderContract.CalificacionSchema.COLUMN_NAME_CALIFICACION+" = ";
        public static final String SQL_DELETE_ROW = "DELETE FROM "+ DbReaderContract.CalificacionSchema.TABLE_NAME+
                " WHERE _id= '";
        //SELECT * FROM DIRECCION WHERE WHERE materia = 'ingles';
    }
}