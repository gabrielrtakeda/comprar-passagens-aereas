package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.adapter;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

import br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.entity.Flight;

/**
 * Created by gtakeda on 10/9/15.
 */
public class SectionIndexBuilder {
    //cria um array de cabecalhos unicos de secao; os dados devem estar ordenados por nome
    public static Object[] BuildSectionHeaders(Flight[] cervejas)
    {
        ArrayList<String> results = new ArrayList<>();
        TreeSet<String> used    = new TreeSet<>();
        if(cervejas != null) {
            for (Flight item : cervejas) {
                String letter = item.getOrigin().substring(0, 1);

                if (!used.contains(letter))
                    results.add(letter);

                used.add(letter);
            }
        }
        return results.toArray(new Object[0]);
    }

    //cria um mapa para responder: posicao --> secao de dados ordenados pelo nome
    public static Hashtable<Integer, Integer> BuildSectionForPositionMap(Flight[] cervejas)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(cervejas != null) {
            for (int i = 0; i < cervejas.length; i++) {
                String letter = cervejas[i].getOrigin().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                }

                results.put(i, section);
            }
        }
        return results;
    }

    //cria um mapa para responder: secao --> posicao de dados ordenados pelo nome
    public static Hashtable<Integer, Integer> BuildPositionForSectionMap(Flight[] cervejas)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(cervejas != null) {
            for (int i = 0; i < cervejas.length; i++) {
                String letter = cervejas[i].getOrigin().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                    results.put(section, i);
                }
            }
        }
        return results;
    }
}
