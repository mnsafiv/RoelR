package ru.safonoviv.roelr.layer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import ru.safonoviv.roelr.Logic.AbstractMap;

public class AnimationActivity002 extends Activity {
    private AbstractMap launchBattlefield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    protected void onResume() {
        Log.d("AnimationActivity002.java", "onCreate");
        super.onResume();
        launchBattlefield.start();
    }

    @Override
    protected void onPause() {
        Log.d("AnimationActivity002.java", "onPause");
        super.onPause();
        launchBattlefield.pause();


    }
}
