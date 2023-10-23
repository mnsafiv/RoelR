package ru.safonoviv.roelr.Map.Value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
public class MapPrototypeBattle {
    private static volatile MapPrototypeBattle mapPrototypeBattle;
    private Map<Integer, MapValue> mapValues;
    private int capacityX=-1;
    private int capacityY=-1;

    public synchronized static MapPrototypeBattle getInstance() {
        if (mapPrototypeBattle == null) {
            mapPrototypeBattle = new MapPrototypeBattle();
        }
        return mapPrototypeBattle;
    }

    public void updateInfo(){
        AtomicInteger sizeX= new AtomicInteger();
        AtomicInteger sizeY= new AtomicInteger();
        FieldSetting fieldSetting = Setting.getInstance().getFieldSetting();


        mapValues.forEach((key, value) -> {
            int areaY = fieldSetting.getAreaY(key);
            int areaX = fieldSetting.getAreaX(key);
            if (sizeX.get() < areaX) sizeX.set(areaX);
            if (sizeY.get() < areaY) sizeY.set(areaY);
        });


        capacityX=sizeX.get();
        capacityY=sizeY.get();


    }

}
