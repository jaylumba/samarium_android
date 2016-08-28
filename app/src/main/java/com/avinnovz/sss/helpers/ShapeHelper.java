package com.avinnovz.sss.helpers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by jayan on 8/29/2016.
 */
public class ShapeHelper {

    public static Drawable getPointedRectangleBg() {
        Shape shape = new Shape() {
            Path path = new Path();

            @Override
            protected void onResize(float width, float height) {
                path.reset();
                path.lineTo(width - height / 2, 0);
                path.lineTo(width, height / 2);
                path.lineTo(width - height / 2, height);
                path.lineTo(0, height);
                path.close();
            }

            @Override
            public void draw(Canvas canvas, Paint paint) {
                canvas.drawPath(path, paint);
            }
        };
        ShapeDrawable d = new ShapeDrawable(shape);
        d.getPaint().setColor(0xbb000000);
        return d;
    }
}
