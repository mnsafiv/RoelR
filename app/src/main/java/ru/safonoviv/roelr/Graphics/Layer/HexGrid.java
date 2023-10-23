package ru.safonoviv.roelr.Graphics.Layer;

import android.graphics.*;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Graphics.Display.Camera;


public class HexGrid extends GridDraw {
    private double start;
    private final Camera camera;
    final double section = Math.toRadians(60);

    public HexGrid(float radius, GridType gridType, Camera camera) {
        super(radius);
        this.camera = camera;


        switch (gridType) {
            case HexVertical:
                start = Math.toRadians(0);
                break;
            case HexHorizontal:
                start = Math.toRadians(30);
                break;
        }


    }


    @Override
    public void draw(Canvas canvas, int coordinate, Paint paint, FieldSetting setting) {
        canvas.drawPath(
                calculatePathCamera(
                        setting.getCenterAreaPositionX(coordinate),
                        setting.getCenterAreaPositionY(coordinate)),
                paint);


        //delete after
        Paint paintText = new Paint();
        float nextX = (float) camera.getDistanceToCameraX(
                setting.getCenterAreaPositionX(coordinate) - setting.getRadius() / 3);
        float nextY = (float) camera.getDistanceToCameraY(
                setting.getCenterAreaPositionY(coordinate) - setting.getRadius() / 3);
        String str = String.valueOf(coordinate);
        canvas.drawText(str, nextX, nextY, paintText);

    }


    @Override
    public void draw(Canvas canvas, int coordinate, Bitmap bitmap, @NotNull FieldSetting setting) {
        Paint paint = new Paint();
        canvas.drawPath(
                calculatePathCamera(
                        setting.getCenterAreaPositionX(coordinate),
                        setting.getCenterAreaPositionY(coordinate)),
                paint);

        PointF points = setting.getCoordinateStartBitmap(bitmap, coordinate);

        canvas.drawBitmap(bitmap, (float) camera.getDistanceToCameraX(points.x), (float) camera.getDistanceToCameraY(points.y), null);


    }

    @Override
    public void draw(Canvas canvas, float positionX, float positionY, Paint paint, FieldSetting setting) {
        canvas.drawPath(
                calculatePathCamera(
                        positionX,
                        positionY),
                paint);

    }

    @Override
    public Path getPath() {
        int x = 100;
        int y = 100;
        Path hex = new Path();
        hex.reset();
        hex.moveTo(
                (float) (x + radius * Math.cos(start)),
                (float) (y + radius * Math.sin(start)));


        for (int i = 1; i < 6; i++) {
            hex.lineTo(
                    (float) (x + radius * Math.cos(section * i + start)),
                    (float) (y + radius * Math.sin(section * i + start)));


        }

        System.out.println();

        hex.close();


        return hex;
    }


    private Path calculatePathCamera(float x, float y) {
        Path hex = new Path();
        hex.reset();
        hex.moveTo(
                (float) camera.getDistanceToCameraX(x + radius * Math.cos(start)),
                (float) camera.getDistanceToCameraY(y + radius * Math.sin(start)));
        for (int i = 1; i < 6; i++) {
            hex.lineTo(
                    (float) camera.getDistanceToCameraX(x + radius * Math.cos(section * i + start)),
                    (float) camera.getDistanceToCameraY(y + radius * Math.sin(section * i + start)));

        }

        hex.close();

        return hex;
    }


}


