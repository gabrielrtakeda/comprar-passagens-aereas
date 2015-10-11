//package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.activity;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.ViewGroup;
//import android.widget.TableLayout;
//import android.widget.TableRow;
//import android.widget.TextView;
//
//import com.squareup.okhttp.FormEncodingBuilder;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.Locale;
//
//import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.R;
//import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;
//import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.listener.FlightDescriptionOnClickListener;
//import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.message.MainMessage;
//
//public class FlightsRequester {
//
//    protected void onCreate() {
//        // Pega Mock de vôos disponíveis.
//        JSONObject jsonObject = this.getMockData();
//        if (jsonObject != null) {
//            try {
//                JSONArray jsonArray = jsonObject.getJSONArray("flights");
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject flightData = jsonArray.getJSONObject(i);
//
//                    // Instancia e define o Layout da TableRow.
//                    TableRow tableRow = new TableRow(this);
//                    tableRow.setLayoutParams(
//                            new TableRow.LayoutParams(
//                                    TableRow.LayoutParams.MATCH_PARENT,
//                                    TableRow.LayoutParams.WRAP_CONTENT
//                            )
//                    );
//                    tableRow.setPadding(0, 50, 0, 50);
//                    tableRow.setFocusable(true);
//                    tableRow.setFocusableInTouchMode(true);
//                    tableRow.setOnClickListener(new FlightDescriptionOnClickListener(flightData));
//
//                    // Cria os TextViews para a TableRow.
//                    tableRow.addView(this.buildTableRowTextView(flightData, "flight"));
//                    tableRow.addView(this.buildTableRowTextView(flightData, "carrier"));
//                    tableRow.addView(this.buildTableRowTextView(flightData, "origin"));
//                    tableRow.addView(this.buildTableRowTextView(flightData, "arrival"));
//
//                    // Finalmente, incluímos a TableRow na TableView.
//                    TableLayout.LayoutParams tableRowLayout = new TableLayout.LayoutParams(
//                            ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT
//                    );
//                    tableAvailableFlights.addView(tableRow, tableRowLayout);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    OkHttpClient client = new OkHttpClient();
//
//    public ArrayList<Flight> get(String url, String pEstilo, String pCor, String pPais) throws IOException {
//
//        ArrayList<Flight> lista = new ArrayList<>();
//
//        // acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
//        RequestBody formBody = new FormEncodingBuilder()
//                .add("estilo", pEstilo)
//                .add("cor", pCor)
//                .add("pais", pPais)
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(formBody)
//                .build();
//
//        Response response = client.newCall(request).execute();
//
//        String jsonStr = response.body().string();
//
//        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
//
//        try {
//            JSONArray root = new JSONArray(jsonStr);
//            JSONObject item = null;
//            for (int i = 0; i < root.length(); i++ ) {
//                item = (JSONObject)root.get(i);
//
//                String name = item.getString("name");
//                String carrier = item.getString("carrier");
//                String origin = item.getString("origin");
//                String arrival = item.getString("arrival");
//
//                lista.add(new Flight(name, carrier, origin, arrival));
//            }
//        } catch(JSONException e){
//            e.printStackTrace();
//        }
//        finally {
//            Log.v("CervejaRequester", jsonStr);
//        }
//        return lista;
//    }
//
//    private JSONObject getMockData() {
//        String jsonString;
//        JSONObject jsonObject = null;
//        try {
//            // Abre um InputStream para o arquivo.
//
//            InputStream inputStream = getResources().openRawResource(R.raw.list_available_flights);
//            if (inputStream == null) return null;
//
//            int sizeOfJSONFile = inputStream.available();
//
//            // Array que armazenará os dados.
//            byte[] bytes = new byte[sizeOfJSONFile];
//
//            // Lê os dados do arquivo para o Array.
//            inputStream.read(bytes);
//
//            // Fecha o InputStream.
//            inputStream.close();
//
//            // Transforma os dados JSON para objeto Java.
//            jsonString = new String(bytes, "UTF-8");
//            jsonObject = new JSONObject(jsonString);
//
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return jsonObject;
//    }
//}
