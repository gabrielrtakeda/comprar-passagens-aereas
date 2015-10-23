package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.thread;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.message.MainMessage;

/**
 * Created by gtakeda on 10/11/15.
 */
public class ResponseFlightsCountryApi implements Runnable {

    Activity activity;
    Intent intent;
    ProgressBar loader;
    ArrayList<Flight> flightList;

    public ResponseFlightsCountryApi(
            Activity activity,
            Intent intent,
            ProgressBar loader,
            ArrayList<Flight> flightList
    ) {
        this.activity = activity;
        this.intent = intent;
        this.loader = loader;
        this.flightList = flightList;
    }

    @Override
    public void run() {
        intent.putExtra(MainMessage.MESSAGE_FLIGHT_LIST, flightList);
        loader.setVisibility(View.INVISIBLE);
        activity.startActivity(intent);
    }
}
