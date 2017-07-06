package com.rafasalas.rugs;

import android.content.Context;
import android.graphics.Canvas;
import android.media.audiofx.Visualizer;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by salas on 15/06/2017.
 */

public class wallpaper extends WallpaperService {

    private Context context;
    private Visualizer mVisualizer;





    @Override
    public Engine onCreateEngine() {

        return new wallpaperEngine();

    }
    private class wallpaperEngine extends Engine  {
        private boolean visible;
        private final int frameDuration = 16;
        private SurfaceHolder holder;


        private Handler handler;


        private lienzo lienzotrabajo;
        //Variables musica
        final global dataglobal = (global) getApplicationContext();

        private wallpaperEngine() {
            context= getApplicationContext();


            int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
            int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;

            lienzotrabajo=new lienzo(context,width,height);


            handler = new Handler();
            init_music();
        }
        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {


            super.onCreate(surfaceHolder);

            this.holder = surfaceHolder;

        }
        private Runnable drawNucleus = new Runnable() {
            @Override
            public void run() {
                draw();

            }
        }
                ;

        private void draw() {


            if (visible) {
                Canvas canvas = holder.lockCanvas();
                canvas.save();
                //int width=canvas.getWidth();
               // int height=canvas.getHeight();
                //aqui su publicidad
                int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
                int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
                lienzotrabajo.draw(canvas, width, height);

                canvas.restore();
                holder.unlockCanvasAndPost(canvas);

                handler.removeCallbacks(drawNucleus);
                handler.postDelayed(drawNucleus, frameDuration);
            }
        }

        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;

            if (visible) {
                handler.post(drawNucleus);
            } else {
                handler.removeCallbacks(drawNucleus);
            }

        }
        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);

        }


        @Override
        public void onDestroy() {

            super.onDestroy();
            handler.removeCallbacks(drawNucleus);
        }
}
//music!!
private void init_music(){
    final global dataglobal = (global) getApplicationContext();
    mVisualizer = new Visualizer(0);
    mVisualizer.setEnabled(false);

    mVisualizer.setCaptureSize(128);

    Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener()
    {
        @Override
        public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                          int samplingRate)
        { float sum=0;

            for (int i = 0; i < bytes.length; i++) {
                sum=sum+(float)bytes[i];
            }
            //if (sum<-16383){sum=1;}else{sum=sum/1000;}
            if (sum<-12000 || sum>12000){sum=1;}else{sum=sum/1000;}

            dataglobal.setIntensity(sum);
            Log.v("sumatorio"," "+sum);
        }

        @Override
        public void onFftDataCapture(Visualizer visualizer, byte[] bytes,
                                     int samplingRate)
        {
            Log.v("fft", " " + bytes.length+bytes[0] + " "+bytes[60] + " " + bytes[125]);
        }
    };

    mVisualizer.setDataCaptureListener(captureListener,
            Visualizer.getMaxCaptureRate() / 2, true, true);

    // Enabled Visualizer and disable when we're done with the stream

    mVisualizer.setEnabled(true);

}


    public void release()
    {
        mVisualizer.release();
    }

}

//////////////

    //music

