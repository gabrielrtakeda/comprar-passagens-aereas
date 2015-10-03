package br.com.gabrielrtakeda.comprar_passagens_aereas.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.gabrielrtakeda.comprar_passagens_aereas.FlightDescriptionActivity;

/**
 * Created by gtakeda on 10/2/15.
 */
public class FlightDescriptionOnClickListener implements View.OnClickListener {
    private JSONObject flightData;

    public static final String MESSAGE_FLIGHT = "br.com.gabrielrtakeda.MESSAGE_FLIGHT";
    public static final String MESSAGE_CARRIER = "br.com.gabrielrtakeda.MESSAGE_CARRIER";
    public static final String MESSAGE_ORIGIN = "br.com.gabrielrtakeda.MESSAGE_ORIGIN";
    public static final String MESSAGE_ARRIVAL = "br.com.gabrielrtakeda.MESSAGE_ARRIVAL";

    public FlightDescriptionOnClickListener(JSONObject flightData) {
        this.flightData = flightData;
    }

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, FlightDescriptionActivity.class);

        try {
            intent.putExtra(MESSAGE_FLIGHT, this.flightData.getString("flight"));
            intent.putExtra(MESSAGE_CARRIER, this.flightData.getString("carrier"));
            intent.putExtra(MESSAGE_ORIGIN, this.flightData.getString("origin"));
            intent.putExtra(MESSAGE_ARRIVAL, this.flightData.getString("arrival"));
            context.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
