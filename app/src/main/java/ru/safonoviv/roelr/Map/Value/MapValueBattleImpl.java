package ru.safonoviv.roelr.Map.Value;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Graphics.Layer.GridDraw;

import java.util.Set;


public class MapValueBattleImpl extends MapValue {


    public MapValueBattleImpl(int coordinate, int weight, String value, Set<String> bonusKeys, Set<MapValueIconDecorate> iconCoordinates) {
        super(coordinate, value, bonusKeys, iconCoordinates);
    }

    @Override
    public void drawGrid(Canvas canvas, @NotNull GridDraw grid, FieldSetting setting) {
        grid.draw(canvas, coordinate, prototypeGrid.getBackGroundBitmap(value), setting);

        Paint paint =new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle((float) camera.getDistanceToCameraX(centerX), (float) camera.getDistanceToCameraY(centerY),10,paint);
        canvas.drawText(String.valueOf(coordinate),
                (float) camera.getDistanceToCameraX(centerX-10),
                (float) camera.getDistanceToCameraY(centerY-20),paint);

    }

    @Override
    public void drawDecorate(Canvas canvas) {
        iconCoordinates.forEach(t -> t.draw(canvas));

    }
}
