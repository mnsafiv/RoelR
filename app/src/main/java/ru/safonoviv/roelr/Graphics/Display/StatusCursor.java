package ru.safonoviv.roelr.Graphics.Display;

import android.graphics.Canvas;
import android.graphics.Paint;
import ru.safonoviv.roelr.Common.Setting;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StatusCursor {
    private final ConcurrentHashMap<Integer, CursorCounter> cursors = new ConcurrentHashMap<>();
    private final Paint paint;


    public StatusCursor(Paint paint) {

        this.paint = paint;
    }

    public void addNewCursor(int number, float x, float y) {
        cursors.put(number, new CursorCounter((int) x, (int) y));
    }

    public void changeCursor(float x, float y) {
        System.out.println();
        synchronized (cursors){
            cursors.forEach((key, value) -> {
                if (!value.contains((int) x, (int) y)) {
                    value.setPosition((int) x, (int) y);
                }
            });
        }




    }


    public void draw(Canvas canvas) {
        final int heightPixels = Setting.getInstance().getCurrentHeight();
        final int widthPixels = Setting.getInstance().getCurrentWidth();

        int x = widthPixels / 10 * 8;
        int y = heightPixels / 7 * 5;
        int shiftY = 30;


        AtomicInteger position = new AtomicInteger(1);


        cursors.forEach((key, value) ->
                canvas.drawText(key + ": " + value.getPositionX() + " " + value.getPositionY(), x, y + shiftY * position.getAndAdd(1), paint));


    }

    public void remove(int key) {
        cursors.remove(key);
    }

    public void remove() {
        cursors.clear();
    }
}
