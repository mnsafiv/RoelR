package ru.safonoviv.roelr.GenerateObject.Component;

import android.graphics.Bitmap;

public class BitmapSingle extends BitmapComponent {

    private Bitmap bitmap;
    private final double distance;


    public BitmapSingle(Bitmap bitmap, double distance) {
        this.bitmap=bitmap;
        this.distance = distance;
    }


    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public Bitmap getCopyOriginalBitmap() {
        return bitmap;
    }

    @Override
    public Bitmap resetToOriginalBitmapAndGetRef() {
        return bitmap;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
