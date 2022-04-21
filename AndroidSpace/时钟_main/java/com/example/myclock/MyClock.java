package com.example.myclock;

import static java.lang.System.currentTimeMillis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Date;

public class MyClock extends View {
    private long time = 0;
    public MyClock(Context context) {
        super(context);
    }

    public MyClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setTime(long time){
        this.time = time;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int r = ((this.getWidth() > this.getHeight()) ? this.getHeight() : this.getWidth()) / 2 - 8;
        int centerX = this.getWidth() / 2;
        int centerY = this.getHeight() / 2;

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(16);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, r - 2, paint);

        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.FILL);

        for(int i = 0; i < 360; i = i + 6) {
            double deltaX = (r - 30) * Math.cos(i / 180.0 * Math.PI);
            double deltaY = (r - 30) * Math.sin(i / 180.0 * Math.PI);
            int cx = centerX + (int) deltaX;
            int cy = centerY + (int) deltaY;
            int r1 = (i % 30 == 0) ? 15 : 8;
            canvas.drawCircle(cx, cy, r1, paint);
        }

        double degree = 0;
//        long time = System.currentTimeMillis();

        long minute = time / 1000 / 60 % 60;
        double hour = (time / 1000 / 60 / 60 % 12) + minute / 60.0;
        degree = hour * 30 - 90;
        double deltaX = (r - 160) * Math.cos(degree / 180 * Math.PI);
        double deltaY = (r - 160) * Math.sin(degree / 180 * Math.PI);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);
        canvas.drawLine(centerX, centerY,
                (int)(centerX + deltaX), (int)(centerY + deltaY),
                paint);



        degree = minute * 6 - 90;
        deltaX = (r - 100) * Math.cos(degree / 180 * Math.PI);
        deltaY = (r - 100) * Math.sin(degree / 180 * Math.PI);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        canvas.drawLine(centerX, centerY,
                (int)(centerX + deltaX), (int)(centerY + deltaY),
                paint);


        long second = time / 1000 % 60;
        degree = second * 6 - 90;
        deltaX = (r - 60) * Math.cos(degree / 180 * Math.PI);
        deltaY = (r - 60) * Math.sin(degree / 180 * Math.PI);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        canvas.drawLine(centerX, centerY,
                (int)(centerX + deltaX), (int)(centerY + deltaY),
                paint);

    }
}
