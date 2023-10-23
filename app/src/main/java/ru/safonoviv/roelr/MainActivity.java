package ru.safonoviv.roelr;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.safonoviv.roelr.Common.Utils;
import ru.safonoviv.roelr.layer.AnimationActivityMapExplore;

public class MainActivity extends AppCompatActivity {

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }
    private static MainActivity mInstance;
    public static synchronized MainActivity getInstance() {
        return mInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;


        Utils.setFullScreen(getWindow());
        setContentView(R.layout.activity_main);


        Button button_start_level = findViewById(R.id.startGame);



        button_start_level.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnimationActivityMapExplore.class);
            startActivity(intent);
        });
    }
}