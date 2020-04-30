package com.app.flutter.mtpl.selfie_ocr_mtpl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

public class TransparentCircle extends View {

    Bitmap bm;
    Canvas cv;
    Paint eraser;
    Paint stroke;

    public TransparentCircle(Context context) {
        super(context);
        Init();
    }

    public TransparentCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    public TransparentCircle(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    private void Init(){

        eraser = new Paint();
        stroke = new Paint();
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        eraser.setAntiAlias(true);

        stroke.setStyle(Paint.Style.STROKE);
        stroke.setColor(Color.RED);
        stroke.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        if (w != oldw || h != oldh) {
            bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            cv = new Canvas(bm);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {

        int w = getWidth();
        int h = getHeight();
        int radius = w > h ? h / 3 : w / 3;

        bm.eraseColor(Color.TRANSPARENT);
        cv.drawColor(getContext().getColor(R.color.trans));
        cv.drawCircle(w / 2, h / 2, radius, eraser);
        cv.drawCircle(w / 2, h / 2, radius, stroke);
        canvas.drawBitmap(bm, 0, 0, null);
        super.onDraw(canvas);
    }
}
