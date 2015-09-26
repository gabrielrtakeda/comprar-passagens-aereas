package br.com.gabrielrtakeda.comprar_passagens_aereas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public final static String MESSAGE_SELECTED_COUNTRY = "br.com.gabrielrtakeda.SELECTED_COUNTRY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void sendCountry(View view) {
        // Pega o país selecionado.
        Spinner spinner = (Spinner) findViewById(R.id.dropdown_countries);
        String selectedCountry = spinner.getSelectedItem().toString();

        String[] listCountries = getResources().getStringArray(R.array.list_countries);
        System.out.println(listCountries[0]);
        if (selectedCountry.equals(listCountries[0])) {
            new AlertDialog.Builder(this)
                .setTitle("País não selecionado.")
                .setMessage("Você não selecionou nenhum país. Por favor, selecione o país corretamente.")
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Não faz nada.
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

            return;
        }

        // Cria Intent para passar os valores para outra Activity.
        Intent intent = new Intent(this, DisplayAvailableFlightsActivity.class);
        intent.putExtra(MESSAGE_SELECTED_COUNTRY, selectedCountry);

        // Inicia a nova Activity.
        startActivity(intent);
    }
}
