package ru.safonoviv.roelr.Graphics.Display;

import lombok.NoArgsConstructor;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Common.Utils;


@NoArgsConstructor
public final class CameraResolution {
    private final double resolutionDefault= DefaultValue.DEFAULT_CAMERA_RESOLUTION;
    private double resolutionCurrent= DefaultValue.DEFAULT_CAMERA_RESOLUTION;

    private double distanceStart;

    public void changeResolution(float x1, float y1, float x2, float y2){
        resolutionCurrent= (int) (resolutionCurrent+distanceStart- Utils.getDistanceBetweenTwoPoints(x1,y1,x2,y2)/ DefaultValue.CAMERA_SENSE);
    }
    public void startChangeResolution(float x1, float y1, float x2, float y2){
        distanceStart= Utils.getDistanceBetweenTwoPoints(x1,y1,x2,y2)/100;
    }

    public double getResolutionSize(){
        return resolutionDefault/resolutionCurrent;
    }
}
