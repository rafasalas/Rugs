package com.rafasalas.rafalib.composites;

import android.content.Context;
import android.util.Log;

import com.rafasalas.rafalib.particles.particulasimple;

import java.util.Random;

import processing.core.PVector;

/**
 * Created by salas on 19/04/2017.
 */

public class cloud extends sistema {
    public int numberofparticles;
    public cloud(Context context, int particles) {
        super(context);
        numberofparticles=particles;
        life=true;
        generate();
    }
    public void generate(){
        for (int i = 0; i < numberofparticles; i++) {
           otraparticula(null, null);
        }
    }

    public void otraparticula(PVector porigen, PVector vinicial)
    {   Random rnd=new Random();
        int width =contexto.getResources().getDisplayMetrics().widthPixels;
        int height = contexto.getResources().getDisplayMetrics().heightPixels;

        Log.v("size", " w: "+ width + "h: "+height);
        if(sBoxed){
            width=(int)sLimitX;
            height=(int)sLimitY;

        }
        if (porigen==null){
            origen=new PVector (rnd.nextInt(width),rnd.nextInt(height));}
        else{origen=porigen;}
        if (vinicial==null){
            velocidadinicial=new PVector (rnd.nextInt(width),rnd.nextInt(height));

            //velocidadinicial=new PVector (0,50+(rnd.nextInt(20)-10));

            //velocidadinicial=new PVector ((rnd.nextInt(30-10))*-1,80);
        }
        else {velocidadinicial=vinicial;}

        //a=rnd.nextInt(255-125);
        float masaparticula=(rnd.nextFloat()*7)+3;

        particulas.add(new particulasimple(origen,masaparticula,life, 200,(int)velocidadinicial.x, (int)velocidadinicial.y,contexto));
        // velocidadinicial=new PVector (rnd.nextInt(3),rnd.nextInt(3));
        // particulas.add(new particulasimple(origen,masaparticula,false, 700,(int)velocidadinicial.x, (int)velocidadinicial.y,contexto));

        Log.d("onDraw: ", ""+origen.x+"   "+origen.y);



    }
}
