package ru.safonoviv.roelr.Map.Value;


import android.graphics.Canvas;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.GenerateObject.Bonus.BonusType;
import ru.safonoviv.roelr.GenerateObject.Component.PrototypeGrid;
import ru.safonoviv.roelr.Object.AbstractActiveObject;
import ru.safonoviv.roelr.Graphics.Display.Camera;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Graphics.Layer.GridDraw;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public abstract class MapValue {
    protected final PrototypeGrid prototypeGrid;
    private Long id;
    protected double centerX;
    protected double centerY;
    protected int coordinate;
    protected String value;
    protected int weight;
    protected CoordinateProperties coordinateProperties;

    protected final Set<MapValueIconDecorate> iconCoordinates;
    private Map<BonusType, Double> bonus;
    protected final Camera camera;
    @JsonIgnore
    private final Comparator<MapValueIconDecorate> sort;
    @JsonIgnore
    private byte alpha = -1;
    private int count;


    public MapValue(int coordinate, String value, Set<String> bonusKeys, Set<MapValueIconDecorate> iconCoordinates) {
        sort = (o1, o2) -> {
            if (o1.getMapValuePositionY() == o2.getMapValuePositionY())
                return 0;
            return o1.getMapValuePositionY() < o2.getMapValuePositionY() ? -1 : 1;
        };
        this.iconCoordinates = new TreeSet<>(sort);

        this.coordinate = coordinate;
        this.value = value;
        this.weight = (int) PrototypeGrid.getInstance().getBitmapComponent(value).getDistance();
        this.centerX = Setting.getInstance().getFieldSetting().getCenterAreaPositionX(coordinate);
        this.centerY = Setting.getInstance().getFieldSetting().getCenterAreaPositionY(coordinate);

        bonusKeys.forEach(bonus -> {
//            Map<BonusType, Double> bonusItems = BonusPrototype.getInstance().getBonus(bonus).getBonusItems();
        });


        this.bonus = new HashMap<>();
        this.iconCoordinates.addAll(iconCoordinates);
        this.prototypeGrid = PrototypeGrid.getInstance();


        camera = Setting.getInstance().getCamera();

    }


    public abstract void drawGrid(Canvas canvas, @NotNull GridDraw grid, FieldSetting setting);

    public abstract void drawDecorate(Canvas canvas);

    public void reset() {
        alpha = -1;
    }

    public void addDecorate(Set<MapValueIconDecorate> icons) {
        synchronized (iconCoordinates) {
            iconCoordinates.addAll(icons);
        }


    }

    public boolean addDecorate(MapValueIconDecorate icon) {
        boolean result;
        synchronized (iconCoordinates) {
            result = iconCoordinates.add(icon);
        }
        return result;

    }

    public void set(MapValue mapValue) {
        reset();
        iconCoordinates.clear();
        bonus.clear();
        value = mapValue.getValue();
        weight = mapValue.getWeight();

    }

    private void calculateDecorate() {
        //realize sort
        List<MapValueIconDecorate> collect = iconCoordinates.stream().sorted(sort).collect(Collectors.toList());
        synchronized (iconCoordinates) {
            iconCoordinates.clear();
            iconCoordinates.addAll(collect);
        }


    }

    public boolean remove(AbstractActiveObject abstractActiveObject) {
        boolean result;
        synchronized (iconCoordinates) {
            result = iconCoordinates.remove(abstractActiveObject);

        }
        return result;
    }

    public void addWeight(int weight) {
        this.weight += weight;
        controlWeight();
    }

    private void controlWeight() {
        weight = Math.max(1, weight);
    }

    public boolean calculateDecorateAndRemove(AbstractActiveObject abstractActiveObject) {
        boolean result;
        synchronized (iconCoordinates) {
            calculateDecorate();
            result= remove(abstractActiveObject);
        }
        return result;
    }
}
