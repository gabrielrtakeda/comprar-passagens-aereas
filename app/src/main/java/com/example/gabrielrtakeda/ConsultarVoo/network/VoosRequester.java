package com.example.gabrielrtakeda.ConsultarVoo.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import com.example.gabrielrtakeda.ConsultarVoo.model.Voos;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by gabrielrtakeda on 26/09/2015.
 */

public class VoosRequester {

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Voos> get(String url, String pOrigem, String pDestino) throws IOException {

        ArrayList<Voos> lista = new ArrayList<>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
        RequestBody formBody = new FormEncodingBuilder()
                .add("origem", pOrigem)
                .add("destino", pDestino)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        try {
            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++ ) {
                item = (JSONObject)root.get(i);

                 String nome = item.getString("nome");;
                 String origem = item.getString("origem");
                 String destino = item.getString("destino");
                 String imagem = item.getString("imagem");
                 String data = item.getString("data");
                 String horario = item.getString("horario");
                 String companhia = item.getString("companhia");
                 double preco = item.getDouble("preco");

                lista.add(new Voos(nome, origem, destino, imagem, data, horario, companhia, preco));
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            if(lista.size() == 0)
                lista.add(new Voos(Voos.NAO_ENCONTRADA, pOrigem, pDestino, "voos_vazio", "00/00/0000", "00:00", "???", 0.0));
        }
        return lista;
    }
    public Bitmap getImage(String url) throws IOException {

        Bitmap img = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}

