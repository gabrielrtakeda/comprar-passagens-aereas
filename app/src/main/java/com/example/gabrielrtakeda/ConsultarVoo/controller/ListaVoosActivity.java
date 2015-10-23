package com.example.gabrielrtakeda.ConsultarVoo.controller;

/**
 * Created by gabrielrtakeda on 11/09/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.gabrielrtakeda.atividade_arqdsis02_consultarvoo.R;
import com.example.gabrielrtakeda.ConsultarVoo.model.Voos;
import com.example.gabrielrtakeda.ConsultarVoo.adapter.VoosAdapter;

public class ListaVoosActivity extends ActionBarActivity {
    ListView listView;
    Activity atividade;
    public final static String VOOS = "com.example.gabrielrtakeda.VOOS";
    Voos[] voos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_voos);
        atividade = this;

        //pega a mensagem do intent
        Intent intent = getIntent();
        voos = ((ArrayList<Voos>)intent.getSerializableExtra(MainActivity.VOOS)).toArray(new Voos[0]);

        //cria o listview de cervejas
        listView = (ListView) findViewById(R.id.view_lista_voos);

        VoosAdapter adapter = new VoosAdapter(this, voos);

        listView.setAdapter(adapter);

        // listener de click em um item do listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheVoosActivity.class);
                intent.putExtra(VOOS, voos[position]);

                startActivity(intent);

            }

        });
    }

}
