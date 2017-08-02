package com.rafasalas.rugs;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ComponentName;
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


public class MainActivity extends Activity {
    private Button vamos;
    private SeekBar hue, saturation, bright=null;
    private int valH=0, valS=0,valB=0;

    private boolean is_Running=false;
    public SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    public  String initializedKey=this.getString(R.string.pref_init_key);;
    public  String hueKey=this.getString(R.string.pref_hue_key);
    public  String satKey=this.getString(R.string.pref_saturation_key);;
    public  String brightKey=this.getString(R.string.pref_bright_key);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        hue=(SeekBar) findViewById(R.id.hue);
        saturation=(SeekBar) findViewById(R.id.saturation);
        bright=(SeekBar) findViewById(R.id.bright);
        addlistenerOnButton();
        WallpaperManager wpm = WallpaperManager.getInstance(this);
        WallpaperInfo info = wpm.getWallpaperInfo();
        //

        if (!prefs.contains(initializedKey)){

            hue.setProgress(50);
            valH=180;
            saturation.setProgress(50);
            valS=50;

            bright.setProgress(50);
            valB=50;
        } else
        {
          //valH=prefs.getIn
        }
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
                valS=progress;


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
                valB=progress;


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
                PrefsUtils.SetPrefs(getApplicationContext(),valH,valS,valB);

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
