package com.rafasalas.rafalib.composites;

import android.content.Context;
import android.graphics.Canvas;

import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.particles.particulasimple;


import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;

/**
 * Created by salas on 06/10/2016.
 */

public class red {
    public int initX, initY,salto;
    public int numrow, numcol, heightrow, widthcol;

    public ArrayList<particulasimple> vertice;
    public boolean tresbolillo;
    public red(int X, int Y, int filas, int columnas, int anchofilas, int anchocolumnas, boolean tresbo, int paso, Context context) {

        initX = X;
        initY = Y;
        numrow = filas;
        numcol = columnas;
        heightrow = anchofilas;
        widthcol = anchocolumnas;
        tresbolillo=tresbo;

        vertice = new ArrayList<particulasimple>();
        Random rnd=new Random();
        int numerototal = numrow * numcol;
        int Xparcial = 0;
        int Yparcial = 0;
        for (int i = 0; i < numerototal; i++) {
            PVector nodo = new PVector(initX + Xparcial, initY + Yparcial);
            vertice.add(new particulasimple(nodo, rnd.nextInt(15), true, 0, 0, 0, context));
            if (i % numcol == 0) {
                Xparcial = 0;

                Yparcial = Yparcial + heightrow;
            } else {
                Xparcial = Xparcial + widthcol;
                }
          if (tresbolillo==true & i%2==0){Yparcial = Yparcial + paso;}
            if (tresbolillo==true & i%2!=0){Yparcial = Yparcial - paso;}
        }

    }
    public void rozamiento (float roce){
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.resistencia=true;
            vert.factor_rozamiento=roce;
        }
    }
    public void masa_aleatoria(int limite) {
        Random rnd = new Random();

        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.masa = rnd.nextInt(limite-20)+20;

        }
    }
    public void masa_fija(int masa){
        Random rnd=new Random();
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.masa = masa;

        }

    }
    public void muelle (float elastico) {
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.muelle = true;
            vert.kmuelle = elastico;
        }
    }
    public void cuerda (float longitud) {
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.cuerda = true;
            vert.coto = longitud;
        }
    }
    public void carga_dibujo (String nombre, String carpeta, String paquete){
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.carga_dibujo(nombre, carpeta, paquete);
        }

    }

    public void invertir_masa(){
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.masainversa=true;

        }
    }

public void acelerar(Atractor atractor){
    for (int i = 0; i < vertice.size(); i++) {
        particulasimple vert = vertice.get(i);
        vert.acelerar(atractor.fuerza(vert.posicion));
        vert.actualizar();

    }
}
    public void acelerar(PVector fuerza){
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.acelerar(fuerza);
            vert.actualizar();

        }
    }
    public void mostrar(Canvas canvas) {
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.mostrar(canvas);

        }
    }
    public void mostrar_dibujo(Canvas canvas, float factor) {
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.mostrar_dibujo(canvas,(float)(vert.masa/factor));

        }
    }
    public void colorize_dibujo(int a, int r, int g, int b) {
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.colorize_dibujo(a,r,g,b);

        }
    }
    public void alfa(int a) {
        for (int i = 0; i < vertice.size(); i++) {
            particulasimple vert = vertice.get(i);
            vert.alfa(a);

        }
    }
}