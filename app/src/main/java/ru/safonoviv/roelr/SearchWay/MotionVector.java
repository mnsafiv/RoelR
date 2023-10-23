package ru.safonoviv.roelr.SearchWay;


public interface MotionVector {
    double getPositionX();

    double getPositionY();

    int getCoordinate();

    void setPositionX(double positionX);

    void setPositionY(double positionY);

    void setMotionVectorX(double motionVectorX);

    void setMotionVectorY(double motionVectorY);

    boolean canMoveToNextCoordinate(int coordinate);

    boolean decreaseMovePoint(int coordinate);

    void updatePath();

    void setActiveState(ActiveState activeState);

    MovePositionVector getMovePosition();

    void moveToNextCoordinate(Node node);

    double getMoveSpeed();
}
