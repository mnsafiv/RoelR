package ru.safonoviv.roelr.Map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import lombok.Getter;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Graphics.Layer.GridDraw;
import ru.safonoviv.roelr.Map.Value.MapExploreModel;
import ru.safonoviv.roelr.Object.AbstractActiveObject;
import ru.safonoviv.roelr.SearchWay.FieldPrototype;
import ru.safonoviv.roelr.SearchWay.VectorAdjacent;
import ru.safonoviv.roelr.SearchWay.VectorMove;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapExploreImpl implements FieldPrototype {
    private static volatile MapExploreImpl mapExplore;
    @Getter
    private final MapExploreModel mapExploreModel;
    private List<List<VectorMove>> vectors;
    @Getter
    private final Map<Integer, AbstractActiveObject> noActiveUnstable = new HashMap<>();
    @Getter
    private final Map<Integer, AbstractActiveObject> activeUnstable = new HashMap<>();

    private final Set<Integer> busyCoordinate = new HashSet<>();

    @Getter
    private int sequenceCounter = 0;

    private MapExploreImpl() {
        vectors = new ArrayList<>();
        switch (DefaultValue.GRID_TYPE) {
            case HexVertical:
                vectors = VectorAdjacent.getVerticalVectorMove();
                break;
            case HexHorizontal:
                vectors = VectorAdjacent.getHorizontalVectorMove();
                break;
        }
        mapExploreModel = MapExploreModel.getInstance();

    }

    public static MapExploreImpl getInstance() {
        MapExploreImpl singleton = mapExplore;
        if (singleton == null) {
            synchronized (MapExploreImpl.class) {
                if (mapExplore == null) {
                    mapExplore = new MapExploreImpl();
                }
            }
        }
        return mapExplore;
    }

    @Override
    public List<VectorMove> getVectors(int number) {
        return vectors.get(number);
    }

    @Override
    public boolean addNoActiveUnstable(AbstractActiveObject object) {
        boolean result = false;
        if (addBusyCoordinate(object.getCoordinate())) {
            synchronized (noActiveUnstable) {
                AbstractActiveObject put = noActiveUnstable.put(object.getCoordinate(), object);
                if (put == null) {
                    object.setUnstable(noActiveUnstable);
                } else {
                    throw new IllegalArgumentException("mapValue can have one or lesser active unstable");
                }
            }
        }
        nextSequence();
        return result;

    }

    @Override
    public boolean addActiveUnstable(AbstractActiveObject object) {
        boolean result = false;
        if (addBusyCoordinate(object.getCoordinate())) {
            synchronized (activeUnstable) {
                AbstractActiveObject put = activeUnstable.put(object.getCoordinate(), object);
                if (put == null) {
                    object.setUnstable(activeUnstable);
                } else {
                    throw new IllegalArgumentException("mapValue can have one or lesser active unstable");
                }
            }
        }
        nextSequence();
        return result;

    }

    private boolean addBusyCoordinate(int coordinate) {
        return busyCoordinate.add(coordinate);
    }

    private void nextSequence() {
        sequenceCounter++;
    }

    @Override
    public boolean switchUnstable(int coordinatePrev, AbstractActiveObject object) {
        AbstractActiveObject result = null;
        if (addBusyCoordinate(object.getCoordinate())) {
            synchronized (noActiveUnstable) {
                AbstractActiveObject objectPrevCoordinate = noActiveUnstable.remove(coordinatePrev);
                if (objectPrevCoordinate != object) {
                    updateObjectUnstable();
                }
                result = noActiveUnstable.put(object.getCoordinate(), object);
                nextSequence();
            }
        } else {
            if (activeUnstable.containsKey(object.getCoordinate())) {
                object.getAction(Objects.requireNonNull(activeUnstable.get(object.getCoordinate())));
                synchronized (noActiveUnstable) {
                    AbstractActiveObject objectPrevCoordinate = noActiveUnstable.remove(coordinatePrev);
                    if (objectPrevCoordinate != object) {
                        updateObjectUnstable();
                    }
                    result = noActiveUnstable.put(object.getCoordinate(), object);
                    nextSequence();
                }
            }
        }
        return result == null;
    }

    @Override
    public boolean releaseAndSwitchUnstable(int coordinatePrev, AbstractActiveObject object) {
        Set<Integer> busy = Stream.of(activeUnstable, noActiveUnstable).flatMap(t -> t.entrySet().stream()).map(Map.Entry::getKey).collect(Collectors.toSet());
        List<Integer> collect = busyCoordinate.stream().filter(t -> !busy.contains(t)).collect(Collectors.toList());
        collect.forEach(busyCoordinate::remove);
        return switchUnstable(coordinatePrev,object);
    }

    private void updateObjectUnstable() {
        Set<AbstractActiveObject> needUpdate = new HashSet<>();
        Set<AbstractActiveObject> duplicate = new HashSet<>();
        Set<Integer> noActualKey = new HashSet<>();
        noActiveUnstable.forEach((key, value) -> {
            if (duplicate.add(value)) {
                noActualKey.add(key);
                needUpdate.add(value);
            }
            if (key != value.getCoordinate()) {
                needUpdate.add(value);
                noActualKey.add(key);
            }
        });
        noActualKey.forEach(noActiveUnstable::remove);
        needUpdate.forEach(t -> noActiveUnstable.put(t.getCoordinate(), t));

    }

    public void draw(Canvas canvas) {
        GridDraw grid = Setting.getInstance().getGrid();
        FieldSetting fieldSetting = Setting.getInstance().getFieldSetting();

        Paint paint = new Paint();

        paint.setColor(Color.CYAN);
        paint.setAlpha(90);
        noActiveUnstable.forEach((key, value) -> grid.draw(canvas, key, paint, fieldSetting));

        paint.setColor(Color.BLUE);
        paint.setAlpha(90);
        activeUnstable.forEach((key, value) -> grid.draw(canvas, key, paint, fieldSetting));

    }


    @Override
    public boolean isBusyNoActive(int coordinate) {
        return noActiveUnstable.containsKey(coordinate);
    }

    @Override
    public boolean isBusyActive(int coordinate) {
        return activeUnstable.containsKey(coordinate);
    }


    private boolean releaseBusyCoordinate(int coordinate) {
        return busyCoordinate.remove(coordinate);
    }


    @Override
    public boolean removeUnstable(AbstractActiveObject object) {
        if (releaseBusyCoordinate(object.getCoordinate())) {
            return object.getUnstableOwner().remove(object.getCoordinate(), object) && busyCoordinate.remove(object.getCoordinate());
        }
        return false;
    }


    public boolean contains(int coordinate) {
        return busyCoordinate.contains(coordinate);
    }
}
