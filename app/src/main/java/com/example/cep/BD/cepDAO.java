package com.example.cep.BD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cep.Interface.IDbdCEP;

import java.util.ArrayList;
import java.util.List;

public class cepDAO implements IDbdCEP
{
        private SQLiteDatabase leitura;


    private SQLiteDatabase escrita;


        public cepDAO(Context context) {
            BDHelper bdHelper = new BDHelper(context);
            leitura = bdHelper.getReadableDatabase();
            escrita = bdHelper.getWritableDatabase();
        }

        @Override
        public boolean salvar(CEP dbcep) {
            ContentValues contentValues = new ContentValues();
            //ContentValues.put("resp", dbcep.getCep());


            contentValues.put("cep", dbcep.getCep());
            contentValues.put("complemento", dbcep.getComplemento());
            contentValues.put("bairro", dbcep.getBairro());
            contentValues.put("localidade", dbcep.getLocalidade());
            contentValues.put("uf", dbcep.getUf());
            //contentValues.put("id",dbCep.id);

//*/
            this.escrita.insert(BDHelper.TABELA_CEP, null, contentValues);
            return true;
        }


        @Override
        public boolean atualizar(CEP dbCep) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("logradouro", dbCep.getLogradouro());
            contentValues.put("cep", dbCep.getCep());
            contentValues.put("complemento", dbCep.getComplemento());
            contentValues.put("bairro", dbCep.getBairro());
            contentValues.put("localidade", dbCep.getLocalidade());
            contentValues.put("uf", dbCep.getUf());

            String[] args = {String.valueOf(dbCep.getId())};

            this.escrita.update(BDHelper.TABELA_CEP, contentValues, "id=?", args);

            return true;
        }

        @Override
        public boolean deletar(CEP dbCep) {
            String[] args = {String.valueOf(dbCep.getId())};

           this.escrita.delete(BDHelper.TABELA_CEP, "id=?", args);

            return true;
        }


        @SuppressLint("Range")
        @Override
        public List<CEP> listar() {
            List<CEP> dbCeps = new ArrayList<>();
            String sql = "SELECT * FROM " + BDHelper.TABELA_CEP + "; ";
            Cursor cursor = leitura.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                Long id = cursor.getLong(cursor.getColumnIndex("id"));
                String logradouro = cursor.getString(cursor.getColumnIndex("logradouro"));
                String cep = cursor.getString(cursor.getColumnIndex("cep"));
                String complemento = cursor.getString(cursor.getColumnIndex("complemento"));
                String bairro = cursor.getString(cursor.getColumnIndex("bairro"));
                String localidade = cursor.getString(cursor.getColumnIndex("localidade"));
                String uf = cursor.getString(cursor.getColumnIndex("uf"));
            }

            return dbCeps;
        }
}
