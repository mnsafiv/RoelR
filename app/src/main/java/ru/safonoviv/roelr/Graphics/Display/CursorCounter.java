package ru.safonoviv.roelr.Graphics.Display;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.safonoviv.roelr.Common.Utils;

@Getter
@EqualsAndHashCode
public class CursorCounter {
    private float positionX;
    private float positionY;

    public CursorCounter(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }


    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public boolean contains(int x, int y) {
        return positionX == x && positionY == y;
    }

    public boolean checkDistance(int distance, float positionX, float positionY) {
        return Utils.getDistanceBetweenTwoPoints(positionX, positionY, this.positionX, this.positionY) < distance;
    }
}
