package com.example.gabrielrtakeda.ConsultarVoo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gabrielrtakeda on 26/09/2015.
 */
public class CategoriasDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Categorias.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String OPEN_PAR = "(";
    public static final String CLOSE_PAR = ")";

    public static final String SQL_CREATE_ORIGEM =
            "CREATE TABLE " + CategoriasContract.OrigemEntry.TABLE_NAME + OPEN_PAR +
                    CategoriasContract.OrigemEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME + TEXT_TYPE + CLOSE_PAR;
    public static final String SQL_DROP_ORIGEM =
            "DROP TABLE IF EXISTS " + CategoriasContract.OrigemEntry.TABLE_NAME;

    public static final String SQL_CREATE_DESTINO =
            "CREATE TABLE " + CategoriasContract.DestinoEntry.TABLE_NAME + OPEN_PAR +
                    CategoriasContract.DestinoEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME + TEXT_TYPE + CLOSE_PAR;
    public static final String SQL_DROP_DESTINO =
            "DROP TABLE IF EXISTS " + CategoriasContract.DestinoEntry.TABLE_NAME;


    public CategoriasDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ORIGEM);
        db.execSQL(SQL_CREATE_DESTINO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //tabelas de parâmetros que podem ser recriadas
        db.execSQL(SQL_DROP_ORIGEM);
        db.execSQL(SQL_DROP_DESTINO);
        db.execSQL(SQL_CREATE_ORIGEM);
        db.execSQL(SQL_CREATE_DESTINO);
    }

}
