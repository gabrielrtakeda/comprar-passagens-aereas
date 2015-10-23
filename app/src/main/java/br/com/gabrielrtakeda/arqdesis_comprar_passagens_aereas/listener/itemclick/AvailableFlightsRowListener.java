package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.listener.itemclick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.activity.FlightDescriptionActivity;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.message.AvailableFlightsMessage;

/**
 * Created by gtakeda on 10/10/15.
 */
public class AvailableFlightsRowListener implements AdapterView.OnItemClickListener {

    Activity activity;
    Flight[] flightData;

    public AvailableFlightsRowListener(Activity activity, Flight[] flightData) {
        this.activity = activity;
        this.flightData = flightData;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(activity, FlightDescriptionActivity.class);
        intent.putExtra(AvailableFlightsMessage.AVAILABLE_FLIGHTS_MESSAGE, flightData[position]);
        activity.startActivity(intent);
    }
}
