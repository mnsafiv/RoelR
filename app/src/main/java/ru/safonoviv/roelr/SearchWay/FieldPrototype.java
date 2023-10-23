package ru.safonoviv.roelr.SearchWay;

import ru.safonoviv.roelr.Map.Value.MapExploreModel;
import ru.safonoviv.roelr.Object.AbstractActiveObject;

import java.util.List;

public interface FieldPrototype {
    MapExploreModel getMapExploreModel();
    List<VectorMove> getVectors(int number);

    boolean addNoActiveUnstable(AbstractActiveObject object);
    boolean addActiveUnstable(AbstractActiveObject object);

    boolean isBusyActive(int coordinate);

    boolean removeUnstable(AbstractActiveObject object);



    boolean switchUnstable(int coordinatePrev, AbstractActiveObject object);



    int getSequenceCounter();

    boolean releaseAndSwitchUnstable(int coordinatePrev, AbstractActiveObject object);

    boolean isBusyNoActive(int coordinate);





}
