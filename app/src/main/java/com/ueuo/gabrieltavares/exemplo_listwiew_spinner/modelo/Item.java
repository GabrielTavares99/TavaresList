package com.ueuo.gabrieltavares.exemplo_listwiew_spinner.modelo;

/**
 * Created by gabri on 19/05/2017.
 */

public class Item {
    private String descricao ;
    private int id;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
