package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;

public class FlightsRequester {

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Flight> get(String url, String countryApiIndex) throws IOException {

        ArrayList<Flight> lista = new ArrayList<>();

        RequestBody formBody = new FormEncodingBuilder()
                .add("country", countryApiIndex)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        String jsonStr = response.body().string();

        try {
            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++ ) {
                item = (JSONObject)root.get(i);

                String name = item.getString("flight");
                String carrier = item.getString("carrier");
                String origin = item.getString("origin");
                String arrival = item.getString("arrival");
                String image = item.getString("image");
                String description = item.getString("description");

                lista.add(new Flight(name, carrier, origin, arrival, image, description));
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            Log.v("CervejaRequester", jsonStr);
        }
        return lista;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
