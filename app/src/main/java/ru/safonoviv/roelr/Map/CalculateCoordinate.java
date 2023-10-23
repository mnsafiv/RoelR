package ru.safonoviv.roelr.Map;


import ru.safonoviv.roelr.Active.DisplayControl;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Object.ExploreObject.ChestRabbit;
import ru.safonoviv.roelr.Object.Model.GroupExploreModel;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Map.Value.MapExploreModel;
import ru.safonoviv.roelr.Map.Value.MapValue;
import ru.safonoviv.roelr.Object.CharacterExplore.GroupExplore;
import ru.safonoviv.roelr.Active.PlayerGroup;

import java.util.*;

public class CalculateCoordinate {
    private static volatile CalculateCoordinate calculateCoordinate;
    private final MapExploreImpl mapExplore = MapExploreImpl.getInstance();

    private CalculateCoordinate() {
    }

    public static CalculateCoordinate getInstance() {
        CalculateCoordinate singleton = calculateCoordinate;
        if (singleton == null) {
            synchronized (CalculateCoordinate.class) {
                if(calculateCoordinate==null)
                    calculateCoordinate  = new CalculateCoordinate();

            }
        }
        return calculateCoordinate;
    }

    public void calculateExplore(GroupExploreModel playerGroupExploreModel, Set<GroupExploreModel> opponentGroupsModel, MapExploreModel exploreMap) {

        FieldSetting fieldSetting = Setting.getInstance().getFieldSetting();

        List<Integer> availablePositions = new LinkedList<>();

        exploreMap.updateInfo();
        int capacityX = exploreMap.getCapacityX();
        int capacityY = exploreMap.getCapacityY();

        for (int i = 0; i < capacityX; i++) {
            for (int j = 0; j < capacityY; j++) {
                availablePositions.add(fieldSetting.getCoordinate(i, j));
            }
        }

        Collections.shuffle(availablePositions);

        MapValue mapValue = searchCoordinate(exploreMap, availablePositions);
        GroupExplore groupExplore = new GroupExplore(mapValue, true, playerGroupExploreModel, true);
        PlayerGroup.getInstance().setPlayGroupExplore(groupExplore);
        DisplayControl.getInstance().setActive(groupExplore);

        opponentGroupsModel.forEach(t ->
                new GroupExplore(Objects.requireNonNull(searchCoordinate(exploreMap, availablePositions)), false, t, false));


    }

    private MapValue searchCoordinate(MapExploreModel exploreMap, List<Integer> available) {
        while (!available.isEmpty()) {
            Integer nCoordinate = available.remove(0);
            MapValue mapValue = exploreMap.getMapValues().get(nCoordinate);
            if (mapValue != null) {
                if (mapValue.getWeight() < 3 && !mapExplore.contains(nCoordinate)) {
                    return mapValue;
                }
            }

        }
        return null;
    }

    public void addTreasureRabbit(int count, MapExploreModel exploreMap) {

        FieldSetting fieldSetting = Setting.getInstance().getFieldSetting();

        List<Integer> availablePositions = new LinkedList<>();

        exploreMap.updateInfo();
        int capacityX = exploreMap.getCapacityX();
        int capacityY = exploreMap.getCapacityY();

        for (int i = 0; i < capacityX; i++) {
            for (int j = 0; j < capacityY; j++) {
                availablePositions.add(fieldSetting.getCoordinate(i, j));
            }
        }

        Collections.shuffle(availablePositions);

        for (int i = 0; i < count; i++) {
            MapValue mapValue = searchCoordinate(exploreMap, availablePositions);
            new ChestRabbit(mapValue);
        }


    }
}
