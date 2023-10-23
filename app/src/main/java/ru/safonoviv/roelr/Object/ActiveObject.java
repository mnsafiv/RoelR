package ru.safonoviv.roelr.Object;


import java.util.Map;

public interface ActiveObject {
    boolean getTouchCollision(int coordinate);
    boolean getAction();
    boolean getAction(AbstractActiveObject object);

    boolean getTouchCollision(double positionX, double positionY);

    void setUnstable(Map<Integer, AbstractActiveObject> exploreObjectUnstable);

}
