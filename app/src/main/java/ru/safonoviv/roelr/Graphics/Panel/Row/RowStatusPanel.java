package ru.safonoviv.roelr.Graphics.Panel.Row;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import lombok.Getter;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ActionOnClick;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ActionOnClickImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class RowStatusPanel implements ActionOnClick {

    protected Context context;
    protected Rect rectCur;
    protected Paint paintBorder;
    protected Paint paintText;
    @Getter
    protected RowStatusPanelPreference rowStatusPanelPreference;
    @Getter
    protected final double sizeMultiplier;
    protected List<ActionOnClickImpl> messages = new ArrayList<>();


    public RowStatusPanel(Context context, int groupId, Paint paintBorder, Paint paintText, double sizeMultiplier) {
        this.context = context;
        this.sizeMultiplier = sizeMultiplier;
        this.paintBorder = paintBorder;
        this.paintText = paintText;
        this.rowStatusPanelPreference = new RowStatusPanelPreference(groupId, messages);
    }



    public abstract void draw(Canvas canvas);

    public void update() {

    }

    public final List<ActionOnClickImpl> getMessages() {
        return messages;
    }

    public void shift(int distanceX, int distanceY){

    }

    public void drawPart(Canvas canvas) {

    }

    @Override
    public void setPosition(int defaultX, int defaultY) {

    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int getPositionX() {
        return 0;
    }

    @Override
    public int getPositionY() {
        return 0;
    }

    @Override
    public boolean getCollision(int positionX, int positionY) {
        return rectCur.contains(positionX,positionY);
    }

    @Override
    public void setStart(Rect rect) {

    }

    @Override
    public boolean getAction() {
        return false;
    }

    @Override
    public void shiftY(int distanceY) {

    }

    @Override
    public void shiftX(int distanceX) {

    }

    @Override
    public void setStartX(Rect rect) {

    }

    @Override
    public String getKey() {
        return null;
    }

    public Rect getRect() {
        return rectCur;
    }



    public void calculate(Rect rect) {
        init(rect.height());
        rectCur = rect;
        AtomicInteger x = new AtomicInteger(rectCur.left);
        AtomicInteger y = new AtomicInteger(rectCur.top);
        rowStatusPanelPreference.setRect(rectCur);
        messages.forEach(t -> t.setPosition(x.getAndAdd(t.getRect().width()), y.get()));
        messages.forEach(t -> rowStatusPanelPreference.getPosition().add(new PointF(t.getRect().left, t.getRect().top)));


    }



    public void calculate(Map<Integer, List<Float>> positionsX) {
        final List<Float> floats = positionsX.get(rowStatusPanelPreference.getId());
        if (floats != null) {
            for (int i = 0; i < floats.size(); i++) {
                messages.get(i).setPositionX(floats.get(i));
            }
            boolean alignment = rowStatusPanelPreference.alignment(rectCur);

        }



    }

    public abstract void init(int height);
}
