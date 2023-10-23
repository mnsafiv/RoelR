package ru.safonoviv.roelr.Graphics.Panel.RectItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class StructureTextInt extends ActionOnClickImpl {
    private float textSize;
    private int strInt;
    private String str;
    private int shiftLeft;
    private int shiftRight;
    private Paint paintText;


    public StructureTextInt(int number, Paint paintStatusInfo, int height, int shiftLeft, int shiftRight) {
        this.str = String.valueOf(number);
        this.strInt = number;
        this.shiftLeft = shiftLeft;
        this.shiftRight = shiftRight;
        final int color = paintStatusInfo.getColor();
        textSize = 20;
        this.paintText = new Paint();
        this.paintText.setColor(color);
        rect = new Rect();


        calculateSize();
        while (rect.height() < height / 3) {
            calculateSize();
        }

        rect = new Rect(rect.left - shiftLeft, 0, rect.right + shiftRight, height);
        rect.offsetTo(rect.left + shiftLeft, rect.top);


    }

    private void calculateSize() {
        paintText.setTextSize(textSize++);
        paintText.getTextBounds(str, 0, str.length(), rect);

    }

    @Override
    public void setPosition(int defaultX, int defaultY) {
        positionX = defaultX;
        positionY = defaultY;
        rect.offsetTo(positionX, positionY);
        positionX += shiftLeft;
        positionY = defaultY + rect.height() / 3 * 2;
    }


    @Override
    public void setStartX(Rect rect) {
        positionX = rect.left;
        final int width = rect.width();
        final int height = rect.height();
        this.rect.set(positionX, positionY, positionX + width, positionY + height);

        positionY = this.rect.bottom - rect.height() / 3;


    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(str, positionX, positionY, paintText);
    }


    public int getStrInt(){
        return strInt;
    }

    @Override
    public boolean getAction() {

        return false;
    }


    public void setInt(int number) {
        this.str = String.valueOf(number);
        this.strInt = number;
    }
}
