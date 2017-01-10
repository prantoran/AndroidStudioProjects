package com.prantoran.rectangles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;


/**
 * Created by pinku on 7/3/16.
 */
public class DrawingTheBall extends View {
    public DrawingTheBall(Context context){
        super(context);

    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Rect ourRect = new Rect();
        ourRect.set(0,0,canvas.getWidth(),canvas    .getHeight()/2);
        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);
        canvas.drawRect(ourRect,blue);
    }
}
