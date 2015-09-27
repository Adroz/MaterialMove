package com.nikmoores.android.materialmove;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 26/09/2015.
 */
public class Utilities {

    /* Intent Constants */
    public static final String INTENT_FILTER = "com.nikmoores.android.materialmove.INTENT_FILTER";
    public static final String STATE_KEY = "state";

    /* Colour location constants */
    public final static int COLOUR_LOCATION_MAIN = 3;
    public final static int COLOUR_LOCATION_DARK = 5;

    public static List<int[]> get2dResourceArray(Context context, String key) {
        List<int[]> array = new ArrayList<>();
        Resources resources = context.getResources();
        try {
            Class<R.array> res = R.array.class;
            Field field;
            int counter = 0;
            do {
                field = res.getField(key + "_" + counter);
                field.getInt(field);
                int[] intArray = resources.getIntArray(field.getInt(field));
                for (int i = 0; i < intArray.length; i++) {
                    intArray[i] += 0xFF000000;
                }
                array.add(intArray);
                counter++;
            } while (field != null);
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            return array;
        }
    }

    // A method to find height of the status bar
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(
                    tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

}
