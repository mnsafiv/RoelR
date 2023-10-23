package ru.safonoviv.roelr.Graphics.Panel.Row;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Graphics.Panel.StatusModelItem;

public class RabbitChestRewardEnd extends RowStatusPanel {
    private StatusModelItem statusModelItemRabbit;
    public RabbitChestRewardEnd(Context context, StatusModelItem statusModelItem, Paint paintBorder, Paint paintText, int groupId) {
        super(context, groupId, paintBorder, paintText, DefaultValue.rabbitChest.ROW_SIZE_END);
        this.statusModelItemRabbit = statusModelItem;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rectCur, paintBorder);
        messages.forEach(t -> t.draw(canvas));


    }

    @Override
    public void shift(int distanceX, int distanceY) {
        rectCur.offsetTo(rectCur.left, rectCur.top + distanceY);
        messages.forEach(t -> t.shiftY(distanceY));

    }

    @Override
    public void init(int height) {
        messages.clear();

    }
}
