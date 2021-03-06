package com.rafasalas.rugs;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;

import com.rafasalas.rugs.data.PrefsUtils;
import com.rafasalas.rugs.views.muestracolor;


public class MainActivity extends Activity {
    private muestracolor muestra;
    private Button vamos;
    private SeekBar hue, saturation, bright=null;
    private float valH=0, valS=0,valB=0;

    private boolean is_Running=false;
  // public SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
  public SharedPreferences prefs;
    public  String initializedKey;
    public  String hueKey;
    public  String satKey;
    public  String brightKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
     // prefs=this.getPreferences(Context.MODE_PRIVATE);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        initializedKey=this.getString(R.string.pref_init_key);
        hueKey=this.getString(R.string.pref_hue_key);
        satKey=this.getString(R.string.pref_saturation_key);
       brightKey=this.getString(R.string.pref_bright_key);



        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        muestra=(muestracolor)findViewById(R.id.muestra);
        hue=(SeekBar) findViewById(R.id.hue);
        saturation=(SeekBar) findViewById(R.id.saturation);
        bright=(SeekBar) findViewById(R.id.bright);
        addlistenerOnButton();
        WallpaperManager wpm = WallpaperManager.getInstance(this);
        WallpaperInfo info = wpm.getWallpaperInfo();
        //
        //Log.d("valores", prefs.getInt(hueKey, 0)+" "+prefs.getInt(satKey, 0)+" "+prefs.getInt(brightKey, 0));
        if (!prefs.contains(initializedKey)){

            hue.setProgress(50);
            valH=180;
            saturation.setProgress(50);
            valS=0.5f;

            bright.setProgress(50);
            valB=0.5f;
        } else
        {
            valH=prefs.getFloat(hueKey, 0);

            hue.setProgress((int)(((double)valH/360)*100));
            valS=prefs.getFloat(satKey, 0);
            saturation.setProgress((int)(valS*100));
            valB=prefs.getFloat(brightKey, 0);
            bright.setProgress((int)(valB*100));
            //Log.d("valores_obtenidos", valH+" "+valS+" "+valB);
        }
        muestra.hsv[0]=valH;
        muestra.hsv[1]=valS;
        muestra.hsv[2]=valB;
        //
        if (info != null && info.getPackageName().equals(this.getPackageName())) {
            //Log.d("chocho ", "We're already running");
            is_Running=true;
            vamos.setText("Modify");
        } else {
            //Log.d("chocho ", "We're not running");
            is_Running=false;
            vamos.setText("Go!");
        }
        //
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.RECORD_AUDIO}, 1);
        }
        //


        hue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                valH=(progress*360)/100;
                muestra.hsv[0]=valH;


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        saturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                valS=(float)progress/100;

                muestra.hsv[1]=valS;


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bright.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                valB=(float)progress/100;

                muestra.hsv[2]=valB;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void addlistenerOnButton(){


        vamos = (Button) findViewById(R.id.goBoton);

        vamos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                float[] colornuevo=new float[3];
                colornuevo[0]=valH;
                colornuevo[1]=valS;
                colornuevo[2]=valB;
                final global dataglobal = (global) getApplicationContext();
                dataglobal.set_palette(true);
                dataglobal.set_color(colornuevo);
                PrefsUtils.SetColors(getApplicationContext(),valH,valS,valB);

                if (is_Running){
                    dataglobal.set_modified(true);
                    finish();
                } else {
                    WallpaperManager wallcachas = WallpaperManager.getInstance(getApplicationContext());
                    try {


                        Intent intent = new Intent(wallcachas.ACTION_CHANGE_LIVE_WALLPAPER);

                        intent.putExtra(wallcachas.EXTRA_LIVE_WALLPAPER_COMPONENT,
                                new ComponentName(MainActivity.this, wallpaper.class));
                        //stopService(intent);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {

                        Intent intent = new Intent();
                        intent.setAction(wallcachas.ACTION_LIVE_WALLPAPER_CHOOSER);
                        startActivity(intent);
                        finish();
                    }
                }


            }

        });
    }



}
