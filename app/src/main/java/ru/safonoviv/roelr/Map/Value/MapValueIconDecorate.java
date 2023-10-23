package ru.safonoviv.roelr.Map.Value;

import android.graphics.Canvas;

public interface MapValueIconDecorate {
    void draw(Canvas canvas);

    double getMapValuePositionY();
}
