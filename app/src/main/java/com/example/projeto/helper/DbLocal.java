package com.example.projeto.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbLocal extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "DB_LOCAL";
    public static String TABELA_LOCAL ="USUARIOS";
    public DbLocal(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_LOCAL
                +  " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL," +
                " email VARCHAR UNIQUE NOT NULL, " +
                " senha INTEGER NOT NULL, " +
                " cep INTEGER NOT NULL ," +
                " rua VARCHAR NOT NULL ); ";
        try {
            db.execSQL(sql);
            Log.i("INFO DB","Sucesso!");

        }catch (Exception e){
            Log.i("INFO DB","Erro");

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
