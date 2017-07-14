package com.rafasalas.rafalib.composites;

import android.graphics.Canvas;
import android.graphics.Color;

import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.particles.Mat_point;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;

/**
 * Created by salas on 05/06/2017.
 */

public class bezierBundle {
    ArrayList<bezierChain> mChains;
    int numberChains;
    int limX, limY;
    public bezierBundle(int width, int height, int numero){
        mChains=new ArrayList<bezierChain>();
        for (int i=0;i<numero; i++){
            mChains.add(new bezierChain(width,height, (float)i));
        }
    }
    public void acelerador (Atractor atractor){
        for (int i = 0; i < mChains.size(); i++) {
            bezierChain p = mChains.get(i);
            p.acelera_particulas(atractor);
             }
    }

    public void actualizar (){
        for (int i = 0; i < mChains.size(); i++) {
            bezierChain p = mChains.get(i);
            p.actualiza_particula();
        }
    }

    public void mostrar (Canvas canvas){
        for (int i = 0; i < mChains.size(); i++) {
            bezierChain p = mChains.get(i);
            p.mostrar(canvas);
        }
    }

    public void colorize_random(){
        for (int i = 0; i < mChains.size(); i++) {
            bezierChain p = mChains.get(i);
            p.colorize_random();
        }

    }
    public void colorize_palette(float[] hsv){
        for (int i = 0; i < mChains.size(); i++) {
            bezierChain p = mChains.get(i);
            p.colorize_palette(hsv);
        }
    }
    public void remasses(){
        Random rnd=new Random();
        for (int i = 0; i < mChains.size(); i++) {
            bezierChain p = mChains.get(i);
            p.mass_increment=(rnd.nextFloat()*2)+0.5f;

        }
    }


}
