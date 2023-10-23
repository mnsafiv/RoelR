package ru.safonoviv.roelr.Logic;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Display.Camera;
import ru.safonoviv.roelr.Graphics.Display.CursorPosition;


public abstract class AbstractMap extends SurfaceView implements SurfaceHolder.Callback {

    protected Thread thread;
    protected final GameLoop gameLoop;
    protected CursorPosition cursor;
    protected final Camera camera;

    public AbstractMap(Context context) {
        super(context);
        Setting setting = Setting.getInstance();
        this.camera = setting.getCamera();
        this.cursor = camera.getCursorPosition();
        gameLoop = new GameLoop(this, getHolder());
    }


    public void update() {
        //update camera position
        camera.update();
    }

    public void start() {
        thread = new Thread(gameLoop);
        thread.start();
    }


    public void pause() {
        gameLoop.setRunning(false);
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread = null;

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                cursor.addCursor(event.getActionIndex(),event.getX(), event.getY());
                cursor.setActiveCursor(event.getX(), event.getY());
                return true;

            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 2) {
                    camera.cameraResolution.startChangeResolution(
                            event.getX(0),
                            event.getY(0),
                            event.getX(1),
                            event.getY(1));
                }


                cursor.addCursor(event.getActionIndex(),event.getX(event.getActionIndex()), event.getX(event.getActionIndex()));
                cursor.setActiveCursor(event.getX(event.getActionIndex()), event.getX(event.getActionIndex()));

                return true;

            case MotionEvent.ACTION_MOVE:
                camera.setIsMoving(event.getX(), event.getY());

                if (event.getPointerCount() == 2) {
                    cursor.refreshCounterCursor();
//                    camera.cameraResolution.changeResolution(
//                            event.getX(0),
//                            event.getY(0),
//                            event.getX(1),
//                            event.getY(1));
                }


                return true;

            case MotionEvent.ACTION_UP:
                cursor.setSelectCursor(event.getActionIndex(),event.getX(), event.getY());
                camera.setIsUp();
                return true;

            case MotionEvent.ACTION_POINTER_UP:

                cursor.setSelectCursor(event.getActionIndex(),event.getX(event.getActionIndex()), event.getX(event.getActionIndex()));
                cursor.refreshCounterCursor();
                camera.setIsUp();


                return true;

        }


        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }


}
