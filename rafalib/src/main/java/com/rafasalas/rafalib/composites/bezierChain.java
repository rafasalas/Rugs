package com.rafasalas.rafalib.composites;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.particles.Mat_point;
import com.rafasalas.rafalib.particles.particulasimple;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;

/**
 * Created by salas on 17/05/2017.
 */

public class bezierChain {
    int nodes, radius_link;
    float masainicial;
    ArrayList<Mat_point> links;
    float[] masses;
    PVector velocidadinicial;
    PVector origen;
    int limX, limY;
    PVector browniano;
    boolean esbrowniano;
    float magbrowniano;
    int r,g,b,a;
    public bezierChain(int width, int height){
        Random rnd=new Random();
        //nodes=28;
        //radius_link=20;
        nodes=10;
        radius_link=200;
        masainicial=(rnd.nextFloat()*30)+15;
        origen=new PVector(rnd.nextInt (width),rnd.nextInt (height));
        links=new ArrayList<Mat_point>();
        esbrowniano=true;
        magbrowniano=.9f;

        float [] masses=new float [nodes];
        for(int i=0; i<nodes; i++){

           // if (i==0){velocidadinicial=new PVector (rnd.nextFloat ()*5,rnd.nextFloat ()*5);} else {velocidadinicial=new PVector (0,0);}
            velocidadinicial=new PVector ((rnd.nextFloat ()*5)+5,(rnd.nextFloat ()*5)+5);
            //masses[i]=masaparticula*((random(10,100))*0.01);
            masses[i]=masainicial/((i+1));
            r=rnd.nextInt(255);
            g=rnd.nextInt(255);
            b=rnd.nextInt(255);
            a=rnd.nextInt(130-100)+100;
            links.add(new Mat_point(origen,  masses[i]));


            links.get(i).eterna=true;
            links.get(i).limite=10;
            links.get(i).boxed(true, width, height);
            links.get(i).velocidad=velocidadinicial;


        }
    }
    public void acelera_particulas(Atractor atractor){

        for (int i = 0; i < links.size(); i++) {
           Mat_point p = links.get(i);
            p.acelerar(atractor.fuerza(p.posicion));
            if (esbrowniano == true) {
                browniano = new PVector(0, magbrowniano);
                browniano.rotate(p.velocidad.heading());
                p.acelerar(browniano);
            }

        }
    }
    public void actualiza_particula() {
        for (int i = 0; i < links.size(); i++) {
            Mat_point l = links.get(i);

            l.actualizar();
            if (i != 0) {
                Mat_point l_ant = links.get(i - 1);
                PVector radius = PVector.sub(l.posicion, l_ant.posicion);
                radius.limit(radius_link);
                l.posicion = PVector.add(l_ant.posicion, radius);
            }
        }
    }
        public void mostrar(Canvas canvas){
            Random rnd=new Random();
        Paint paint;
        paint = new Paint();
        //paint.setARGB(rnd.nextInt(255-110)+110,rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
            paint.setARGB(a,r,g,b);
        paint.setStyle(Paint.Style.FILL);
           paint.setStrokeWidth(2.0f);
            paint.setAntiAlias(true);
            Path p = new Path();
            Mat_point l=links.get(0);
            p.reset();
            p.moveTo(l.posicion.x,l.posicion.y);
            for (int i = 1; i < links.size()-1; i=i+3) {
                Mat_point l1 = links.get(i);
                Mat_point l2 = links.get(i+1);
                Mat_point l3 =  links.get(i+2);
                if (i==links.size()-2){l3= links.get(1);}
                //p.quadTo(l1.posicion.x, l1.posicion.y, l2.posicion.x,l2.posicion.y);
                p.cubicTo(l1.posicion.x, l1.posicion.y, l2.posicion.x,l2.posicion.y, l3.posicion.x,l3.posicion.y);
                //p.moveTo(l3.posicion.x,l3.posicion.y);
                    }
                    p.close();
            canvas.drawPath(p,paint);
    }




}
