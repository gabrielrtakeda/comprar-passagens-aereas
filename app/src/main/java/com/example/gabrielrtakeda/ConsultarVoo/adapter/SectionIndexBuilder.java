package com.example.gabrielrtakeda.ConsultarVoo.adapter;

import com.example.gabrielrtakeda.ConsultarVoo.model.Voos;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

/**
 * Created by gabrielrtakeda on 19/09/2015.
 */
public class SectionIndexBuilder{
//cria um array de cabecalhos unicos de secao; os dados devem estar ordenados por nome
        public static Object[] BuildSectionHeaders(Voos[] voos)
        {
            ArrayList<String> results = new ArrayList<>();
            TreeSet<String> used    = new TreeSet<>();
            if(voos != null) {
                for (Voos item : voos) {
                    String letter = item.getNome().substring(0, 1);

                    if (!used.contains(letter))
                        results.add(letter);

                    used.add(letter);
                }
            }
            return results.toArray(new Object[0]);
        }

        //cria um mapa para responder: posicao --> secao de dados ordenados pelo nome
        public static Hashtable<Integer, Integer> BuildSectionForPositionMap(Voos[] voos)
        {
            Hashtable results = new Hashtable<Integer, Integer>();
            TreeSet<String> used    = new TreeSet<>();
            int section = -1;

            if(voos != null) {
                for (int i = 0; i < voos.length; i++) {
                    String letter = voos[i].getNome().substring(0, 1);

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
        public static Hashtable<Integer, Integer> BuildPositionForSectionMap(Voos[] voos)
        {
            Hashtable results = new Hashtable<Integer, Integer>();
            TreeSet<String> used    = new TreeSet<>();
            int section = -1;

            if(voos != null) {
                for (int i = 0; i < voos.length; i++) {
                    String letter = voos[i].getNome().substring(0, 1);

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
