package ru.safonoviv.roelr.Graphics.Display;

import lombok.Getter;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;

import java.util.HashMap;
import java.util.Map;

public class CursorPosition {
    private static volatile CursorPosition cursorPosition;

    private final Camera camera;
    private final FieldSetting setting;

    private final Map<Integer, CursorCounter> counter = new HashMap<>();

    @Getter
    private int activeCursorX = -1;
    @Getter
    private int activeCursorY = -1;

    @Getter
    private int cursorPositionX = -1;
    @Getter
    private int cursorPositionY = -1;

    @Getter
    private int cursorCoordinate = -1;
    @Getter
    private int controlNumber = -1;

    private CursorPosition(Camera camera, FieldSetting setting) {
        this.camera = camera;
        this.setting = setting;
    }

    public static CursorPosition getInstance(Camera camera, FieldSetting setting) {
        if (cursorPosition == null) {
            cursorPosition = new CursorPosition(camera, setting);
        }
        return cursorPosition;
    }

    public static void setCursorPosition(CursorPosition cursorPosition) {
        CursorPosition.cursorPosition = cursorPosition;
    }


    public void setActiveCursor(float positionX, float positionY) {
        this.activeCursorX = (int) positionX;
        this.activeCursorY = (int) positionY;
    }


    public void setSelectCursor(int actionIndex, float positionX, float positionY) {
        final CursorCounter cursorCounter = counter.get(actionIndex);

        if (cursorCounter == null) {
            return;
        }

        if (cursorCounter.checkDistance(10, positionX, positionY)) {
            double resolution = camera.cameraResolution.getResolutionSize();

            this.cursorCoordinate = setting.getCoordinate(
                    (positionX / resolution - camera.getCameraX()),
                    (positionY / resolution - camera.getCameraY()));

            controlNumber = Integer.valueOf(cursorPositionX).hashCode() +
                    Integer.valueOf(cursorPositionY).hashCode();


            this.cursorPositionX = (int) positionX;
            this.cursorPositionY = (int) positionY;


        }


    }


    public void addCursor(int index, float positionX, float positionY) {
        counter.put(index, new CursorCounter(positionX, positionY));
    }

    public boolean isChanged(int sequence) {
        return controlNumber != sequence;
    }

    public void refreshCounterCursor() {
        counter.clear();
    }


}
