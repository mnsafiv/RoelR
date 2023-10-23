package ru.safonoviv.roelr.Map.Value;


import lombok.Getter;
import lombok.Setter;
import ru.safonoviv.roelr.Common.Setting;

import java.util.Objects;

@Getter
@Setter
public class CoordinatePropertiesImpl implements CoordinateProperties {
    private final double centerX;
    private final double centerY;
    private double actualCenterX;
    private double actualCenterY;
    private int coordinate;


    public CoordinatePropertiesImpl(int coordinate) {
        this.coordinate = coordinate;
        this.centerX = Setting.getInstance().getFieldSetting().getCenterAreaPositionX(coordinate);
        this.centerY = Setting.getInstance().getFieldSetting().getCenterAreaPositionY(coordinate);
        this.actualCenterX = centerX;
        this.actualCenterY = centerY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatePropertiesImpl that = (CoordinatePropertiesImpl) o;
        return coordinate == that.coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
