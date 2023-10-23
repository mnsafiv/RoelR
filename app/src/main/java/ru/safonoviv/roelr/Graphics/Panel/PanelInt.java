package ru.safonoviv.roelr.Graphics.Panel;


import android.graphics.Canvas;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ActionOnClick;

public interface PanelInt {
    ActionOnClick getActiveCollision(int positionX, int positionY);

    boolean getCollision(int positionX, int positionY);

    void draw(Canvas canvas);

    void shift(int distanceX, int distanceY);

    boolean getAction();
}
