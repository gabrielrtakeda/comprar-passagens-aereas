package br.com.gabrielrtakeda.comprar_passagens_aereas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.gabrielrtakeda.comprar_passagens_aereas.listener.FlightDescriptionOnClickListener;

public class FlightDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_description);

        Intent intent = getIntent();

        String flightValue = intent.getStringExtra(FlightDescriptionOnClickListener.MESSAGE_FLIGHT);
        TextView flightText = (TextView) findViewById(R.id.flight_description_flight);
        flightText.setText(flightValue);

        String carrierValue = intent.getStringExtra(FlightDescriptionOnClickListener.MESSAGE_CARRIER);
        TextView carrierText = (TextView) findViewById(R.id.flight_description_carrier);
        carrierText.setText(carrierValue);

        String originValue = intent.getStringExtra(FlightDescriptionOnClickListener.MESSAGE_ORIGIN);
        TextView originText = (TextView) findViewById(R.id.flight_description_origin);
        originText.setText(originValue );

        String arrivalValue = intent.getStringExtra(FlightDescriptionOnClickListener.MESSAGE_ARRIVAL);
        TextView arrivalText = (TextView) findViewById(R.id.flight_description_arrival);
        arrivalText.setText(arrivalValue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flight_description, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
