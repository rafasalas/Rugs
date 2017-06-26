package com.rafasalas.rugs;

import android.content.Context;
import android.graphics.Canvas;

import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.composites.bezierBundle;
import com.rafasalas.rafalib.composites.cloud;
import com.rafasalas.rafalib.composites.sistema;

import processing.core.PVector;

/**
 * Created by salas on 19/06/2017.
 */

public class lienzo {
    Atractor hole, lateral1, lateral2, lateral3,lateral4;
    //Mandala mandy;
    PVector centro, part;
    //sistema sistem, sistem2;
    bezierBundle sistem, sistem2;

    bezierBundle sistem3, sistem4;

    public float intensity;

    final global dataglobal;

    public lienzo(Context context, int width, int height){
        part=new PVector(10,-5);
        centro=new PVector(500, 500);
        dataglobal = (global) context;
        intensity=-1;
        /*sistem=new cloud(context, 75);
        sistem.isboxed(width, height);
        sistem2=new cloud(context, 75);
        sistem2.isboxed(width, height);*/
        sistem=new bezierBundle(width,height,6);
        sistem2=new bezierBundle(width,height,6);
        sistem3=new bezierBundle(width,height,6);
        sistem4=new bezierBundle(width,height,6);


        hole=new Atractor(1);
        lateral1=new Atractor(1);
        lateral2=new Atractor(1);
        lateral3=new Atractor(1);
        lateral4=new Atractor(1);
        hole.sentido=-(float)1;

        hole.posicion.x=width/2;
        hole.posicion.y=height/2;
        lateral1.posicion=new PVector(width/2, height/8);
        lateral2.posicion=new PVector(width/8, height/2);
        lateral3.posicion=new PVector(7*(width/8), height/2);
        lateral4.posicion=new PVector((width/2), 7*(height/8));



    }


    public void draw(Canvas canvas, int width, int height) {


        canvas.drawColor(0xFF000000);
        hole.sentido=-1-dataglobal.getIntensity();;
        lateral1.sentido=-0.5f*dataglobal.getIntensity();
        lateral2.sentido=-0.5f*dataglobal.getIntensity();
        lateral3.sentido=-0.5f*dataglobal.getIntensity();
        lateral4.sentido=-0.5f*dataglobal.getIntensity();
        /*sistem.acelera_particulas(hole);
        sistem2.acelera_particulas(hole);
        sistem.acelera_particulas(lateral1);
        sistem.acelera_particulas(lateral2);
        sistem2.acelera_particulas(lateral3);
        sistem2.acelera_particulas(lateral4);
        sistem.actualiza_particula();
        sistem.dibujaparticulas(canvas);
        sistem2.actualiza_particula();
        sistem2.dibujaparticulas(canvas);*/
        sistem.acelerador(hole);
        sistem2.acelerador(hole);

        sistem3.acelerador(hole);
        sistem4.acelerador(hole);

        sistem.acelerador(lateral1);
        sistem.acelerador(lateral2);
        sistem2.acelerador(lateral3);
        sistem2.acelerador(lateral4);

        sistem3.acelerador(lateral1);
        sistem3.acelerador(lateral3);
        sistem4.acelerador(lateral2);
        sistem4.acelerador(lateral4);

        sistem.actualizar();
        sistem.mostrar(canvas);

        sistem2.actualizar();
        sistem2.mostrar(canvas);

        sistem3.actualizar();
        sistem3.mostrar(canvas);
        sistem4.actualizar();
        sistem4.mostrar(canvas);


    }



}
