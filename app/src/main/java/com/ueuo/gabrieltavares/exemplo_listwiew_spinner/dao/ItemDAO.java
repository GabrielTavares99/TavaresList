package com.ueuo.gabrieltavares.exemplo_listwiew_spinner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ueuo.gabrieltavares.exemplo_listwiew_spinner.modelo.Item;

/**
 * Created by gabri on 19/05/2017.
 */

public class ItemDAO extends SQLiteOpenHelper{

    public ItemDAO(Context context) {
        //CONTEXT, BANCO, FACTORY E VERSÃO DO BANCO DE DADOS
        super(context, "bd_tavares_list",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CRIAR A TABELA
        String sql = "CREATE TABLE IF NOT EXISTS tb_itens (id INTEGER PRIMARY KEY AUTOINCREMENT, descricao VARCHAR (200) NOT NULL)";
        db.execSQL(sql);
    }

    //QUANDO MUDANMOS O NUMERO DA VERSÃO DO BANCO DE DADOS, É CHAMADO ESSE MÉTODO PARA RECRIA-LO
    //DIDATICAMENTE QUANDO ERRAR O ALGO NA TABELA, MUDAREI O NUMERO DE VERSAO E FACILMENTE SERA RECRIADO
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tb_itens";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Item item) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("descricao",item.getDescricao());

        db.insertOrThrow("tb_itens",null,dados);

    }
}
