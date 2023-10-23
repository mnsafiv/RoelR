package ru.safonoviv.roelr.Graphics.Display;


import android.graphics.Bitmap;
import android.graphics.Rect;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Common.Utils;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.GenerateObject.Model.CharacterSkillArea;


public final class Camera {
    private static volatile Camera camera;

    private float moveX;
    private float moveY;
    private CameraState state;
    private double cameraX;
    private double cameraY;
    private double distance;
    private double startX;
    private double startY;
    public CameraResolution cameraResolution;
    @Getter
    private final CursorPosition cursorPosition;

    //for update fixate position
    private double positionCameraX;
    private double positionCameraY;

    @Getter
    private final PanelControl panelControl;
    @Setter
    private Display display;

    private Rect displayBounds;


    private Camera(Setting setting) {
        this.cameraX = 0;
        this.cameraY = 0;
        this.cursorPosition = CursorPosition.getInstance(this, setting.getFieldSetting());
        this.cameraResolution = new CameraResolution();
        this.state = CameraState.isUp;
        this.panelControl = PanelControl.getInstance();
    }

    public static Camera getInstance(Setting setting) {
        Camera singleton = camera;
        if (singleton == null) {
            synchronized (Camera.class) {
                if (camera == null) {
                    camera = new Camera(setting);
                }
            }
        }
        return camera;
    }

    private void setIsDown(float x, float y) {
        moveX = x;
        moveY = y;
        distance = 0;
        startX = cameraX;
        startY = cameraY;


        if (checkCollision(x, y)) {
            state = CameraState.isLock;
        } else {
            state = CameraState.isDown;
        }


    }

    private boolean checkCollision(float x, float y) {
        //collision with panel

        //        final boolean actionCollision = panelControl.getActionCollision((int) x, (int) y);


        return panelControl.getCollision((int) x, (int) y);
    }

    public void setIsMoving(float x, float y) {
        System.out.println();
        switch (state) {
            case isMove:
            case isDown:
                isMoving(x, y);
                break;
            case isUp:
                setIsDown(x, y);
                break;
            case isLock:
                isLock(x, y);
                break;
        }


    }


    private void isLock(float x, float y) {
        int distanceX = (int) Utils.getDistanceBetweenTwoPoints(startX, 0, cameraX - moveX + x, 0);
        int distanceY = (int) Utils.getDistanceBetweenTwoPoints(0, startY, 0, cameraY - moveY + y);

        distance = Utils.getDistanceBetweenTwoPoints(startX, startY, cameraX - moveX + x, cameraY - moveY + y);

        if (moveX > x) {
            distanceX = -distanceX;
        }
        if (moveY > y) {
            distanceY = -distanceY;
        }


        moveX = x;
        moveY = y;


        panelControl.shift(distanceX, distanceY);


    }

    private void isMoving(float x, float y) {
        //need fix
        System.out.println();
        double prevDistance = distance;
        distance = Utils.getDistanceBetweenTwoPoints(startX, startY, cameraX - moveX + x, cameraY - moveY + y);
        if (Math.abs(prevDistance - distance) > 100) {
            moveX = x;
            moveY = y;
            return;
        }

        cameraX = (float) (cameraX - moveX + x);
        cameraY = (float) (cameraY - moveY + y);

        cameraX = Math.min(-displayBounds.left, cameraX);
        cameraX = Math.max(-displayBounds.right, cameraX);
        cameraY = Math.min(-displayBounds.top, cameraY);
        cameraY = Math.max(-displayBounds.bottom, cameraY);

        moveX = x;
        moveY = y;

        state = CameraState.isMove;
    }

    public void setDisplayBounds(Rect displayBounds) {
        this.displayBounds = displayBounds;
    }

    public void setIsUp() {

        state = CameraState.isUp;
    }

    public double getCameraX() {
        return positionCameraX;
    }

    public double getCameraY() {
        return positionCameraY;
    }


    public double getDistanceToCameraX(double x) {
        return (x + positionCameraX) * cameraResolution.getResolutionSize();
    }

    public double getDistanceToCameraY(double y) {
        return (y + positionCameraY) * cameraResolution.getResolutionSize();
    }


    public void update() {
        //need check if moved
        if (positionCameraX != cameraX || positionCameraY != cameraY) {

            positionCameraX = cameraX;
            positionCameraY = cameraY;

            display.updateDrawArea();
        }


    }


    public float getCenterBitmapToCameraX(@NotNull Bitmap bitmap, double centerX) {
        return (float) getDistanceToCameraX(centerX - (double) bitmap.getWidth() / 2);
    }

    public float getCenterBitmapToCameraY(@NotNull Bitmap bitmap, double centerY) {
        return (float) getDistanceToCameraY(centerY - (float) bitmap.getHeight() / 2);
    }


    public double getCameraResolution() {
        return cameraResolution.getResolutionSize();
    }
}
