package ru.safonoviv.roelr.Object;

import android.graphics.Canvas;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Display.Camera;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;


@EqualsAndHashCode
public abstract class ObjectProperties {
    @Getter
    @Setter
    protected double positionX;
    @Getter
    @Setter
    protected double positionY;
    @Getter
    protected int coordinate;
    @Getter
    @Setter
    protected double motionVectorX = 0.0;
    @Getter
    @Setter
    protected double motionVectorY = 0.0;
    protected Camera camera;
    protected FieldSetting setting;

    public ObjectProperties(double positionX, double positionY) {
        this.camera = Setting.getInstance().getCamera();
        this.setting = Setting.getInstance().getFieldSetting();
        this.positionX = positionX;
        this.positionY = positionY;
        this.coordinate = setting.getCoordinate(positionX, positionY);
    }


    public void update() {
        if(motionVectorX !=0 || motionVectorY !=0){
            positionX = positionX + motionVectorX;
            positionY = positionY + motionVectorY;
            coordinate = setting.getCoordinate((float) positionX, (float) positionY);
        }
    }

    public abstract void draw(Canvas canvas);

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
        this.positionX = setting.getCenterAreaPositionX(coordinate);
        this.positionY = setting.getCenterAreaPositionY(coordinate);
    }
}
