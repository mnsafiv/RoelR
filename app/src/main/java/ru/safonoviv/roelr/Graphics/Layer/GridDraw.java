package ru.safonoviv.roelr.Graphics.Layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public abstract class GridDraw implements Cloneable {

    protected float radius;


    public GridDraw(float radius) {
        this.radius = radius;
    }


    public abstract void draw(Canvas canvas, int coordinate, Paint paint, FieldSetting setting);

    public abstract void draw(Canvas canvas, float positionX, float positionY, Paint paint, FieldSetting setting);

    public abstract Path getPath();


    @NonNull
    @NotNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract void draw(Canvas canvas, int coordinate, Bitmap bitmap, FieldSetting setting);
}
