package com.rafasalas.rugs.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.rafasalas.rugs.R;
/**
 * Created by salas on 02/08/2017.
 */

public class PrefsUtils {

  /*  public static  String hueKey;
    public static String satKey;;
    public static String brightKey;;
    public static String initializedKey;
  public  static SharedPreferences prefs;*/


    public static  void SetColors(Context context,int h, int s, int v){
        String hueKey=context.getString(R.string.pref_hue_key);
        String  satKey=context.getString(R.string.pref_saturation_key);;
        String brightKey=context.getString(R.string.pref_bright_key);;
       String initializedKey=context.getString(R.string.pref_init_key);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor=prefs.edit();
        Log.d("valores_set", h+" "+s+" "+v);
        Log.d("valores_string", hueKey+" "+satKey+" "+brightKey);
        editor.putInt(hueKey,h);
        editor.putInt(satKey,s);
        editor.putInt(brightKey,v);
        editor.putBoolean(initializedKey, true);
        editor.apply();
        editor.commit();
    }


}
