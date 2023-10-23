package ru.safonoviv.roelr.Map;

import ru.safonoviv.roelr.GenerateObject.Component.MapComponent;

public class MapBattleImpl {
    private static volatile MapBattleImpl mapBattle;

    private MapBattleImpl() {
    }

    public MapBattleImpl getInstance() {
        MapBattleImpl singleton = mapBattle;
        if (singleton == null) {
            synchronized (MapBattleImpl.class) {
                if (mapBattle == null) {
                    mapBattle = new MapBattleImpl();
                }
            }
        }
        return mapBattle;
    }
}
