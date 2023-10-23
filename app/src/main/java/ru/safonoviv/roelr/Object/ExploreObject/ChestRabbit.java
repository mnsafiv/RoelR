package ru.safonoviv.roelr.Object.ExploreObject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.GenerateObject.Component.PrototypeDecor;
import ru.safonoviv.roelr.Graphics.Panel.Model.RabbitChestReward;
import ru.safonoviv.roelr.Map.Value.MapValue;
import ru.safonoviv.roelr.Object.AbstractActiveObject;
import ru.safonoviv.roelr.Object.Model.ModelSequence;

import java.util.Objects;

public class ChestRabbit extends AbstractActiveObject {
    private final Bitmap bitmap;
    private final long sequence;
    private RabbitChestReward rabbitChestReward;
    private final int weight = DefaultValue.EXPLORE_PATH_MAX -1;

    public ChestRabbit(MapValue mapValueOwner) {
        super(mapValueOwner.getCenterX(), mapValueOwner.getCenterY(), mapValueOwner);
        this.bitmap = PrototypeDecor.getInstance().getBackGroundBitmap("treasure_rabbit_1", 1.0);
        this.sequence = ModelSequence.sequence.incrementAndGet();
        mapValueOwner.addWeight(weight);
        mapExploreImpl.addActiveUnstable(this);


    }

    @Override
    public boolean getTouchCollision(int coordinate) {
        return this.coordinate == coordinate;
    }

    @Override
    public boolean getAction() {
        //open interface
        rabbitChestReward = new RabbitChestReward(this);
        camera.getPanelControl().addActivePanel(rabbitChestReward);
        return false;
    }

    @Override
    public boolean reset() {
        mapValueOwner.addWeight(-weight);
        return super.reset();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChestRabbit that = (ChestRabbit) o;
        return sequence == that.sequence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sequence);
    }

    @Override
    public boolean getTouchCollision(double positionX, double positionY) {
        return false;
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,
                camera.getCenterBitmapToCameraX(bitmap, positionX),
                camera.getCenterBitmapToCameraY(bitmap, positionY),
                null);


    }
}
