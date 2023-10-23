package ru.safonoviv.roelr.Common;


import android.graphics.Rect;
import lombok.Getter;
import ru.safonoviv.roelr.Active.DisplayControl;
import ru.safonoviv.roelr.Graphics.Display.Camera;
import ru.safonoviv.roelr.Graphics.Display.DisplayImpl;
import ru.safonoviv.roelr.Graphics.Layer.*;

public class Setting {

    private static volatile Setting setting;
    @Getter
    private GridDraw grid;
    @Getter
    private final FieldSetting fieldSetting;
    @Getter
    private final GridSetting gridSetting;
    @Getter
    private Camera camera;
    private Rect displayBounds;


    private Setting(GridType gridType) {
        this.gridSetting = new GridSetting(gridType);
        if (gridType == GridType.HexVertical) {
            this.fieldSetting = new HexVertical(this);
        } else {
            this.fieldSetting = new HexHorizontal(this);
        };
        this.camera = Camera.getInstance(this);
        this.grid = new HexGrid(DefaultValue.DEFAULT_FIELD_SIZE / 2, gridType,camera);

    }


    public static Setting getInstance() {
        Setting singleton = setting;
        if (singleton == null) {
            synchronized (Setting.class) {
                if (setting == null) {
                    setting = new Setting(DefaultValue.GRID_TYPE);
                }
            }
        }
        return setting;
    }

    public void setDisplayBounds(Rect bounds) {
        this.displayBounds = bounds;
        this.camera.setDisplay(DisplayImpl.getInstance());


    }

    public int getCurrentHeight() {
        return displayBounds.height();
    }

    public int getCurrentWidth() {
        return displayBounds.width();
    }
}
