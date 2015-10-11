package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.R;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.listener.click.ValidationNeutralButtonListener;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.message.MainMessage;

public class MainActivity extends ActionBarActivity {

    Spinner spinnerCountries;
    Spinner spinnerCities;
    Button buttonDone;
    int selectedCountryIndex;
    int selectedCityIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        spinnerCountries = (Spinner) findViewById(R.id.dropdown_countries);
        String[] listCountries = getResources().getStringArray(R.array.list_countries);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(
            this, R.layout.spinner,
            listCountries
        );

        spinnerCities = (Spinner) findViewById(R.id.dropdown_cities);
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(
            this, R.layout.spinner,
            getResources().getStringArray(R.array.list_empty)
        );

        buttonDone = (Button) findViewById(R.id.button_done);

        // Set Adapters.
        spinnerCountries.setAdapter(countryAdapter);
        spinnerCities.setAdapter(citiesAdapter);

        // Set Listeners.
        spinnerCountries.setOnItemSelectedListener(new SelectedCountry(this));
        spinnerCities.setOnItemSelectedListener(new SelectedCity());
    }

    private void buildAlertMessage(Context context, String title, String message) {
        new AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton(android.R.string.ok, new ValidationNeutralButtonListener())
            .setIcon(android.R.drawable.ic_dialog_info)
            .show();
    }

    public boolean validateEntries() {
        if (selectedCountryIndex == 0) {
            buildAlertMessage(
                this,
                getResources().getString(R.string.validation_country_not_selected_title),
                getResources().getString(R.string.validation_country_not_selected_message)
            );
            return false;

        } else if (selectedCityIndex == 0) {
            buildAlertMessage(
                this,
                getResources().getString(R.string.validation_city_not_selected_title),
                getResources().getString(R.string.validation_city_not_selected_message)
            );
            return false;
        }
        return true;
    }

    /**
     * Inner Listener Classes.
     */
    private class SelectedCountry implements AdapterView.OnItemSelectedListener {
        Context context;

        public SelectedCountry(Context context) {
            this.context = context;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedCountryIndex = position;
            if (position != 0) {
                String[] listCountriesIndex = getResources().getStringArray(R.array.list_countries_index);
                int citiesListResourceId = getResources().getIdentifier(
                    listCountriesIndex[position],
                    "array",
                    getPackageName()
                );

                ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(
                    context, R.layout.spinner,
                    getResources().getStringArray(citiesListResourceId)
                );
                spinnerCities.setAdapter(citiesAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    private class SelectedCity implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedCityIndex = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    /**
     * Action Methods
     */
    public void sendCountry(View view) {
        if (!validateEntries()) return;

        Intent intent = new Intent(this, DisplayAvailableFlightsActivity.class);
        intent.putExtra(MainMessage.MESSAGE_FLIGHT_LIST, getFlights());

        startActivity(intent);
    }

    /**
     * Mock
     */
    public ArrayList<Flight> getFlights() {
        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight("JL 5513 ^", "JAL", "São Paulo", "4:55 AM", "sao_paulo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("LH 7358 ^", "Lufthansa", "Rio Grande do Sul", "4:55 AM", "rio_grande_do_sul", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("NH 5929 ^", "ANA", "Yokohama", "4:55 AM", "yokohama", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("CA 7109 ^", "Air China", "Tokyo", "4:55 AM", "tokyo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("G3 7725", "Gol", "Liverpool", "5:00 AM", "liverpool", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("DL 6905 ^", "Delta Air Lines", "Londres", "5:00 AM", "londres", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("AZ 674", "Alitalia", "Flin-Flon", "5:05 AM", "flin_flon", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("G3 1889  ", "Gol", "Campbellton", "5:10 AM", "campbellton", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("G3 1181", "Gol", "Yulara", "5:15 AM", "yulara", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("KL 9353 ^", "KLM", "Palmerstone", "5:15 AM", "palmerstone", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("AZ 5517 ^", "Alitalia", "Curuzú Cuatiá", "5:15 AM", "curuzu_cuatia", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("UX 57", "Air Europa", "Paso de los Libres", "5:15 AM", "paso_de_los_libres", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("BA 247", "British Airways", "Gunzenhausen", "5:20 AM", "gunzenhausen", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("IB 4741 ^", "Iberia", "Bad Zwischenahn", "5:20 AM", "bad_zwischenahn", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("JJ 8065", "TAM", "Johannesburg", "5:25 AM", "johannesburg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        flightList.add(new Flight("LA 8526 ^", "LAN Airlines", "Cape Town", "5:25 AM", "cape_town", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum molestie sagittis vehicula. Aenean eu leo nunc. Maecenas ut vehicula mi."));
        return flightList;
    }
}
