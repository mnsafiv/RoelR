package ru.safonoviv.roelr.GenerateObject.Component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapMulti extends BitmapComponent {

    private final Context context;
    private final int bitmapId;
    private Bitmap bitmap;
    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;
    private final double distance;


    public BitmapMulti(Context context, int bitmapId, int startX, int startY, int endX, int endY, double distance) {
        this.bitmapId = bitmapId;
        this.context = context;
        this.distance = distance;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createBitmap(bitmap, startX, startY, endX, endY);
    }

    public BitmapMulti(Context context, int bitmapId, int size, int startX, int startY, int endX, int endY, double distance) {
        this.bitmapId = bitmapId;
        this.context = context;
        this.distance = distance;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createBitmap(bitmap, startX, startY, endX, endY);
        if (bitmap.getHeight() > size || bitmap.getWidth() > size) {
            bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);

        }
    }


    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public Bitmap getCopyOriginalBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        return Bitmap.createBitmap(bitmap, startX, startY, endX, endY);
    }

    @Override
    public Bitmap resetToOriginalBitmapAndGetRef() {
        bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createBitmap(bitmap, startX, startY, endX, endY);
        return bitmap;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
