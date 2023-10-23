package ru.safonoviv.roelr.Logic;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import ru.safonoviv.roelr.Common.DefaultValue;

import static java.lang.Thread.sleep;

public class GameLoop implements Runnable {
    private final double UPS_PERIOD = 1E+3 / DefaultValue.MAX_UPS;
    private boolean isRunning = false;
    private final SurfaceHolder surfaceHolder;
    private final AbstractMap abstractMap;


    public GameLoop(AbstractMap abstractMap, SurfaceHolder surfaceHolder) {
        this.abstractMap = abstractMap;
        this.surfaceHolder = surfaceHolder;
    }


    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        isRunning = true;

        int updateCount = 0;
        int frameCount = 0;

        long startTime = 0;
        long elapsedTime;
        long sleepTime;

        Canvas canvas = null;
        while (isRunning) {

            if (surfaceHolder.getSurface().isValid()) {
                try {
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        abstractMap.update();
                        abstractMap.draw(canvas);
                        updateCount++;
                        frameCount++;
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                //pause game loop to not exceed target UPS
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
                if (sleepTime > 0) {
                    try {
                        sleep(sleepTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                //skip frames to keep up with target UPS
                while (sleepTime < 0 && updateCount < DefaultValue.MAX_UPS - 1) {
                    abstractMap.update();
                    updateCount++;
                    elapsedTime = System.currentTimeMillis() - startTime;
                    sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
//                    Log.d(String.valueOf(GameLoop.class), "sleepTime: " + sleepTime);

                }


                //calculate average UPS and FPS
                elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime >= 1000) {
                    Log.d(String.valueOf(GameLoop.class), "UPS:" + updateCount);
                    Log.d(String.valueOf(GameLoop.class), "FPS: " + frameCount);
                    updateCount = 0;
                    frameCount = 0;
                    startTime = System.currentTimeMillis();

                }

            }
        }
    }


}
