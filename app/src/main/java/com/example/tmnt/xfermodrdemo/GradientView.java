package com.example.tmnt.xfermodrdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tmnt on 2016/4/8.
 */
public class GradientView extends View {
    private Bitmap src, ref;
    private Paint paint;
    private PorterDuffXfermode duffXfermode;

    public GradientView(Context context) {
        super(context);
        init();
    }


    public GradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        src = BitmapFactory.decodeResource(getResources(), R.drawable.tttt);


        Matrix m = new Matrix();
        m.setScale(1f, -1f);
        ref = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, true);

        paint = new Paint();
        paint.setShader(new LinearGradient(0, src.getHeight(), 0, src.getHeight() + src.getHeight() / 4,
                0XDD000000, 0X1000000, Shader.TileMode.CLAMP));

        duffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(src, 0, 0, null);
        canvas.drawBitmap(ref, 0, src.getHeight(), null);
        paint.setXfermode(duffXfermode);
        canvas.drawRect(0, src.getHeight(), ref.getWidth(), ref.getHeight() * 2, paint);
        paint.setXfermode(null);
    }
}
