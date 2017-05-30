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
    private Button btn_adicionar;
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

        list_Itens = (ListView) findViewById(R.id.list_itens);

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

        registerForContextMenu(list_Itens);

    }

    private void carregaLista() {

        adpItens.clear();

        ItemDAO itemDAO = new ItemDAO(this);

        List<Item> listaItens = itemDAO.buscaItens();

        itemDAO.close();

        for (Item item : listaItens){
            adpItens.add(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        //CRIANDO ITEM DO MENU
        MenuItem item_Deletar = menu.add("DELETAR");
        //DOU UMA AÇÃO PRA ELE
        item_Deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                //RECUPERO O ITEM SELECIONADO - MENU INFO INFORMA PRA GENTE
                Item itemRecuperado = (Item) list_Itens.getItemAtPosition(info.position);

                ItemDAO itemDAO = new ItemDAO(act_main.this);
                if (itemDAO.delete(itemRecuperado)){
                    itemDAO.close();
                    Toast.makeText(act_main.this,"Deletar a tarefa "+itemRecuperado.getDescricao(),Toast.LENGTH_LONG).show();
                    carregaLista();
                }else {
                    Toast.makeText(act_main.this,"Falha Catastrófica!",Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
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


                carregaLista();
                //String itemAdd = txt_Item.getText().toString();
                //itemAdd += " - "+ spn_Opcoes.getSelectedItem();
                //adpItens.add(itemAdd);

                break;
            default:
                break;
        }

        txt_Item.setText("");


    }
}
