package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.R;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.util.AvailableFlightsViewHolder;
import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.util.Util;

/**
 * Created by gtakeda on 10/9/15.
 */
public class DisplayAvailableFlightsAdapter extends BaseAdapter implements SectionIndexer {
    Activity context;
    Flight[] flightList;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public DisplayAvailableFlightsAdapter(Activity context, Flight[] flightList){
        this.context = context;
        this.flightList = flightList;
    }
    @Override
    public int getCount() {
        return flightList.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < flightList.length)
            return flightList[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.available_flight_adapter_row, parent, false);

            ImageView flightImage = (ImageView) view.findViewById(R.id.available_flight_image);
            TextView flightOrigin = (TextView) view.findViewById(R.id.available_flight_origin);
            TextView flightData = (TextView) view.findViewById(R.id.available_flight_data);
            TextView flightArrival = (TextView) view.findViewById(R.id.available_flight_arrival);
            TextView flightDescription = (TextView) view.findViewById(R.id.available_flight_description);
            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new AvailableFlightsViewHolder(
                flightImage,
                flightOrigin,
                flightData,
                flightArrival,
                flightDescription
            ));
        }
        //usa os widgets cacheados na view reciclada
        AvailableFlightsViewHolder holder = (AvailableFlightsViewHolder) view.getTag();
        //carrega os novos valores
        Drawable drawable = Util.getDrawable(context, flightList[position].getImage());
        holder.getImage().setImageDrawable(drawable);
        holder.getOrigin().setText(flightList[position].getOrigin());
        holder.getData().setText(flightList[position].getName() +": "+ flightList[position].getCarrier());
        holder.getArrival().setText(flightList[position].getArrival());
        holder.getDescription().setText(flightList[position].getDescription());

        return view;
    }
//metodos da interface SectionIndexer


    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }
}
