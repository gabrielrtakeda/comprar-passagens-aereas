package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.R;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.listener.FlightDescriptionOnClickListener;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.message.AvailableFlightsMessage;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.util.Util;

public class FlightDescriptionActivity extends AppCompatActivity {

    TextView origin;
    ImageView image;
    TextView data;
    TextView arrival;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_description);

        Intent intent = getIntent();
        Flight flight = (Flight) intent.getSerializableExtra(
            AvailableFlightsMessage.AVAILABLE_FLIGHTS_MESSAGE
        );

        prepareViews();
        setupViews(flight);
    }

    private void prepareViews() {
        origin = (TextView) findViewById(R.id.flight_description_origin);
        image = (ImageView) findViewById(R.id.flight_description_image);
        data = (TextView) findViewById(R.id.flight_description_data);
        arrival = (TextView) findViewById(R.id.flight_description_arrival);
        description = (TextView) findViewById(R.id.flight_description_description);
    }

    private void setupViews(Flight flight) {
        origin.setText(flight.getOrigin());
        image.setImageDrawable(Util.getDrawable(this, flight.getImage()));
        data.setText(flight.getCarrier() +" - "+ flight.getName());
        arrival.setText(flight.getArrival());
        description.setText(flight.getDescription());
    }
}
