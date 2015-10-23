package com.example.gabrielrtakeda.ConsultarVoo.adapter;

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

import com.example.gabrielrtakeda.ConsultarVoo.R;
import com.example.gabrielrtakeda.ConsultarVoo.util.Util;
import com.example.gabrielrtakeda.ConsultarVoo.util.ViewHolder;
import com.example.gabrielrtakeda.ConsultarVoo.model.Voos;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;

/**
 * Created by gabrielrtakeda on 19/09/2015.
 */
public class VoosAdapter extends BaseAdapter implements SectionIndexer {
    Activity context;
    Voos[] voos;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public VoosAdapter(Activity context, Voos[] voos){
        this.context = context;
        this.voos = voos;
        sectionHeaders = SectionIndexBuilder.BuildSectionHeaders(voos);
        positionForSectionMap = SectionIndexBuilder.BuildPositionForSectionMap(voos);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(voos);
    }

    public int getCount() {
        return voos.length;
    }

    public Object getItem(int position) {
        if(position >= 0 && position < voos.length)
            return voos[position];
        else
            return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_voos, parent, false);

            ImageView fotinhoCidadeVoo = (ImageView)view.findViewById(R.id.fotinhoCidadeVooImageView);
            TextView nomeCidadeVoo = (TextView)view.findViewById(R.id.nomeCidadeVooTextView);
            TextView detalhesVoo = (TextView)view.findViewById(R.id.detalhesVooTextView);

            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new ViewHolder(fotinhoCidadeVoo, nomeCidadeVoo, detalhesVoo));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
        Drawable drawable = Util.getDrawable(context, voos[position].getImagem());
        holder.getFotinhoCidadeVoo().setImageDrawable(drawable);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        holder.getNomeCidadeVoo().setText(voos[position].getNome());
        holder.getDetalhesVoo().setText(String.format("%s - %s", voos[position].getData(), voos[position].getHorario(),
                voos[position].getCompanhia(), formatter.format(voos[position].getPreco())));

        return view;
    }
//metodos da interface SectionIndexer

    public Object[] getSections() {
        return sectionHeaders;
    }

    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }

}
