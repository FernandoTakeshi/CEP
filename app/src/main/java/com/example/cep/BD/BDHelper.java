package com.example.cep.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper
{
    static final int        VERSION     = 1;
    static final String     NOME_DB     = "DB_CEP";
    static final String     TABELA_CEP  = "CEP";

    public BDHelper(@Nullable Context context)
    {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABELA_CEP+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "cep            TEXT NOT   NULL, "+
                "logradouro     TEXT NOT   NULL, "+
                "complemento    TEXT , "+
                "bairro         TEXT , "+
                "localidade     TEXT , "+
                "uf TEXT );";

        try
        {
            sqLiteDatabase.execSQL(sql);

            Log.i("Info DB", "Tabela criada com sucesso!");
        }
        catch (Exception e)
        {
            Log.i("Info DB", "ERRO NA CRIAÇÃO DA TABELA "+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String sql = "DROP TABLE IF EXISTS "+TABELA_CEP+";";
        try
        {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("Info DB", "Tabela atualizada com sucesso!");
        }
        catch (Exception e)
        {
            Log.i("Info DB", "ERRO NA ATUALIZAÇÃO DA TABELA "+e.getMessage());
        }
    }

}

