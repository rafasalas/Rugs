package com.rafasalas.rafalib.particles;

/**
 * Created by salas on 27/09/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;

import processing.core.PVector;

//import com.rafasalas.rafalib.particles.puntocolor;
import com.rafasalas.rafalib.vectorgraph.vectordraw;
public class particulasimple extends puntocolor {
    public vectordraw dibujo;
    public particulasimple(PVector pos, float masa, boolean siempre, int vida , int viX, int viY, Context context){
                    super(pos, masa);
                    velocidad.x=viX;
                    velocidad.y=viY;
                    eterna=siempre;
                    lifespan=vida;
                    dibujo=new vectordraw(context);
    }

    public void mostrar(Canvas canvas){

        RectF limites;
        Paint paint;


        limites = new RectF();
        float head=(float)Math.toDegrees(velocidad.heading());
        paint = new Paint();

        if (eterna==false){a=lifespan;}
        if (a<0 || a>255){a=0;}
        //paint.setARGB(a, r, g, b);
        paint.setARGB(a,0,0,0);
        paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(10);

        paint.setAntiAlias(true);
        //paint.setStrokeWidth(masa*2);
       // canvas.drawPoint(posicion.x,posicion.y,paint);
        canvas.drawCircle(posicion.x,posicion.y,masa,paint);



        //limites.set(posicion.x, posicion.y, posicion.x + masa , posicion.y + masa );
        //  canvas.drawOval(limites, paint);



        //canvas.save();
        //canvas.translate(posicion.x, posicion.y);
        //canvas.rotate(head, 0, 0);
        //limites.set(0, 0, masa * 3, masa*2);
        //canvas.drawRect(limites, paint);
       // canvas.restore();

    }

public void carga_dibujo (String nombre, String carpeta, String paquete){
        dibujo.loadsvg(nombre, carpeta, paquete);

    }
    public void mostrar_dibujo(Canvas canvas, double scale){

       dibujo.resize(scale,0,0);
        float head=(float)Math.toDegrees(velocidad.heading());


        if (eterna==false){a=lifespan;};
        if (a<0 || a>255){a=0;}
        //dibujo.colorize(a,r,g,b);

        canvas.save();
        canvas.translate(posicion.x, posicion.y);
        //canvas.rotate(head, 0, 0);
        dibujo.dibujar(canvas);
        canvas.restore();

    }
    public void colorize_dibujo(int opacity, int r, int g, int b) {



        dibujo.colorize(opacity,r,g,b);

    }
    public void alfa(int opacity) {



        dibujo.alfa(opacity);

    }

}
