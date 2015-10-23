package com.example.gabrielrtakeda.ConsultarVoo.util;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gabrielrtakeda on 19/09/2015.
 */
public class ViewHolder {

    private ImageView fotinhoCidadeVoo;
    private TextView nomeCidadeVoo, detalhesVoo;

    public ViewHolder(ImageView fotinhoCidadeVoo, TextView nomeCidadeVoo, TextView detalhesVoo) {
        this.fotinhoCidadeVoo = fotinhoCidadeVoo;
        this.nomeCidadeVoo = nomeCidadeVoo;
        this.detalhesVoo = detalhesVoo;
    }

    public ImageView getFotinhoCidadeVoo() {
        return fotinhoCidadeVoo;
    }

    public void setFotinhoCidadeVoo(ImageView fotinhoCidadeVoo) {
        this.fotinhoCidadeVoo = fotinhoCidadeVoo;
    }

    public TextView getNomeCidadeVoo() {
        return nomeCidadeVoo;
    }

    public void setNomeCidadeVoo(TextView nomeCidadeVoo) {
        this.nomeCidadeVoo = nomeCidadeVoo;
    }

    public TextView getDetalhesVoo() {
        return detalhesVoo;
    }

    public void setDetalhesVoo(TextView detalhesVoo) {
        this.detalhesVoo = detalhesVoo;
    }
}

