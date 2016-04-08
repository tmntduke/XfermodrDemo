package com.example.tmnt.xfermodrdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by tmnt on 2016/4/8.
 */
public class SurfaceViewTemp extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;
    private Canvas canvas;//绘制的canvas
    private boolean isDrawing;//绘制的标志位

    public SurfaceViewTemp(Context context) {
        super(context);
        initView();
    }

    public SurfaceViewTemp(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    private void initView() {
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

    @Override
    public void run() {
        while (isDrawing) {
            drawView();
        }
    }

    private void drawView() {
        canvas = holder.lockCanvas();
        if (canvas != null) {
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
