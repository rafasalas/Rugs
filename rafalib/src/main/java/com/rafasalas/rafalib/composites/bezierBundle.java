package com.rafasalas.rafalib.composites;

import android.graphics.Canvas;

import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.particles.Mat_point;

import java.util.ArrayList;

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
            mChains.add(new bezierChain(width,height));
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




}
