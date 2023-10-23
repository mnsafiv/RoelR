package ru.safonoviv.roelr.Graphics.Display;


import android.graphics.Canvas;
import android.graphics.Rect;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Common.Setting;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Map.Value.MapExploreModel;
import ru.safonoviv.roelr.Map.Value.MapValue;
import ru.safonoviv.roelr.Map.Value.MapValueExploreImpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DisplayImpl implements Display {
    private static volatile DisplayImpl displayImpl;
    private final PanelControl panelControl;
    @Setter
    private MapExploreModel exploreMap;
    private final DisplayObserver displayObserver;
    private final int areaBorder;
    private final Rect drawArea = new Rect();
    @Getter
    private final Rect displayBounds = new Rect();


    private final Set<Integer> mapValuesKeys = new HashSet<>();


    private DisplayImpl() {
        areaBorder = (int) DefaultValue.DEFAULT_FIELD_SIZE * 2;
        displayObserver = new DisplayObserver();
        panelControl= PanelControl.getInstance();
        drawArea.set(
                -areaBorder,
                -areaBorder,
                Setting.getInstance().getCurrentWidth() + areaBorder,
                Setting.getInstance().getCurrentHeight() + areaBorder);

    }


    public static DisplayImpl getInstance() {
        DisplayImpl singleton = displayImpl;
        if (singleton == null) {
            synchronized (DisplayImpl.class) {
                if (displayImpl == null) {
                    displayImpl = new DisplayImpl();
                }
            }
        }
        return displayImpl;
    }


    @Override
    public void updateBoundsMap() {
        displayBounds.set(getBoundsMap());
        Setting.getInstance().getCamera().setDisplayBounds(new Rect(displayBounds.left,
                displayBounds.top,
                displayBounds.right - Setting.getInstance().getCurrentWidth(),
                displayBounds.bottom - Setting.getInstance().getCurrentHeight()));
    }


    private Rect getBoundsMap() {
        AtomicInteger left = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger top = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger right = new AtomicInteger(Integer.MIN_VALUE);
        AtomicInteger bottom = new AtomicInteger(Integer.MIN_VALUE);


        exploreMap.getMapValues().forEach((key, value) -> {
            int centerX = (int) value.getCenterX();
            if (centerX < left.get()) left.set(centerX);
            if (centerX > right.get()) right.set(centerX);


            int centerY = (int) value.getCenterY();
            if (centerY < top.get()) top.set(centerY);
            if (centerY > bottom.get()) bottom.set(centerY);

        });

        return new Rect((int) (left.get() - DefaultValue.DEFAULT_FIELD_SIZE),
                (int) (top.get() - DefaultValue.DEFAULT_FIELD_SIZE),
                (int) (right.get() + 1.5 * DefaultValue.DEFAULT_FIELD_SIZE),
                (int) (bottom.get() + DefaultValue.DEFAULT_FIELD_SIZE));
    }


    @Override
    public void updateDrawArea() {
        int x = (int) Setting.getInstance().getCamera().getCameraX();
        int y = (int) Setting.getInstance().getCamera().getCameraY();

        drawArea.offset(-x, -y);

        double leftCorner = Math.max(0, drawArea.left);
        double topCorner = Math.max(0, drawArea.top);
        double rightCorner = Math.min(displayBounds.right, drawArea.right);
        double bottomCorner = Math.min(displayBounds.bottom, drawArea.bottom);

        int coordinate1 = Setting.getInstance().getFieldSetting().getCoordinate(leftCorner, topCorner);
        int topAreaX = Setting.getInstance().getFieldSetting().getAreaX(coordinate1);
        int topAreaY = Setting.getInstance().getFieldSetting().getAreaY(coordinate1);

        int coordinate2 = Setting.getInstance().getFieldSetting().getCoordinate(rightCorner, bottomCorner);
        int bottomAreaX = Setting.getInstance().getFieldSetting().getAreaX(coordinate2) + 1;
        int bottomAreaY = Setting.getInstance().getFieldSetting().getAreaY(coordinate2) + 1;

        Set<Integer> newMapValuesKeys = new HashSet<>();

        for (int i = topAreaX; i < bottomAreaX; i++) {
            for (int j = topAreaY; j < bottomAreaY; j++) {
                newMapValuesKeys.add(Setting.getInstance().getFieldSetting().getCoordinate(i, j));
            }
        }

        Set<Integer> add = newMapValuesKeys.stream().filter(t -> !mapValuesKeys.contains(t)).collect(Collectors.toSet());
        Set<Integer> remove = mapValuesKeys.stream().filter(t -> !newMapValuesKeys.contains(t)).collect(Collectors.toSet());
        mapValuesKeys.addAll(add);
        mapValuesKeys.removeAll(remove);

        Set<MapValue> mapValues = new HashSet<>();
        add.forEach(t -> mapValues.add(exploreMap.getMapValues().get(t)));
        mapValues.remove(null);
        displayObserver.add(mapValues);
        mapValues.clear();
        remove.forEach(t -> mapValues.add(exploreMap.getMapValues().get(t)));
        mapValues.remove(null);
        displayObserver.remove(mapValues);

        drawArea.offsetTo(-areaBorder, -areaBorder);


    }


    @Override
    public void draw(Canvas canvas) {
        displayObserver.draw(canvas);
        panelControl.draw(canvas);
    }

    @Override
    public void addToDrawArea(@NotNull MapValueExploreImpl mapValue) {
        mapValuesKeys.add(mapValue.getCoordinate());
        exploreMap.getMapValues().put(mapValue.getCoordinate(), mapValue);
        displayObserver.add(Collections.singleton(mapValue));
    }
}
