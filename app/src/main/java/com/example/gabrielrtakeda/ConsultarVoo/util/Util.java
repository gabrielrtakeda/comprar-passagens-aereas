package com.example.gabrielrtakeda.ConsultarVoo.util;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.gabrielrtakeda.ConsultarVoo.R;

import java.lang.reflect.Field;

/**
 * Created by gabrielrtakeda on 19/09/2015.
 */
public class Util {
    public static Drawable getDrawable(Activity context, String drawableName){
        //troca hifen por underline, pois hifen e invalido para nome de recursos
        drawableName = drawableName.replace('-', '_');
        drawableName = drawableName.replace('â', 'a');
        //procurar a imagem por meio de reflection
        Class<?> c = R.drawable.class;
        try {
            Field idField = c.getDeclaredField(drawableName);
            int id = idField.getInt(idField);
            return context.getResources().getDrawable(id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return context.getResources().getDrawable(R.drawable.voos_vazio);
    }
}
