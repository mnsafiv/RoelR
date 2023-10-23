package ru.safonoviv.roelr.Object.Model;

import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Layer.GridType;
import lombok.Getter;
import ru.safonoviv.roelr.Map.Value.MapExploreModel;
import ru.safonoviv.roelr.Map.Value.MapValue;

import java.util.Map;
import java.util.Set;

@Getter
public class ModelDataExplore {

    private final MapExploreModel map;
    private final GridType gridType;
    private final GroupExploreModel playerGroupModel;
    private final Set<GroupExploreModel> opponentGroupsModel;


    public ModelDataExplore(Map<Integer, MapValue> mapValues, GroupExploreModel playerGroupModel, Set<GroupExploreModel> opponentGroupsModel) {
        this.map = MapExploreModel.getInstance();
        this.map.setMapValues(mapValues);
        this.gridType = Setting.getInstance().getGridSetting().getGrid();

        this.playerGroupModel = playerGroupModel;
        this.opponentGroupsModel = opponentGroupsModel;
    }

}
