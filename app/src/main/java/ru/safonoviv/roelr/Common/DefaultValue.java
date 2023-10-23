package ru.safonoviv.roelr.Common;


import ru.safonoviv.roelr.Common.Item.AddPanel;
import ru.safonoviv.roelr.Common.Item.EditPanel;
import ru.safonoviv.roelr.Common.Item.RabbitChest;
import ru.safonoviv.roelr.Graphics.Layer.GridType;

public class DefaultValue {

    public static final double DEFAULT_POSITION_STATUS_X = 0.0;
    public static final double DEFAULT_POSITION_STATUS_Y = 0.0;
    public static final double MAX_WIDTH_STATUS = 0.35;
    public static final double MAX_HEIGHT_STATUS = 0.30;
    public static final double TARGET_WIDTH_STATUS = 0.20;
    public static final double TARGET_HEIGHT_STATUS = 0.20;
    public static final double TARGET_CURSOR_SIZE_STATUS = 0.03;

    public static final String PLAYER_KEY = "playerGroup";
    public static final String OPPONENT_KEY = "opponentKey";
    public static final int PATHWAY_NO_INIT = -1;
    public static final int EXPLORE_PATH_MAX = 10;


    public static GridType GRID_TYPE;


    public static final double MAX_UPS = 30;
    public static final int TIME_TO_FINISH = 5;
    public static final String SETTING_KEY = "setting";
    public static final int COMMON_MULTIPLIER_PROGRESS = 100;
    public static final int ELITE_MULTIPLIER_PROGRESS = 120;
    public static final int HERO_MULTIPLIER_PROGRESS = 200;
    public static final int FIELD_CAPACITY = 10000;


    //panel edit/add head,end and row
    public static EditPanel editPanel=new EditPanel();
    public static AddPanel addPanel=new AddPanel();
    public static RabbitChest rabbitChest=new RabbitChest();
    public static final int RESOLUTION = 30000;


    public static final int NO_EXIST_GROUP = -1;


    public static float DEFAULT_FIELD_SIZE = 200;
    public static float DEFAULT_CAMERA_RESOLUTION = 1000;
    public static float CAMERA_SENSE = 100;

}
