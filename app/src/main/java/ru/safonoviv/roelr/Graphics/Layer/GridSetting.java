package ru.safonoviv.roelr.Graphics.Layer;

import lombok.Getter;
import ru.safonoviv.roelr.Common.DefaultValue;

public class GridSetting {
    @Getter
    private final int capacity = DefaultValue.FIELD_CAPACITY;
    @Getter
    private final float size;
    private final float sizeMargin;
    @Getter
    private float height;
    @Getter
    private float nextX;
    @Getter
    private float nextY;
    @Getter
    private final GridType grid;


    public GridSetting(GridType grid) {
        this.grid = grid;
        this.size = DefaultValue.DEFAULT_FIELD_SIZE;
        int MARGIN = 2;
        sizeMargin = DefaultValue.DEFAULT_FIELD_SIZE + MARGIN;

        switch (grid) {
            case HexVertical:
                initializeVertical();
                break;
            case HexHorizontal:
                initializeHorizontal();
                break;
        }
    }

    private void initializeHorizontal() {
        double size = sizeMargin * Math.cos(Math.toRadians(30));
        height = (float) size / 2;
        nextX = (float) size;
        nextY = (float) (sizeMargin * 1.5);
    }

    private void initializeVertical() {
        double size = sizeMargin * Math.cos(Math.toRadians(30));
        height = (float) size / 2;
        nextX = (float) (sizeMargin * 1.5);
        nextY = (float) size;
    }
}
