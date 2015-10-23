package com.example.gabrielrtakeda.ConsultarVoo.controller;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielrtakeda.ConsultarVoo.R;
import com.example.gabrielrtakeda.ConsultarVoo.util.Util;
import com.example.gabrielrtakeda.ConsultarVoo.model.Voos;
import com.example.gabrielrtakeda.ConsultarVoo.network.VoosRequester;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by gabrielrtakeda on 19/09/2015.
 */
public class DetalheVoosActivity extends ActionBarActivity {

    TextView voosNome;
    ImageView voosImageView;
    TextView voosOrigem;
    TextView voosDestino;
    TextView voosData;
    TextView voosHorario;
    TextView voosCompanhia;
    TextView voosPreco;
    VoosRequester requester;
    ProgressBar mProgress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_voos);

        Intent intent = getIntent();
        final Voos voos = (Voos)intent.getSerializableExtra(ListaVoosActivity.VOOS);
        setupViews(voos);

        if(!voos.getNome().equals(Voos.NAO_ENCONTRADA)) {
            requester = new VoosRequester();
            if (requester.isConnected(this)) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mProgress.setVisibility(View.VISIBLE);
                            final Bitmap img = requester.getImage(voos.getImagem());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    voosImageView.setImageBitmap(img);
                                    mProgress.setVisibility(View.INVISIBLE);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                Resources res = getResources();
                Drawable drawable = res.getDrawable(R.drawable.voos_vazio);
                voosImageView.setImageDrawable(drawable);
                Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Resources res = getResources();
            Drawable drawable = res.getDrawable(R.drawable.voos_vazio);
            voosImageView.setImageDrawable(drawable);
        }

    }
    private void setupViews(Voos voos) {
        voosNome = (TextView) findViewById(R.id.txt_voos_nome);
        voosNome.setText(voos.getNome());
        voosImageView = (ImageView) findViewById(R.id.voos_image_view);
        Drawable drawable = Util.getDrawable(this, voos.getImagem());
        voosImageView.setImageDrawable(drawable);
        voosOrigem = (TextView) findViewById(R.id.txt_voos_origem);
        voosOrigem.setText(voos.getOrigem());
        voosDestino = (TextView) findViewById(R.id.txt_voos_destino);
        voosDestino.setText(voos.getDestino());
        voosData = (TextView) findViewById(R.id.txt_voos_data);
        voosData.setText(voos.getData());
        voosHorario = (TextView) findViewById(R.id.txt_voos_horario);
        voosHorario.setText(voos.getHorario());
        voosCompanhia = (TextView) findViewById(R.id.txt_voos_companhia);
        voosCompanhia.setText(voos.getCompanhia());
        voosPreco = (TextView) findViewById(R.id.txt_voos_preco);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        voosPreco.setText(""+formatter.format(voos.getPreco()));
        mProgress = (ProgressBar) findViewById(R.id.carregando_voos);
        mProgress.setVisibility(View.INVISIBLE);
    }

}

