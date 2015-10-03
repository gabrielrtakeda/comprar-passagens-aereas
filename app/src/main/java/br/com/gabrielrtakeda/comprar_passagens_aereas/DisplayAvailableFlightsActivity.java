package br.com.gabrielrtakeda.comprar_passagens_aereas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import br.com.gabrielrtakeda.comprar_passagens_aereas.listener.FlightDescriptionOnClickListener;

public class DisplayAvailableFlightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_available_flights);

        // Pega a Intent passada pela Activity anterior.
        Intent intent = getIntent();

        // Pega a mensagem passada.
        String selectedCountry = intent.getStringExtra(MainActivity.MESSAGE_SELECTED_COUNTRY);

        // Seta o valor do TextView com o país selecionado.
        TextView textView = (TextView) findViewById(R.id.text_selected_country);
        textView.setText(
            String.format(
                getResources().getString(R.string.format_selected_country),
                selectedCountry
            )
        );

        // Pega a TableLayout a ser populada.
        TableLayout tableAvailableFlights = (TableLayout) findViewById(R.id.table_available_flights);

        // Pega Mock de vôos disponíveis.
        JSONObject jsonObject = this.getMockData();
        if (jsonObject != null) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("flights");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject flightData = jsonArray.getJSONObject(i);

                    // Instancia e define o Layout da TableRow.
                    TableRow tableRow = new TableRow(this);
                    tableRow.setLayoutParams(
                        new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT
                        )
                    );
                    tableRow.setPadding(0, 50, 0, 50);
                    tableRow.setFocusable(true);
                    tableRow.setFocusableInTouchMode(true);
                    tableRow.setOnClickListener(new FlightDescriptionOnClickListener(flightData));

                    // Cria os TextViews para a TableRow.
                    tableRow.addView(this.buildTableRowTextView(flightData, "flight"));
                    tableRow.addView(this.buildTableRowTextView(flightData, "carrier"));
                    tableRow.addView(this.buildTableRowTextView(flightData, "origin"));
                    tableRow.addView(this.buildTableRowTextView(flightData, "arrival"));

                    // Finalmente, incluímos a TableRow na TableView.
                    TableLayout.LayoutParams tableRowLayout = new TableLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    tableAvailableFlights.addView(tableRow, tableRowLayout);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_available_flights, menu);
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

    private JSONObject getMockData() {
        String jsonString;
        JSONObject jsonObject = null;
        try {
            // Abre um InputStream para o arquivo.
            InputStream inputStream = getResources().openRawResource(R.raw.list_available_flights);
            if (inputStream == null) return null;

            int sizeOfJSONFile = inputStream.available();

            // Array que armazenará os dados.
            byte[] bytes = new byte[sizeOfJSONFile];

            // Lê os dados do arquivo para o Array.
            inputStream.read(bytes);

            // Fecha o InputStream.
            inputStream.close();

            // Transforma os dados JSON para objeto Java.
            jsonString = new String(bytes, "UTF-8");
            jsonObject = new JSONObject(jsonString);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonObject;
    }

    private InputStream getStream(String streamURL) {
        try {
            // Faz a requisição do arquivo JSON.
            URL url = new URL(streamURL);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(1000);
            return urlConnection.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private TextView buildTableRowTextView(
            JSONObject flightData,
            String field
    ) throws JSONException {
        TextView textView = new TextView(this);
        textView.setText(flightData.getString(field));
        textView.setPadding(25, 0, 25, 0);
        return textView;
    }
}
