package com.rafasalas.rafalib.composites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;

import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.particles.particulasimple;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;

/**
 * Created by salas on 31/10/2016.
 */

public class sistema {
    PVector origen;
    PVector  velocidadinicial, Vvar;
    float masaparticula;
    ArrayList<particulasimple> particulas;
    int a,r,g,b, clas;
   Context contexto;
    public boolean life;
    public boolean sBoxed;
    public float sLimitX,sLimitY;
    boolean isBrowniano;
    float magBrowniano;
    PVector browniano;


   public  sistema ( Context context){
        Random rnd=new Random();
        particulas = new ArrayList<particulasimple>();
        //origen=new PVector (rnd.nextInt(700),rnd.nextInt(450));
       //origen=new PVector (500,500);
        velocidadinicial=new PVector (rnd.nextInt(20-5),rnd.nextInt(20-5));
        masaparticula=rnd.nextFloat()*30;
        a=rnd.nextInt(255);
        r=rnd.nextInt(255);
        g=rnd.nextInt(255);
        b=rnd.nextInt(255);
        //clas=clase;
        contexto=context;
       life=false;
       sBoxed=false;
       sLimitX=0;
       sLimitY=0;
       isBrowniano=true;
       magBrowniano=.8f;
    }
    public void otraparticula()
    {Random rnd=new Random();

        velocidadinicial=new PVector (rnd.nextInt(1)-5,0);


        //a=rnd.nextInt(255-125);
        float masaparticula=(rnd.nextFloat()*4)-2;
        if (masaparticula<0){masaparticula=0.5f;}

        particulas.add(new particulasimple(origen,masaparticula,life, 200,(int)velocidadinicial.x, (int)velocidadinicial.y,contexto));
       // velocidadinicial=new PVector (rnd.nextInt(3),rnd.nextInt(3));
       // particulas.add(new particulasimple(origen,masaparticula,false, 700,(int)velocidadinicial.x, (int)velocidadinicial.y,contexto));

        Log.d("onDraw: ", ""+origen.x+"   "+origen.y);



    }
    public void colorea_particulas(int red, int green, int blue){
        r=red;
        g=green;
        b=blue;


    }
    public void engorda_particulas(int max, int min){
        Random rnd=new Random();

        //masaparticula=rnd.nextInt(12-10);;
        masaparticula=max;
    }

    public void dibujaparticulas(Canvas canvas){
        //
        RectF limites;
        Paint paint;
        limites = new RectF();
        paint = new Paint();
        paint.setARGB(255, r, g, b);
        paint.setStyle(Paint.Style.FILL);

        //limites.set(origen.x, origen.y, origen.x +50, origen.y +50);
        //canvas.drawOval(limites, paint);
        //
        for (int i = 0; i < particulas.size(); i++) {
            particulasimple p = particulas.get(i);
            p.mostrar(canvas);
            if (p.muerta()){particulas.remove(i);}

        }

    }



    public void acelera_particulas(PVector fuerza){

        for (int i = 0; i < particulas.size(); i++) {
            particulasimple p = particulas.get(i);
            p.acelerar(fuerza);
            if(isBrowniano==true){
                browniano=new PVector (0, magBrowniano);
                browniano.rotate(p.velocidad.heading());
                p.acelerar(browniano);
            }
        }
        }


    public void acelera_particulas(Atractor atractor){

        for (int i = 0; i < particulas.size(); i++) {
            particulasimple p = particulas.get(i);
            p.acelerar(atractor.fuerza(p.posicion));
            if (isBrowniano == true) {
                browniano = new PVector(0, magBrowniano);
                browniano.rotate(p.velocidad.heading());
                p.acelerar(browniano);
            }

        }
    }
    public void actualiza_particula() {
        for (int i = 0; i < particulas.size(); i++) {
            particulasimple p = particulas.get(i);
            p.actualizar();}

    }
    public void isboxed (float limitX, float limitY){
        sBoxed=true;
        sLimitX=limitX;
        sLimitY=limitY;
        for (int i = 0; i < particulas.size(); i++) {
            particulasimple p = particulas.get(i);
            p.boxed(true,limitX,limitY);

        }


    }
    public void central(int x, int y, float intensidad){
        PVector centro;

        for (int i = 0; i < particulas.size(); i++) {
            particulasimple p = particulas.get(i);
            centro=new PVector (x, y);
            PVector rayo=centro;
            rayo.sub(p.posicion);
            //float magnitud=rayo.mag();

            //rayo.normalize();
            //rayo.mult(150/intensidad);
            rayo.div(intensidad);

            //rayo.mult(-1);
            p.acelerar(rayo);

        }

    }

}
