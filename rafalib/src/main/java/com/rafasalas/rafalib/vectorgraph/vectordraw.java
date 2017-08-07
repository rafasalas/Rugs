package com.rafasalas.rafalib.vectorgraph;

/**
 * Created by salas on 27/09/2016.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.VectorDrawable;

import java.util.Random;


public class vectordraw {
   public VectorDrawable drawable;
    public Context context;
    public int draw_height, draw_width;
    public Rect superficie;
    public Resources res;

    public vectordraw(Context context) {
        res = context.getResources();

        superficie = new Rect();
    }

    public void loadsvg(String nombre, String carpeta, String paquete) {
        //int id=res.getIdentifier(nombre, "drawable", "com.simbolic.salas.simbolic");
        int id = res.getIdentifier(nombre, carpeta, paquete);
        drawable = (VectorDrawable) res.getDrawable(id, null);
    }

    public void loadsvg(String nombre) {
        int id = res.getIdentifier(nombre, "drawable", "com.simbolic.salas.simbolic");

        drawable = (VectorDrawable) res.getDrawable(id, null);
    }


    public void resize(int width, int height, double scale) {
        //Scales from the size of the screen, and positioning in center of screen
        double mainX, mainY, newW, newH;

        draw_height = drawable.getIntrinsicHeight();
        draw_width = drawable.getIntrinsicWidth();

        newW = width * scale;
        mainX = (width * ((1 - scale) / 2));
        float rel = ((float) newW / (float) draw_width);
        newW = newW + mainX;


        newH = rel * draw_height;
        mainY = (height - newH) / 2;
        newH = newH + mainY;
        superficie.set((int) mainX, (int) mainY, (int) newW, (int) (newH));
        drawable.setBounds(superficie);


    }

    public void resize(int width, int height, double scale, double Xini, double Yini) {

        //Scales from the size of the screen, and positioning in absolute way
        double mainX, mainY, newW, newH;

        draw_height = drawable.getIntrinsicHeight();
        draw_width = drawable.getIntrinsicWidth();

        newW = width * scale;
        mainX = Xini * width;
        float rel = ((float) newW / (float) draw_width);
        newW = newW + mainX;


        newH = rel * draw_height;
        mainY = Yini * width;
        newH = newH + mainY;
        superficie.set((int) mainX, (int) mainY, (int) newW, (int) (newH));
        drawable.setBounds(superficie);


    }


    public void resize(double scale, double Xini, double Yini) {

        //Scales from the size of the graph, and positioning in absolute way
        double mainX, mainY, newW, newH;

        draw_height = drawable.getIntrinsicHeight();
        draw_width = drawable.getIntrinsicWidth();

        newW =draw_width * scale;
        mainX = Xini ;
        float rel = ((float) newW / (float) draw_width);
        newW = newW + mainX;


        newH = rel * draw_height;
        mainY = Yini ;
        newH = newH + mainY;
        superficie.set((int) mainX, (int) mainY, (int) newW, (int) (newH));
        drawable.setBounds(superficie);


    }

    public void dibujar(Canvas canvas) {



        drawable.draw(canvas);

    }

    public void alfa(int opacity) {
        drawable.setAlpha(opacity);
    }

    public void colorize(int opacity, int r, int g, int b) {
        Color color = new Color();


        drawable.setColorFilter(color.argb(opacity, r, g, b), PorterDuff.Mode.MULTIPLY);

    }
    public void colorize_palette(int opacity, float[] hsv){
        Random rnd=new Random();
        Color color = new Color();
        float[] chocho={0,0,0};
        float hcomp=0;

        // Log.d("hsv_antes", Float.toString(hsv[0]));
        if (hsv[0]>180) {hcomp=hsv[0]-180;} else {hcomp=180+hsv[0];}
        //Log.d("hsv_despues", Float.toString(hsv[0]));
        hcomp=hcomp+rnd.nextInt(90)-45;
        if(hcomp>360){hcomp=hcomp-360;}
        if(hcomp<0){hcomp=360+hcomp;}

        chocho[0]=hcomp;
        chocho[1]=hsv[1];
        chocho[2]=rnd.nextFloat()*100;
        //Log.d("brillo", chocho[2]+" ");
        int outputColor = Color.HSVToColor(chocho);
        int r=Color.red(outputColor);
        int g=Color.green(outputColor);
        int b=Color.blue(outputColor);
        drawable.setColorFilter(color.argb(opacity, r, g, b), PorterDuff.Mode.MULTIPLY);
    }

}