package com.rafasalas.rugs;

import android.app.Activity;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;


public class MainActivity extends Activity {
    private Button vamos;
    private SeekBar hue, saturation, bright=null;
    private int valH=0, valS=0,valB=0;

    private boolean is_Running=false;
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

        if (info != null && info.getPackageName().equals(this.getPackageName())) {
            Log.d("chocho ", "We're already running");
            is_Running=true;
        } else {
            Log.d("chocho ", "We're not running");
            is_Running=false;
        }
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

                if (is_Running){
                    dataglobal.set_modified(true);
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
