package ru.safonoviv.roelr.Graphics.Panel.RectItem;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface ActionOnClick {
    Rect getRect();

    void draw(Canvas canvas);

    void setPosition(int defaultX, int defaultY);

    void updateStatus();

    int getPositionX();

    int getPositionY();

    boolean getCollision(int positionX, int positionY);

    void setStart(Rect rect);

    boolean getAction();

    void shiftY(int distanceY);

    void shiftX(int distanceX);

    void setStartX(Rect rect);

    String getKey();
}
