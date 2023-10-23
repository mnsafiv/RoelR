package ru.safonoviv.roelr.GenerateObject.Component;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Object.Model.GroupExploreModel;
import ru.safonoviv.roelr.GenerateObject.Model.GroupTypeDifficult;
import ru.safonoviv.roelr.GenerateObject.Model.GroupTypeEnvironment;
import ru.safonoviv.roelr.GenerateObject.Model.GroupTypeGrade;
import ru.safonoviv.roelr.Object.CharacterExplore.BattleMapType;
import ru.safonoviv.roelr.Graphics.Enum.MapType;
import ru.safonoviv.roelr.Map.Value.*;
import ru.safonoviv.roelr.Object.Model.ModelDataExplore;

import java.util.*;

public class MapComponent {
    private static volatile MapComponent mapComponent;
    private final Random random = new Random();

    private MapComponent() {
    }

    public static MapComponent getInstance() {
        MapComponent singleton = mapComponent;
        if (singleton == null) {
            synchronized (MapComponent.class) {
                if (mapComponent == null) {
                    mapComponent = new MapComponent();
                }
            }
        }
        return mapComponent;
    }



    public Map<Integer, MapValue> getExploreMap(MapType mapType, Setting setting) {
        int sizeAreaX = 10;
        int sizeAreaY = 10;
        Map<Integer, MapValue> map = new HashMap<>();
        Random random = new Random();

        switch (mapType) {
            case Simply:

                for (int i = 0; i < sizeAreaX; i++) {
                    for (int j = 0; j < sizeAreaY; j++) {
                        int key = i + j * DefaultValue.FIELD_CAPACITY;

                        final int value = random.nextInt(5) - 1;

                        Set<MapValueIconDecorate> icons = new HashSet<>();


                        final BitmapConfig bitmapConfig = getRandomBitmapConfig();
                        final String randomBackGroundType = getRandomBackGround(bitmapConfig);
                        System.out.println();
                        MapValue mapValue = new MapValueExploreImpl(key, 1, randomBackGroundType, new HashSet<>(), icons);

                        for (int k = 0; k < value; k++) {
                            icons.add(getRandomIconCoordinate(setting, mapValue));
                        }
                        mapValue.addDecorate(icons);
                        map.put(key, mapValue);
                    }
                }
                return map;

            case Base:
                break;

            case None:
                return map;

        }
        return new HashMap<>();
    }

    private String getRandomBackGround(BitmapConfig bitmapConfig) {
        final List<String> collect = new ArrayList<>(bitmapConfig.getKeys());
        return collect.get(random.nextInt(collect.size()));
    }

    private IconCoordinate getRandomIconCoordinate(Setting setting, MapValue mapValue) {
        int size = (int) setting.getFieldSetting().getSize();

        Random random = new Random();
        int vectorX = random.nextInt(size / 3) - size / 6;
        int vectorY = random.nextInt(size / 3) - size / 6;
        final Map<String, BitmapConfig> map = PrototypeDecor.getInstance().getRandomKeys();
        final List<String> keys = new ArrayList<>(PrototypeDecor.getInstance().getRandomKeys().keySet());
        final String key = keys.get(random.nextInt(keys.size()));
        final List<String> iconsKey = new ArrayList<>(Objects.requireNonNull(map.get(key)).getKeys());

        return new IconCoordinate(vectorX, vectorY, 1, key, iconsKey.get(random.nextInt(iconsKey.size())), mapValue);
    }

    private BitmapConfig getRandomBitmapConfig() {
        final Map<String, BitmapConfig> map = PrototypeGrid.getInstance().getRandomKeys();
        final List<String> collect = new ArrayList<>(map.keySet());
        final String key = collect.get(random.nextInt(collect.size()));
        return map.get(key);
    }


    @NotNull
    @Contract("_ -> new")
    public ModelDataExplore getModelGroup(Map<Integer, MapValue> map) {
        Set<GroupExploreModel> opponentGroupsModel = new HashSet<>();
        opponentGroupsModel.add(GroupComponent.getMapGroupModel(
                GroupTypeDifficult.easy001,
                GroupTypeEnvironment.easy,
                GroupTypeGrade.easy,
                BattleMapType.smallEasy
        ));
        opponentGroupsModel.add(GroupComponent.getMapGroupModel(
                GroupTypeDifficult.easy002,
                GroupTypeEnvironment.easy,
                GroupTypeGrade.easy,
                BattleMapType.smallEasy
        ));
        opponentGroupsModel.add(GroupComponent.getMapGroupModel(
                GroupTypeDifficult.easy003,
                GroupTypeEnvironment.easy,
                GroupTypeGrade.easy,
                BattleMapType.smallEasy
        ));

        opponentGroupsModel.add(GroupComponent.getMapGroupModel(
                GroupTypeDifficult.easy003,
                GroupTypeEnvironment.easy,
                GroupTypeGrade.easy,
                BattleMapType.smallEasy
        ));

        opponentGroupsModel.add(GroupComponent.getMapGroupModel(
                GroupTypeDifficult.easy003,
                GroupTypeEnvironment.easy,
                GroupTypeGrade.easy,
                BattleMapType.smallEasy
        ));

        opponentGroupsModel.add(GroupComponent.getMapGroupModel(
                GroupTypeDifficult.easy003,
                GroupTypeEnvironment.easy,
                GroupTypeGrade.easy,
                BattleMapType.smallEasy
        ));


        GroupExploreModel playerGroupModel = GroupComponent.getMapGroupModel(
                GroupTypeDifficult.hard001,
                GroupTypeEnvironment.easy,
                GroupTypeGrade.easy,
                BattleMapType.smallEasy
        );
        return new ModelDataExplore(map, playerGroupModel, opponentGroupsModel);
    }

    public Map<Integer, MapValue> getBattleMap(MapType mapType) {
        int sizeAreaX = 20;
        int sizeAreaY = 20;
        Map<Integer, MapValue> map = new HashMap<>();
        Random random = new Random();

        switch (mapType) {
            case Simply:

                for (int i = 0; i < sizeAreaX; i++) {
                    for (int j = 0; j < sizeAreaY; j++) {
                        int key = i + j * DefaultValue.FIELD_CAPACITY;

                        final int value = random.nextInt(5) - 1;

                        Set<MapValueIconDecorate> icons = new HashSet<>();


                        final BitmapConfig bitmapConfig = getRandomBitmapConfig();
                        final String randomBackGroundType = getRandomBackGround(bitmapConfig);
                        MapValue mapValue = new MapValueBattleImpl(key, 1, randomBackGroundType, new HashSet<>(), icons);

                        for (int k = 0; k < value; k++) {
//                            icons.add(getRandomIconCoordinate(setting, mapValue));
                        }

                        mapValue.addDecorate(icons);


                        final int modelGroupCounter = random.nextInt(2);
                        if (modelGroupCounter == 1) {
//                            final GroupExploreModel groupExploreModel = GroupComponent.getMapGroupModel(BattleMapType.mediumEasy);
//                            mapValue.setMapGroup(mapGroupModel);
                        }
                        map.put(key, mapValue);
                    }
                }
                return map;

            case Base:
                break;

            case None:
                return map;

        }
        return new HashMap<>();
    }
}
