package ru.safonoviv.roelr.Graphics.Panel.Model;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.content.ContextCompat;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Graphics.Panel.PanelInt;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ActionOnClick;
import ru.safonoviv.roelr.Graphics.Panel.Row.RabbitChestRewardBody;
import ru.safonoviv.roelr.Graphics.Panel.Row.RabbitChestRewardHead;
import ru.safonoviv.roelr.Graphics.Panel.Row.RowStatusPanel;
import ru.safonoviv.roelr.Graphics.Panel.Status.StatusModelRabbitChestReward;
import ru.safonoviv.roelr.Graphics.Panel.StatusModelItem;
import ru.safonoviv.roelr.MainActivity;
import ru.safonoviv.roelr.Object.ExploreObject.ChestRabbit;
import ru.safonoviv.roelr.R;

public class RabbitChestReward implements PanelInt {
    private final StatusModelItem statusModelRabbitChestReward;
    private final Paint paintBorderInfo;
    private final Paint paintStatusInfo;
    private final Context context;
    private final ChestRabbit chestRabbit;


    public RabbitChestReward(ChestRabbit chestRabbit) {
        context = MainActivity.getContext();
        this.chestRabbit = chestRabbit;

        paintBorderInfo = new Paint();
        paintBorderInfo.setColor(ContextCompat.getColor(context, R.color.ColorBorderStatus));
        paintStatusInfo = new Paint();
        paintStatusInfo.setTextSize((float) Setting.getInstance().getCurrentWidth() / 100 * 2);
        paintStatusInfo.setColor(ContextCompat.getColor(context, R.color.ColorTextStatus));

        statusModelRabbitChestReward = new StatusModelRabbitChestReward(context, DefaultValue.rabbitChest.ROW_NUMBER_SHOW);

        updateStatus();

    }

    private void updateStatus() {

        statusModelRabbitChestReward.reset();

        int screenX = Setting.getInstance().getCurrentWidth();
        int screenY = Setting.getInstance().getCurrentHeight();

        int rectWidth = (int) (screenX * DefaultValue.rabbitChest.ROW_MULTIPLIER_SIZE_WIDTH);
        int rectHeight = (int) (screenY * DefaultValue.rabbitChest.ROW_MULTIPLIER_SIZE_HEIGHT);

        int groupIdHead = 1;
        int groupIdEnd = 2;
        int groupIdRow = 3;


        //init head
        //init end
        Paint paintBorderHeadEnd = new Paint();
        paintBorderHeadEnd.setColor(ContextCompat.getColor(context, R.color.ColorBorderDefault));

        Paint paintTextHeadEnd = new Paint();
        paintBorderHeadEnd.setColor(ContextCompat.getColor(context, R.color.ColorTextOnBorderDefault));

        RowStatusPanel rowStatusPanelHead = new RabbitChestRewardHead(context, statusModelRabbitChestReward, paintBorderHeadEnd, paintTextHeadEnd, groupIdHead);
        statusModelRabbitChestReward.addHead(rowStatusPanelHead);

        RowStatusPanel rowStatusPanelEnd = new RabbitChestRewardHead(context, statusModelRabbitChestReward, paintBorderHeadEnd, paintTextHeadEnd, groupIdEnd);
        statusModelRabbitChestReward.addEnd(rowStatusPanelEnd);

        //init detail
        statusModelRabbitChestReward.addRow(new RabbitChestRewardBody(context, paintBorderInfo, paintStatusInfo, groupIdRow,chestRabbit));

        //left-bottom
        Rect rectStatus = new Rect(0, 0, rectWidth, rectHeight);
        int margin = 10;
        rectStatus.offsetTo((screenX - rectWidth) / 2, (screenY - rectHeight) / 2 - margin * 2);
        statusModelRabbitChestReward.setBorder(rectStatus, margin, DefaultValue.rabbitChest.ROW_SIZE_BODY);


    }

    public void draw(Canvas canvas) {
        statusModelRabbitChestReward.draw(canvas);
    }

    @Override
    public ActionOnClick getActiveCollision(int positionX, int positionY) {
        return statusModelRabbitChestReward.getCollision(positionX, positionY);
    }

    @Override
    public boolean getCollision(int positionX, int positionY) {
//        return statusModelRabbitChestReward.getRectBorder().contains(positionX, positionY);

        //waiting close panel
        return true;
    }

    @Override
    public void shift(int distanceX, int distanceY) {

    }

    @Override
    public boolean getAction() {
        return false;
    }
}
