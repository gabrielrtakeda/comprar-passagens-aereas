package com.example.gabrielrtakeda.ConsultarVoo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by gabrielrtakeda on 26/09/2015.
 */
public class CategoriasDb {
    CategoriasDbHelper dbHelper;
    public static final String ORIGEM = "origem";
    public static final String DESTINO = "destino";

    public CategoriasDb(Context context){
        dbHelper = new CategoriasDbHelper(context);
    }

    public void insereOrigem(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME, "Brasil - São Paulo");
        db.insert(CategoriasContract.OrigemEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME, "Brasil - Rio de Janeiro");
        db.insert(CategoriasContract.OrigemEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME, "Brasil - Brasilia");
        db.insert(CategoriasContract.OrigemEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME, "Brasil - Curitiba");
        db.insert(CategoriasContract.OrigemEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME, "Brasil - Florianopolis");
        db.insert(CategoriasContract.OrigemEntry.TABLE_NAME, null, values);
    }

    public void insereDestino(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Eua - Orlando");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Africa do Sul - Joanesburgo");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Alemanha - Berlin");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Argentina - Buenos Aires");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Australia - Sidney");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Canada - Vancouver");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Inglaterra - Londres");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Japão - Toquio");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Brasil - São Paulo");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME, "Brasil - Rio de Janeiro");
        db.insert(CategoriasContract.DestinoEntry.TABLE_NAME, null, values);
    }


    public ArrayList<String> selecionaOrigem(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Escolha a Origem");

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {CategoriasContract.OrigemEntry._ID,
                CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME};

        String ordem = CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME;

        Cursor c = db.query(CategoriasContract.OrigemEntry.TABLE_NAME, colunas, null, null, ordem, null, null );

        while(c.moveToNext()){
            lista.add(c.getString(c.getColumnIndex(CategoriasContract.OrigemEntry.COLUMN_NAME_ORIGEM_NOME)));
        }

        c.close();

        return lista;
    }


    public ArrayList<String> selecionaDestino(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Escolha o Destino");

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME};

        String ordem = CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME;

        Cursor c = db.query(CategoriasContract.DestinoEntry.TABLE_NAME, colunas, null, null, null, null, ordem);

        while(c.moveToNext()){
            lista.add(c.getString(c.getColumnIndex(CategoriasContract.DestinoEntry.COLUMN_NAME_DESTINO_NOME)));
        }

        c.close();

        return lista;
    }
}
