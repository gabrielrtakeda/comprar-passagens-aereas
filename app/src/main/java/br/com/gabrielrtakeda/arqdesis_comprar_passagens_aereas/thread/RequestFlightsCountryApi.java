package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.thread;

import android.app.Activity;
import android.content.Intent;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.network.FlightsRequester;

/**
 * Created by gtakeda on 10/11/15.
 */
public class RequestFlightsCountryApi implements Runnable {

    Activity activity;
    FlightsRequester requester;
    String host;
    ProgressBar loader;
    Intent intent;
    String selectedCountryApiIndex;

    public RequestFlightsCountryApi(
            Activity activity,
            FlightsRequester requester,
            String host,
            ProgressBar loader,
            Intent intent,
            String selectedCountryApiIndex
    ) {
        this.activity = activity;
        this.requester = requester;
        this.host = host;
        this.loader = loader;
        this.intent = intent;
        this.selectedCountryApiIndex = selectedCountryApiIndex;
    }

    @Override
    public void run() {
        try {
            ArrayList<Flight> flightList = requester.get(
                "http://" + host + "/test.dev/api.php",
                selectedCountryApiIndex
            );
            activity.runOnUiThread(
                new ResponseFlightsCountryApi(activity, intent, loader, flightList)
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
