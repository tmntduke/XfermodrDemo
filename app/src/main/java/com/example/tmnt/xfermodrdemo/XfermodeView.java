package com.example.tmnt.xfermodrdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tmnt on 2016/4/8.
 */
public class XfermodeView extends View {
    private Bitmap back, fornt;
    private Paint paint;
    private Canvas canvas;
    private Path path;

    public XfermodeView(Context context) {
        super(context);
        init();
    }


    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(0);//在初始化时要将画笔设置为透明
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(35);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        Log.i("init", "start");

        path = new Path();
        back = BitmapFactory.decodeResource(getResources(), R.drawable.test);

        fornt = Bitmap.createBitmap(back.getWidth(), back.getHeight(), Bitmap.Config.ARGB_8888);

        canvas = new Canvas(fornt);
        canvas.drawColor(Color.GRAY);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:

                path.lineTo(event.getX(), event.getY());
                canvas.drawPath(path, paint);
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_DOWN:
                path.reset();
                Log.i("onTouch", "start");

                path.moveTo(event.getX(), event.getY());
                break;
        }

         invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(back, 0, 0, null);
        canvas.drawBitmap(fornt, 0, 0, null);
    }
}
