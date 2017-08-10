package com.rafasalas.rugs.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.rafasalas.rafalib.vectorgraph.vectordraw;

import java.util.Random;

import static android.R.attr.height;
import static android.R.attr.width;

/**
 * Created by salas on 04/08/2017.
 */

public class muestracolor extends View {
    private Context context;


   public float[] hsv = new float[3];
    public float[] hsv_1 = new float[3];
    vectordraw muestra_1, muestra_2;
    public muestracolor(Context context, AttributeSet atributos) {


        super(context, atributos);
        muestra_1=new vectordraw(context);
        muestra_1.loadsvg("muestra_1","drawable","com.rafasalas.rugs");
        muestra_2=new vectordraw(context);
        muestra_2.loadsvg("muestra_2","drawable","com.rafasalas.rugs");
        hsv[0]=hsv[1]=hsv[2]=0;

        hsv_1[0]=hsv_1[1]=hsv_1[2]=0;


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
        muestra_1.resize((int)width, (int)height, 1);
        muestra_2.resize((int)width, (int)height, 1);
        canvas.drawARGB(255, Color.red(Color.HSVToColor(hsv)),Color.green(Color.HSVToColor(hsv)),Color.blue(Color.HSVToColor(hsv)));
        muestra_1.colorize(200,cal_comp(1f,hsv));
        //paint.setStrokeWidth(3);
        muestra_1.dibujar(canvas);
        muestra_2.colorize(100,cal_comp(45f,hsv));
        //paint.setStrokeWidth(3);
        muestra_2.dibujar(canvas);
        //paint.setARGB(255, rojo, verde, azul);


        invalidate();


    }
    private float[] cal_comp(float ang, float hsv[]){
        float[] chocho={0,0,0};
        float hcomp=0;

        // Log.d("hsv_antes", Float.toString(hsv[0]));
        if (hsv[0]>180) {hcomp=hsv[0]-180;} else {hcomp=180+hsv[0];}
        //Log.d("hsv_despues", Float.toString(hsv[0]));
        hcomp=hcomp+ang;
        if(hcomp>360){hcomp=hcomp-360;}
        if(hcomp<0){hcomp=360+hcomp;}

        chocho[0]=hcomp;
        chocho[1]=hsv[1];
        chocho[2]=hsv[2];

        return chocho;
    }


}