package ru.safonoviv.roelr.Object.CharacterExplore;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import lombok.Getter;
import lombok.Setter;
import ru.safonoviv.roelr.GenerateObject.Component.CharacterBitmapComponent;
import ru.safonoviv.roelr.Map.MapExploreImpl;
import ru.safonoviv.roelr.Map.Value.MapValue;
import ru.safonoviv.roelr.Map.Value.MapValueIconDecorate;
import ru.safonoviv.roelr.Object.AbstractActiveObject;
import ru.safonoviv.roelr.Object.Model.GroupExploreModel;
import ru.safonoviv.roelr.Object.ObjectCoordinateProperties;
import ru.safonoviv.roelr.Active.ObjectObserver;
import ru.safonoviv.roelr.SearchWay.ActiveState;
import ru.safonoviv.roelr.SearchWay.MotionVector;
import ru.safonoviv.roelr.SearchWay.MovePositionVector;
import ru.safonoviv.roelr.SearchWay.Node;
import ru.safonoviv.roelr.SearchWay.PathWay;
import ru.safonoviv.roelr.Common.DefaultValue;

import java.util.Objects;

public class GroupExplore extends AbstractActiveObject implements ObjectCoordinateProperties, MapValueIconDecorate, MotionVector {
    private final Thread threadSearchPath;
    @Getter
    private final GroupExploreModel groupExploreModel;
    private final PathWay path;
    private final MovePositionVector movePosition;
    @Getter
    private final BattleMapType mapType;
    private final Bitmap bitmap;
    @Setter
    private ActiveState activeState;
    private final int weight = DefaultValue.EXPLORE_PATH_MAX -1;

    public GroupExplore(MapValue mapValue, boolean playerGroup, GroupExploreModel groupExploreModel, boolean isActiveMotion) {
        super(mapValue.getCenterX(), mapValue.getCenterY(), mapValue);
        this.groupExploreModel = groupExploreModel;
        this.path = new PathWay(getCoordinate(), MapExploreImpl.getInstance());
        path.setDistance(DefaultValue.EXPLORE_PATH_MAX, DefaultValue.EXPLORE_PATH_MAX);
        threadSearchPath = new Thread(path);
        if (isActiveMotion) {
            threadSearchPath.start();
        }

        this.mapType = BattleMapType.smallEasy;
        movePosition = new MovePositionVector(this);
        mapValueOwner.addWeight(weight);


        ObjectObserver.getInstance().getExistObjects().add(this);

        if (playerGroup) {
            if (mapExploreImpl.addNoActiveUnstable(this)) {
                throw new IllegalArgumentException("mapValue already isBusy");
            }
        } else {
            if (mapExploreImpl.addActiveUnstable(this)) {
                throw new IllegalArgumentException("mapValue already isBusy");
            }
        }


        bitmap = CharacterBitmapComponent.getInstance().getBitmapById(groupExploreModel.getIconId());


    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,
                camera.getCenterBitmapToCameraX(bitmap, positionX),
                camera.getCenterBitmapToCameraY(bitmap, positionY),
                null);

    }

    @Override
    public void update() {
        super.update();
        movePosition.update();
    }

    @Override
    public boolean getTouchCollision(int coordinate) {
        /* if moving now - finish move - return */
        if (activeState == ActiveState.move) {
            movePosition.stopNextMove();
            return true;
        }


        if (path.isAvailableCoordinate(coordinate, true)) {
            Node node = path.getNode(coordinate, true);
            moveToNextCoordinate(node);
            return true;
        }

        //other logic


        return false;
    }

    @Override
    public boolean getTouchCollision(double positionX, double positionY) {
        if (path.isAvailableCoordinate(positionX, positionY, true)) {
            Node node = path.getNode(positionX, positionY, true);
            moveToNextCoordinate(node);
            return true;
        }

        return false;
    }

    @Override
    public boolean reset() {
        mapValueOwner.addWeight(-weight);
        return super.reset();
    }

    @Override
    public boolean canMoveToNextCoordinate(int coordinate) {
        return !mapExploreImpl.isBusyNoActive(coordinate) || this.coordinate == coordinate;
    }

    @Override
    public boolean decreaseMovePoint(int coordinate) {
        return true;
    }

    @Override
    public void updatePath() {
        path.setDistance(
                DefaultValue.EXPLORE_PATH_MAX,
                DefaultValue.EXPLORE_PATH_MAX);
        path.setCoordinate(getPositionX(), getPositionY());

    }

    @Override
    public MovePositionVector getMovePosition() {
        return null;
    }

    @Override
    public void moveToNextCoordinate(Node node) {
        movePosition.moveToNextCoordinate(node);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupExplore that = (GroupExplore) o;
        return Objects.equals(groupExploreModel, that.groupExploreModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupExploreModel);
    }

    @Override
    public double getMoveSpeed() {
        return 5;
    }
}
