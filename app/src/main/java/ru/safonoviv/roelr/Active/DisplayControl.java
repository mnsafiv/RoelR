package ru.safonoviv.roelr.Active;

import lombok.Setter;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Display.CursorPosition;
import ru.safonoviv.roelr.Graphics.Display.PanelControl;
import ru.safonoviv.roelr.Map.MapExploreImpl;
import ru.safonoviv.roelr.Object.AbstractActiveObject;

public class DisplayControl {
    private static volatile DisplayControl displayControl;
    private final PanelControl panelControl;
    @Setter
    private AbstractActiveObject active;
    private final MapExploreImpl mapExplore;
    private final CursorPosition cursor;
    private int sequence;

    private DisplayControl() {
        cursor = Setting.getInstance().getCamera().getCursorPosition();
        mapExplore=MapExploreImpl.getInstance();
        panelControl = PanelControl.getInstance();
        sequence = -1;
    }

    public static DisplayControl getInstance() {
        DisplayControl singleton = displayControl;
        if (singleton == null) {
            synchronized (DisplayControl.class) {
                if (displayControl == null) {
                    displayControl = new DisplayControl();
                }
            }
        }
        return displayControl;
    }

    public void update() {
        if (cursor.isChanged(sequence)) {

            //update logic
            sequence = cursor.getControlNumber();

            //collision with panel
//            if(panelControl.getCollision(cursor.getCursorPositionX(),cursor.getCursorPositionY())){
//                System.out.println();
//                return;
//            }

            if(panelControl.getCollisionAndSetAction(cursor.getCursorPositionX(),cursor.getCursorPositionY())){
                System.out.println();
                return;
            }

            //collision with active
            if(active.getTouchCollision(cursor.getCursorCoordinate())){
                return;
            }

            //collision with display
            if(mapExplore.contains(cursor.getCursorCoordinate())){
                return;
            }



        }


    }
}
