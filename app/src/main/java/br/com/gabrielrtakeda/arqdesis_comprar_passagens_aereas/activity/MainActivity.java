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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.R;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.listener.click.ValidationNeutralButtonListener;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.network.FlightsRequester;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.thread.RequestFlightsCountryApi;

public class MainActivity extends ActionBarActivity {

    Spinner spinnerCountries;
    Spinner spinnerCities;
    Button buttonDone;
    int selectedCountryIndex;
    int selectedCityIndex;
    String selectedCountryApiIndex;
    FlightsRequester requester;
    ProgressBar loader;
    final String host = "192.168.1.33";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareViews();
        setupViews();
    }

    private void prepareViews() {
        spinnerCountries = (Spinner) findViewById(R.id.dropdown_countries);
        spinnerCities = (Spinner) findViewById(R.id.dropdown_cities);
        buttonDone = (Button) findViewById(R.id.button_done);
        loader = (ProgressBar) findViewById(R.id.loader);
    }

    private void setupViews() {
        String[] listCountries = getResources().getStringArray(R.array.list_countries);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(
            this, R.layout.spinner,
            listCountries
        );

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(
            this, R.layout.spinner,
            getResources().getStringArray(R.array.list_empty)
        );

        loader.setVisibility(View.INVISIBLE);

        // Set Adapters.
        spinnerCountries.setAdapter(countryAdapter);
        spinnerCities.setAdapter(citiesAdapter);

        // Set Listeners.
        spinnerCountries.setOnItemSelectedListener(new SelectedCountry(this));
        spinnerCities.setOnItemSelectedListener(new SelectedCity());
    }

    public boolean validateEntries() {
        if (selectedCountryIndex == 0) {
            Toast toast = Toast.makeText(
                this,
                getResources().getString(R.string.validation_country_not_selected_message),
                Toast.LENGTH_LONG
            );
            toast.show();
            return false;

        } else if (selectedCityIndex == 0) {
            Toast toast = Toast.makeText(
                this,
                getResources().getString(R.string.validation_city_not_selected_message),
                Toast.LENGTH_LONG
            );
            toast.show();
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
            String[] listCountriesApiIndex = getResources().getStringArray(R.array.list_countries_api_index);
            selectedCountryApiIndex = listCountriesApiIndex[position];
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

        requester = new FlightsRequester();
        if(requester.isConnected(this)) {
            loader.setVisibility(View.VISIBLE);
            new Thread(
                new RequestFlightsCountryApi(
                    this, requester, host, loader,
                    new Intent(this, DisplayAvailableFlightsActivity.class),
                    selectedCountryApiIndex
                )
            ).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
