package ru.safonoviv.roelr.SearchWay;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Common.Utils;
import ru.safonoviv.roelr.Map.Value.CoordinateProperties;
import ru.safonoviv.roelr.Map.Value.CoordinatePropertiesImpl;

import java.util.LinkedList;
import java.util.Queue;

public class MovePositionVector {

    private final MotionVector obj;
    private double nextX;
    private double nextY;
    private boolean isMovingAtNextCoordinate = false;
    private boolean movePointWasDecrease = false;

    private CoordinateProperties prevCoordinate;
    private CoordinateProperties nextCoordinate;
    @Getter
    private final Queue<CoordinateProperties> path;
    @Getter
    private final Queue<CoordinateProperties> pathReverse;

    private ObjectState state;

    private final double moveSpeed;
    private double distanceToNextPosition;


    public MovePositionVector(MotionVector obj) {
        this.obj = obj;
        path = new LinkedList<>();
        pathReverse = new LinkedList<>();
        state = ObjectState.waiting;
        moveSpeed = obj.getMoveSpeed();
    }


    public void moveToNextCoordinate(@NotNull Node node) {
        synchronized (pathReverse){
            pathReverse.clear();
        }
        synchronized (path) {
            node.getShortestWay().forEach(t -> path.add(new CoordinatePropertiesImpl(t.getValue().getCoordinate())));
            path.add(new CoordinatePropertiesImpl(node.getValue().getCoordinate()));

            nextCoordinate = path.poll();
            prevCoordinate = nextCoordinate;
            pathReverse.add(prevCoordinate);
            state = ObjectState.prepareMove;
        }

    }

    private void setNextVectorMotion() {
        CoordinateProperties nCoordinate;
        synchronized (path) {
            nCoordinate = path.poll();
        }

        if (nCoordinate == null || !obj.canMoveToNextCoordinate(nCoordinate.getCoordinate())) {
            stop();
            return;
        }
        nextX = nCoordinate.getActualCenterX();
        nextY = nCoordinate.getActualCenterY();


        this.prevCoordinate = nextCoordinate;
        this.nextCoordinate = nCoordinate;

        double distanceToX = obj.getPositionX() - nextX;
        double distanceToY = obj.getPositionY() - nextY;

        double multiplier = Math.abs(distanceToX / distanceToY);
        double motionVectorX;
        double motionVectorY;

        if (Double.isInfinite(multiplier)) {
            motionVectorX = moveSpeed;
            motionVectorY = 0;
        } else {
            motionVectorX = moveSpeed * multiplier;
            motionVectorY = moveSpeed;
        }


        multiplier = Utils.getDistanceBetweenTwoPoints(0, 0,
                motionVectorX, motionVectorY) / moveSpeed;


        if (distanceToX < 0)
            obj.setMotionVectorX(motionVectorX / multiplier);
        else
            obj.setMotionVectorX(-motionVectorX / multiplier);


        if (distanceToY < 0)
            obj.setMotionVectorY(motionVectorY / multiplier);
        else
            obj.setMotionVectorY(-motionVectorY / multiplier);


        distanceToNextPosition = Utils.getDistanceBetweenTwoPoints(obj.getPositionX(), obj.getPositionY(), nextX, nextY);

        obj.setActiveState(ActiveState.move);
        state = ObjectState.moving;
        pathReverse.add(nCoordinate);
        isMovingAtNextCoordinate = true;
        movePointWasDecrease = false;

    }


    private void controlMove() {
        double distance = Utils.getDistanceBetweenTwoPoints(obj.getPositionX(), obj.getPositionY(), nextX, nextY);
        int curCoordinate = obj.getCoordinate();


        if (distanceToNextPosition < moveSpeed) {
            finishMove();
            return;
        }


        /* add control mapValue move, if it will realize */
        if (distanceToNextPosition < distance) {
            setCenterTargetCoordinate(new CoordinatePropertiesImpl(curCoordinate));
            return;
        }




        /* add logic no decrease movePoint */
        if (prevCoordinate.getCoordinate() == curCoordinate && !isMovingAtNextCoordinate) {
            setCenterTargetCoordinate(new CoordinatePropertiesImpl(curCoordinate));
            return;

        }

        distanceToNextPosition = distance;


        /* when reach targetCoordinate to decrease movePoint or back */
        if (nextCoordinate.getCoordinate() == curCoordinate) {
            if (!isMovingAtNextCoordinate) {
                if (obj.decreaseMovePoint(nextCoordinate.getCoordinate()) && !movePointWasDecrease) {
                    reachNextCoordinate();
                } else setCenterTargetCoordinate(prevCoordinate);
                return;
            }

            return;
        }

        /* drop way and return in center */
        if (curCoordinate != prevCoordinate.getCoordinate() && curCoordinate != nextCoordinate.getCoordinate()) {
            setCenterTargetCoordinate(new CoordinatePropertiesImpl(curCoordinate));
        }


    }

    private void setCenterTargetCoordinate(CoordinateProperties curCoordinate) {
        synchronized (path) {
            path.clear();
            path.add(curCoordinate);
        }
        setNextVectorMotion();
        isMovingAtNextCoordinate = true;
    }

    private void reachNextCoordinate() {
        prevCoordinate = nextCoordinate;
        distanceToNextPosition = 0;
        obj.updatePath();
        isMovingAtNextCoordinate = false;
        movePointWasDecrease = true;
    }

    private void stop() {
        obj.setMotionVectorX(0);
        obj.setMotionVectorY(0);
        obj.setActiveState(ActiveState.wait);
        state = ObjectState.waiting;
        isMovingAtNextCoordinate = false;
    }


    private void finishMove() {
        obj.setPositionX(nextX);
        obj.setPositionY(nextY);
        obj.updatePath();
        state = ObjectState.prepareMove;
    }


    public void update() {

        switch (state) {
            case waiting:
                break;

            case moving:
                controlMove();
                break;

            case prepareMove:
                setNextVectorMotion();
                break;


        }


    }

    public void stopNextMove() {
        synchronized (path) {
            setCenterTargetCoordinate(new CoordinatePropertiesImpl(obj.getCoordinate()));
        }

    }
}
