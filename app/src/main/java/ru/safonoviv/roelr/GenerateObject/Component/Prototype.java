package ru.safonoviv.roelr.GenerateObject.Component;

import android.graphics.Bitmap;
import lombok.Getter;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Display.Camera;

import java.util.Map;
import java.util.Objects;

public abstract class Prototype {
    protected Map<String, BitmapComponent> bitmaps;
    protected Map<String,Bitmap> cashedBitmaps;
    @Getter
    protected Map<String, BitmapConfig> randomKeys;
    @Getter
    protected Map<String, BitmapConfig> uniqueKeys;


    protected Camera camera;

    public Prototype() {
        this.camera= Setting.getInstance().getCamera();


    }


    public Bitmap getBackGroundBitmap(String backGroundType) {
        return Objects.requireNonNull(bitmaps.get(backGroundType)).getBitmap();
    }



    public Bitmap getCopyFullBackGroundBitmap(String backGroundType) {
        return Objects.requireNonNull(bitmaps.get(backGroundType)).getCopyOriginalBitmap();
    }

    public BitmapComponent getBitmapComponent(String backGroundType) {
        return bitmaps.get(backGroundType);
    }



}
