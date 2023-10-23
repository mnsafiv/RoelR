package ru.safonoviv.roelr.Graphics.Panel.RectItem;

import android.graphics.Rect;

public abstract class ActionOnClickImpl implements ActionOnClick {

    protected int positionX = -1;
    protected int positionY = -1;

    protected Rect rect;


    @Override
    public Rect getRect() {
        return rect;
    }


    @Override
    public void setPosition(int defaultX, int defaultY) {
        positionX = defaultX;
        positionY = defaultY;
        rect.offsetTo(positionX, positionY);


    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public boolean getCollision(int positionX, int positionY) {
        return rect.contains(positionX, positionY);
    }



    @Override
    public void setStart(Rect rect) {
        positionX = rect.left;
        positionY = rect.top;
        this.rect.set(rect);
    }

    @Override
    public void shiftY(int distanceY) {
        positionY += distanceY;
        rect.offsetTo(positionX, positionY);
    }

    @Override
    public void shiftX(int distanceX) {
        positionX += distanceX;
        rect.offsetTo(positionX, positionY);
    }


    @Override
    public void setStartX(Rect rect) {
        positionX = rect.left;
        this.rect.set(rect);
        this.rect.offsetTo(positionX,positionY);
    }


    @Override
    public String getKey() {
        return null;
    }



    public void setPositionX(int positionsX) {
        positionX = positionsX;
        this.rect.offsetTo(positionX,positionY);
    }

    public void setPositionX(float positionsX) {
        positionX = (int) positionsX;
        this.rect.offsetTo(positionX,positionY);
    }
}
