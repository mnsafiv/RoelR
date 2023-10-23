package ru.safonoviv.roelr.Object;

import lombok.Getter;
import ru.safonoviv.roelr.Map.MapExploreImpl;
import ru.safonoviv.roelr.Map.Value.MapValue;
import ru.safonoviv.roelr.Map.Value.MapValueIconDecorate;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;


public abstract class AbstractActiveObject extends ObjectProperties implements ActiveObject, MapValueIconDecorate {
    protected final MapExploreImpl mapExploreImpl;
    @Getter
    protected MapValue mapValueOwner;
    @Getter
    protected Map<Integer, AbstractActiveObject> unstableOwner;

    public AbstractActiveObject(double positionX, double positionY, MapValue mapValueOwner) {
        super(positionX, positionY);
        this.mapValueOwner = mapValueOwner;

        mapExploreImpl = MapExploreImpl.getInstance();
        mapValueOwner.addDecorate(this);
    }

    @Override
    public void update() {
        System.out.println();
        if (motionVectorX != 0 || motionVectorY != 0) {
            positionX = positionX + motionVectorX;
            positionY = positionY + motionVectorY;

        }

        int curCoordinate = setting.getCoordinate((float) positionX, (float) positionY);

        if (curCoordinate != coordinate) {
            switchCoordinate(curCoordinate);
        }


    }

    private void checkCorrectCoordinate() {
        if (!mapValueOwner.getIconCoordinates().contains(this)) {
            throw new IllegalArgumentException("no contains target decorate");
        }

        if (unstableOwner == null) {
            throw new IllegalArgumentException("no contains unstable owner");
        }


        if (unstableOwner.get(coordinate) != this) {
            Optional<Map.Entry<Integer, AbstractActiveObject>> first = unstableOwner.entrySet().stream().filter(entry -> entry.getValue().equals(this)).findFirst();
            if (first.isPresent()) {
                if (!mapExploreImpl.releaseAndSwitchUnstable(first.get().getKey(), this)) {
                    throw new IllegalArgumentException("cant release and set twice");
                }


            }
        }


    }

    private void switchCoordinate(int curCoordinate) {
        if (!removeDecorate()) {
            if (!Double.isNaN(positionX) && !Double.isNaN(positionY)) {
                return;
//                throw new IllegalArgumentException("no contains target decorate");
            } else return;

        }

        int prevCoordinate = coordinate;
        coordinate = curCoordinate;
        MapValue mapValue = mapExploreImpl.getMapExploreModel().getMapValues().get(coordinate);

        if (mapValue == null) {
            throw new IllegalArgumentException("no contains target mapValue");
        }

        if (!switchMapValue(mapValue, prevCoordinate)) {
            throw new IllegalArgumentException("cant switch unstable");
        }

        checkCorrectCoordinate();
    }

    private boolean switchMapValue(MapValue mapValue, int prevCoordinate) {
        this.mapValueOwner = mapValue;
        this.mapValueOwner.addDecorate(this);
        if (!mapExploreImpl.switchUnstable(prevCoordinate, this)) {
            Optional<Map.Entry<Integer, AbstractActiveObject>> first = unstableOwner.entrySet().stream().filter(entry -> entry.getValue() == this).findFirst();
            if (!first.isPresent()) {
                throw new IllegalArgumentException("no found old coordinate");
            }

            return false;

        }
        return true;
    }

    private boolean removeDecorate() {
        if (!mapValueOwner.remove(this)) {
            return mapValueOwner.calculateDecorateAndRemove(this);
        }
        return true;
    }

    @Override
    public double getMapValuePositionY() {
        return mapValueOwner.getCenterY() - positionY;
    }

    @Override
    public boolean getTouchCollision(int coordinate) {
        return mapExploreImpl.contains(coordinate);
    }

    @Override
    public void setUnstable(Map<Integer, AbstractActiveObject> exploreObjectUnstable) {
        this.unstableOwner = exploreObjectUnstable;
    }

    @Override
    public boolean getAction() {
        return false;
    }

    @Override
    public boolean getAction(AbstractActiveObject object) {
        return object.getAction();
    }

    @Override
    public boolean getTouchCollision(double positionX, double positionY) {
        int coordinate = setting.getCoordinate(positionX, positionY);
        return mapExploreImpl.contains(coordinate);
    }

    public boolean reset() {
        return mapExploreImpl.removeUnstable(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractActiveObject that = (AbstractActiveObject) o;
        return Objects.equals(mapExploreImpl, that.mapExploreImpl) && Objects.equals(mapValueOwner, that.mapValueOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mapExploreImpl, mapValueOwner);
    }

    public void removeFromMapValue() {
        if (!reset()) {
            System.out.println();
        }
        if (!mapValueOwner.remove(this)) {
            if (mapValueOwner.calculateDecorateAndRemove(this)) {
                if (unstableOwner.get(coordinate) != null) {
                    throw new IllegalArgumentException("Coordinate error" + this);
                }
            }
        }


    }
}
