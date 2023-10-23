package ru.safonoviv.roelr.Logic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.Window;
import ru.safonoviv.roelr.Common.Utils;


@SuppressLint("ViewConstructor")
public class LaunchBattlefield extends AbstractMap {
//    private final Back back;


    public LaunchBattlefield(Context context, Window window) {
        super(context);
        Utils.setFullScreen(window);




//        back = new Back(context);

    }


    public void update() {
        super.update();



    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


    }
}
