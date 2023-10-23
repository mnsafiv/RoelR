package ru.safonoviv.roelr.GenerateObject.Component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.GenerateObject.Model.CharacterBitmapModel;
import ru.safonoviv.roelr.MainActivity;
import ru.safonoviv.roelr.R;

import java.util.HashMap;
import java.util.Map;

public class CharacterBitmapComponent {

    private static CharacterBitmapComponent cm;
    private final Map<Integer, Bitmap> characterMap = new HashMap<>();
    private final int maxCameraResolution = 1;
    private final int sizeImage = (int) DefaultValue.DEFAULT_FIELD_SIZE / 2;
    private final int sizeAspect = 2;



    private CharacterBitmapComponent() {
        Context context = MainActivity.getContext();

        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.character_archer_001);
        int endX = bitmap1.getWidth() / 2;
        int endY = bitmap1.getHeight();

        CharacterBitmapModel.getInstance().getCharacterBitmaps().forEach((key, value) -> {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), value.getId());
            Bitmap bitmapMaxSize = Bitmap.createBitmap(bitmap, 0, 0, endX, endY);
            bitmapMaxSize = Bitmap.createScaledBitmap(bitmapMaxSize, maxCameraResolution * sizeImage, maxCameraResolution * sizeImage * sizeAspect, false);
            characterMap.put(value.getIdBitmap(), bitmapMaxSize);
        });

    }

    public static CharacterBitmapComponent getInstance() {
        CharacterBitmapComponent singleton = cm;
        if (singleton == null) {
            synchronized (CharacterBitmapComponent.class) {
                if (cm == null) {
                    cm = new CharacterBitmapComponent();
                }
            }
        }
        return cm;
    }
    public Bitmap getBitmapById(int id) {
        return characterMap.get(id);
    }

}
