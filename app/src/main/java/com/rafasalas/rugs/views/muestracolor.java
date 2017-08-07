package com.rafasalas.rugs.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.rafasalas.rafalib.vectorgraph.vectordraw;

import java.util.Random;

/**
 * Created by salas on 04/08/2017.
 */

public class muestracolor extends View {
    private Context context;


   public float[] hsv = new float[3];
    vectordraw muestra_1;
    public muestracolor(Context context, AttributeSet atributos) {


        super(context, atributos);
        muestra_1=new vectordraw(context);
        muestra_1.loadsvg("muestra_1","drawable","com.rafasalas.rugs";
        //muestra_1.resize(width, h);
        hsv[0]=hsv[1]=hsv[2]=0;
        Random rnd=new Random();

    }
    @Override

    protected void onDraw(Canvas canvas){
        Paint paint;
        paint = new Paint();
        int centerX,centerY;
        centerX=centerY=0;

        float width=canvas.getWidth();
        float height=canvas.getHeight();

        canvas.drawARGB(255, Color.red(Color.HSVToColor(hsv)),Color.green(Color.HSVToColor(hsv)),Color.blue(Color.HSVToColor(hsv)));

        paint.setStrokeWidth(3);
        //paint.setARGB(255, rojo, verde, azul);


        invalidate();


    }



}