package ru.safonoviv.roelr.Common;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.Window;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;

public class Utils {
    public static void setFullScreen(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE

        );
    }





    public static int setColorAlpha(int color, byte alpha) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }

    public static float getStartXForRectObject(float centerX, float width) {
        return centerX - width / 2;

    }

    public static float getStartYForRectObject(float centerY, float height) {
        return centerY - height / 2;
    }

    public static float getEndXForRectObject(float centerX, float width) {
        return centerX + width / 2;

    }

    public static float getEndYForRectObject(float centerY, float height) {
        return centerY + height / 2;
    }


    public static double getDistanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static int getAreaX(double positionX, float sizeX) {
        if (positionX < 0)
            positionX += sizeX;
        return (int) (positionX / sizeX);
    }

    public static int getAreaY(double positionY, float sizeY) {
        if (positionY < 0)
            positionY += sizeY;
        return (int) (positionY / sizeY);
    }


    public static boolean isCollisionWithRectangleObject(int positionX, int positionY, int sizeX, int sizeY, int cursorPositionX, int cursorPositionY) {
        if (Math.abs(positionX - cursorPositionX) < sizeX / 2 && Math.abs(positionY - cursorPositionY) < sizeY / 2) {
            return true;
        }
        return false;
    }



    public static Bitmap changeColor(Bitmap bitmap, byte alpha) {
        long timeStart = System.currentTimeMillis();
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int x = 0; x < pixels.length; ++x) {
            pixels[x] = (pixels[x] != 0) ? setColorAlpha(pixels[x], alpha) : pixels[x];
        }


        Bitmap newBitmap = Bitmap.createBitmap(width, height,
                bitmap.getConfig());
        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        timeStart = System.currentTimeMillis() - timeStart;
        Log.d(String.valueOf(Utils.class), "Expected time: " + timeStart);
        return newBitmap;
    }

    public static Bitmap changeColor(Bitmap bitmap, int newColor, int oldColor) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);


        for (int x = 0; x < pixels.length; ++x) {
            if (pixels[x] == oldColor) {
                pixels[x] = newColor;
            }
        }


        Bitmap newBitmap = Bitmap.createBitmap(width, height,
                bitmap.getConfig());
        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }


    public static double getDistanceBetweenTwoPoints(int coordinate1, int coordinate2, FieldSetting setting) {
        return Utils.getDistanceBetweenTwoPoints(setting.getCenterAreaPositionX(coordinate1),
                setting.getCenterAreaPositionY(coordinate1),
                setting.getCenterAreaPositionX(coordinate2),
                setting.getCenterAreaPositionY(coordinate2));
    }
}
