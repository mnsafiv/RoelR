package ru.safonoviv.roelr.Graphics.Display;

import android.graphics.Canvas;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Graphics.Layer.GridDraw;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Map.Value.MapValue;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class DisplayObserver {
    private final GridDraw grid;
    private final FieldSetting setting;

    @Getter
    private final SortedSet<MapValue> mapValues;

    public DisplayObserver() {
        this.grid = Setting.getInstance().getGrid();
        this.setting = Setting.getInstance().getFieldSetting();
        mapValues = new TreeSet<>((o1, o2) -> {
            if (o1.getCoordinate() == o2.getCoordinate())
                return 0;
            return o1.getCoordinate() < o2.getCoordinate() ? -1 : 1;
        });

    }

    public void add(@NotNull Set<MapValue> mapValues) {
        this.mapValues.addAll(mapValues);
    }

    public void remove(@NotNull Set<MapValue> mapValues) {
        this.mapValues.removeAll(mapValues);
    }

    public void draw(Canvas canvas) {
        mapValues.forEach(t -> t.drawGrid(canvas, grid, setting));
        mapValues.forEach(t -> t.drawDecorate(canvas));

    }


}
