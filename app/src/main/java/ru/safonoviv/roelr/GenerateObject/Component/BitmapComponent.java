package ru.safonoviv.roelr.GenerateObject.Component;

import android.graphics.Bitmap;


public abstract class BitmapComponent {

    public abstract double getDistance();


    public abstract Bitmap getBitmap();

    public abstract Bitmap getCopyOriginalBitmap();

    public abstract Bitmap resetToOriginalBitmapAndGetRef();

    public abstract void setBitmap(Bitmap bitmap);
}
