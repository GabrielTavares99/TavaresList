package com.ueuo.gabrieltavares.exemplo_listwiew_spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import com.ueuo.gabrieltavares.exemplo_listwiew_spinner.dao.ItemDAO;
import com.ueuo.gabrieltavares.exemplo_listwiew_spinner.modelo.Item;

import java.util.List;

public class act_main extends AppCompatActivity implements View.OnClickListener{

    private EditText txt_Item;
    private Spinner spn_Opcoes;
    private Button btn_adicionar, btn_remover;
    private ListView list_Itens;

    //Classe genética de array para add itens
    private ArrayAdapter<String> adpOpcoes;
    private ArrayAdapter<Item> adpItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);

        txt_Item = (EditText) findViewById(R.id.txt_item);
        spn_Opcoes = (Spinner) findViewById(R.id.spin_opcoes);
        btn_adicionar = (Button) findViewById(R.id.btn_adicionar);
        btn_remover = (Button) findViewById(R.id.btn_remover);
        list_Itens = (ListView) findViewById(R.id.list_itens);

        btn_remover.setOnClickListener(this);
        btn_adicionar.setOnClickListener(this);

        //Dou a forma/layout para o spinner
        adpOpcoes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // Como vai ficar quando o spinner é clicado
        adpOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Relacionando o spinner com o arrayAdapter
        spn_Opcoes.setAdapter(adpOpcoes);

        adpOpcoes.add("1 unidade");
        adpOpcoes.add("2 unidades");
        adpOpcoes.add("3 unidades");
        adpOpcoes.add("4 unidades");
        adpOpcoes.add("5 unidades");

        //Só exibe uma linha no meu listView
        adpItens = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1);
        list_Itens.setAdapter(adpItens);

        ItemDAO itemDAO = new ItemDAO(this);

        List<Item> listaItens = itemDAO.buscaItens();

        itemDAO.close();

        for (Item item : listaItens){
            adpItens.add(item);
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_adicionar:

                Item item = new Item();
                item.setDescricao(txt_Item.getText().toString());

                ItemDAO itemDAO = new ItemDAO(this);
                itemDAO.insere(item);

                itemDAO.close();

                //String itemAdd = txt_Item.getText().toString();
                //itemAdd += " - "+ spn_Opcoes.getSelectedItem();
                //adpItens.add(itemAdd);

                break;
            case R.id.btn_remover:
                if (adpItens.getCount() > 0){
                   // String item2 = adpItens.getItem(adpItens.getCount()-1);
                    //adpItens.remove(item2);
                }
                break;

            default:
                break;
        }

        txt_Item.setText("");


    }
}
