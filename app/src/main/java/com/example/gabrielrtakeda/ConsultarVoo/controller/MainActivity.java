package com.example.gabrielrtakeda.ConsultarVoo.controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


import com.example.gabrielrtakeda.ConsultarVoo.R;
import com.example.gabrielrtakeda.ConsultarVoo.data.CategoriasDb;
import com.example.gabrielrtakeda.ConsultarVoo.model.Voos;
import com.example.gabrielrtakeda.ConsultarVoo.network.VoosRequester;

public class MainActivity extends ActionBarActivity{

    Spinner spinnerOrigem;
    Spinner spinnerDestino;
    Button btnConsultar;
    String origem, destino;
    ArrayList<Voos> voos;
    final String servidor = "jbossews-voos.rhcloud.com";
    //final String servidor = "10.0.2.2:8080/arqdesis_json";
    VoosRequester requester;
    ProgressBar mProgress;
    Intent intent;
    Context contexto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.contexto = this;
        setupViews();

    }

    protected void onRestart(){
        super.onRestart();
        spinnerOrigem.setSelection(0);
        spinnerDestino.setSelection(0);
    }

    private void setupViews() {
        origem = "";
        destino = "";
        btnConsultar = (Button) findViewById(R.id.button_send);

        mProgress = (ProgressBar) findViewById(R.id.carregando);

        spinnerOrigem = (Spinner) findViewById(R.id.dropdown_origens);
        new CarregaSpinnerOrigem().execute(CategoriasDb.ORIGEM);
        spinnerOrigem.setOnItemSelectedListener(new OrigemSelecionada());

        spinnerDestino = (Spinner) findViewById(R.id.dropdown_destinos);
        new CarregaSpinnerDestino().execute(CategoriasDb.DESTINO);
        spinnerDestino.setOnItemSelectedListener(new DestinoSelecionado());

        mProgress.setVisibility(View.INVISIBLE);
    }

    private class OrigemSelecionada implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            origem = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    private class DestinoSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            destino = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    // constante static para identificar o parametro
    public final static String VOOS = "com.example.gabrielrtakeda.VOOS";
    //será chamado quando o usuário clicar em enviar
    public void consultarVoos(View view) {
        final String pOrigem = this.origem.equals("Escolha a origem")?"":origem;
        final String pDestino = this.destino.equals("Escolha o destino")?"":destino;


        requester = new VoosRequester();
        if(requester.isConnected(this)) {
            intent = new Intent(this, ListaVoosActivity.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        voos = requester.get("http://" + servidor + "/selecao.json", pOrigem, pDestino);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(VOOS, voos);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private class CarregaSpinnerOrigem extends AsyncTask<String, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            CategoriasDb db = new CategoriasDb(contexto);
            ArrayList<String> lista = db.selecionaOrigem();
            if(lista.size() == 1)
                db.insereOrigem();
            lista = db.selecionaOrigem();
            return lista;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result){
            ArrayAdapter<String> origemAdapter = new ArrayAdapter<String>(contexto,
                    android.R.layout.simple_spinner_item, result);
            spinnerOrigem.setAdapter(origemAdapter);
        }
    }

    private class CarregaSpinnerDestino extends AsyncTask<String, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            CategoriasDb db = new CategoriasDb(contexto);
            ArrayList<String> lista = db.selecionaDestino();
            if(lista.size() == 1)
                db.insereDestino();
            lista = db.selecionaDestino();
            return lista;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result){
            ArrayAdapter<String> destinoAdapter = new ArrayAdapter<String>(contexto,
                    android.R.layout.simple_spinner_item, result);
            spinnerDestino.setAdapter(destinoAdapter);
        }
    }

}
