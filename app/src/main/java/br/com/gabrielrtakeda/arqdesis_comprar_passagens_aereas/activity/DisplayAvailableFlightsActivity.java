package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.R;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.adapter.DisplayAvailableFlightsAdapter;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.listener.itemclick.AvailableFlightsRowListener;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.message.MainMessage;

public class DisplayAvailableFlightsActivity extends AppCompatActivity {

    Activity instance;
    Flight[] flightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_available_flights);
        instance = this;

        Intent intent = getIntent();
        flightList = ((ArrayList<Flight>) intent.getSerializableExtra(MainMessage.MESSAGE_FLIGHT_LIST)).toArray(new Flight[0]);

        ListView listView = (ListView) findViewById(R.id.listview_available_flight);
        DisplayAvailableFlightsAdapter adapter = new DisplayAvailableFlightsAdapter(this, flightList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AvailableFlightsRowListener(this, flightList));
    }
}
