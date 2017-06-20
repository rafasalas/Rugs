package com.rafasalas.rugs;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

/**
 * Created by salas on 15/06/2017.
 */

public class wallpaper extends WallpaperService {

    private Context context;






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
        //Variables acelerometro

        private wallpaperEngine() {
            context= getApplicationContext();


            int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
            int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
            lienzotrabajo=new lienzo(context,width,height);


            handler = new Handler();

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
                int width=canvas.getWidth();
                int height=canvas.getHeight();
                //aqui su publicidad
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

}
