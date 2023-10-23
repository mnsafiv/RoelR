package ru.safonoviv.roelr.Map.Value;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.GenerateObject.Bonus.BonusPrototype;
import ru.safonoviv.roelr.GenerateObject.Bonus.BonusType;
import ru.safonoviv.roelr.GenerateObject.Component.PrototypeDecor;
import ru.safonoviv.roelr.Graphics.Display.Camera;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class IconCoordinate implements MapValueIconDecorate {
    private final PrototypeDecor prototypeDecor;
    private final Camera camera;
    private Long id;
    private double positionX;
    private double positionY;
    private double resolution;
    private String iconType;
    private Bitmap bitmap;


    private byte alpha = -1;


    @JsonIgnore
    private MapValue mapValueOwner;


    //bonus
    private Map<BonusType, Double> bonus;


    public IconCoordinate(double positionX, double positionY, double resolution, String key, String iconType, MapValue mapValueOwner) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.resolution = resolution;
        this.iconType = iconType;
        this.bonus = BonusPrototype.getInstance().getBonus(key).getBonusItems();
        this.prototypeDecor = PrototypeDecor.getInstance();
        this.camera = Setting.getInstance().getCamera();

        this.mapValueOwner = mapValueOwner;
        this.bitmap = prototypeDecor.getBackGroundBitmap(iconType, resolution);

    }


    @Override
    public void draw(Canvas canvas) {
        if (bitmap == null) {
            return;
        }

        canvas.drawBitmap(bitmap,
                camera.getCenterBitmapToCameraX(bitmap, mapValueOwner.getCenterX() + positionX),
                camera.getCenterBitmapToCameraY(bitmap, mapValueOwner.getCenterY() + positionY),
                null);
    }

    @Override
    public double getMapValuePositionY() {
        return positionY + mapValueOwner.getCenterY();
    }
}
