package ru.safonoviv.roelr.Logic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.Window;
import ru.safonoviv.roelr.Common.Utils;
import ru.safonoviv.roelr.Graphics.Display.DisplayImpl;
import ru.safonoviv.roelr.Map.MapExploreImpl;
import ru.safonoviv.roelr.Map.Value.MapExploreModel;
import ru.safonoviv.roelr.Map.CalculateCoordinate;
import ru.safonoviv.roelr.Object.Model.ModelDataExplore;
import ru.safonoviv.roelr.Active.ObjectObserver;


@SuppressLint("ViewConstructor")
public class LaunchExplore extends AbstractMap {
    private final DisplayImpl displayImpl;
    private final ObjectObserver objectObserver;
    private final MapExploreImpl mapExplore;

    public LaunchExplore(Context context, Window window, ModelDataExplore data) {
        super(context);
        Utils.setFullScreen(window);

        MapExploreModel exploreMap = MapExploreModel.getInstance();
        exploreMap.setMapName("map_004");
        exploreMap.setMapValues(data.getMap().getMapValues());

        displayImpl = DisplayImpl.getInstance();
        displayImpl.setExploreMap(exploreMap);
        displayImpl.updateBoundsMap();
        displayImpl.updateDrawArea();

        mapExplore = MapExploreImpl.getInstance();

        CalculateCoordinate.getInstance().calculateExplore(data.getPlayerGroupModel(), data.getOpponentGroupsModel(), exploreMap);
        CalculateCoordinate.getInstance().addTreasureRabbit(20,exploreMap);

        objectObserver = ObjectObserver.getInstance();

    }


    @Override
    public void update() {
        super.update();
        objectObserver.update();


    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        displayImpl.draw(canvas);
        mapExplore.draw(canvas);


    }

    public void setResultBattle(boolean result) {
//        controlMap.action(result);
    }
}
