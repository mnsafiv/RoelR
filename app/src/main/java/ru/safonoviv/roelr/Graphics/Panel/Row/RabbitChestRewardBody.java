package ru.safonoviv.roelr.Graphics.Panel.Row;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ActionOnClickImpl;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ButtonDeclineReward;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ButtonTakeReward;
import ru.safonoviv.roelr.Graphics.Panel.StatusModelItem;
import ru.safonoviv.roelr.Object.ExploreObject.ChestRabbit;
import ru.safonoviv.roelr.R;

public class RabbitChestRewardBody extends RowStatusPanel {
    private final ChestRabbit chestRabbit;
    private StatusModelItem statusModelItemRabbit;

    public RabbitChestRewardBody(Context context, Paint paintBorderInfo, Paint paintStatusInfo, int groupId, ChestRabbit chestRabbit) {
        super(context, groupId, paintBorderInfo, paintStatusInfo, DefaultValue.rabbitChest.ROW_SIZE_BODY);
        this.chestRabbit = chestRabbit;

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
        ActionOnClickImpl buttonOk = new ButtonTakeReward(context, R.drawable.icon_ok_no, 1, 2, height, chestRabbit);
        ActionOnClickImpl buttonNo = new ButtonDeclineReward(context, R.drawable.icon_ok_no, 2, 2, height, chestRabbit);
        messages.add(buttonOk);
        messages.add(buttonNo);

    }
}
