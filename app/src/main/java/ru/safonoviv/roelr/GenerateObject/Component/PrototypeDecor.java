package ru.safonoviv.roelr.GenerateObject.Component;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.MainActivity;
import ru.safonoviv.roelr.R;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Objects;

public class PrototypeDecor extends Prototype {
    private volatile static PrototypeDecor prototypeDecor;
    private static final DecimalFormat df = new DecimalFormat("_0.00");


    private PrototypeDecor() {
        super();
        bitmaps = new HashMap<>();
        cashedBitmaps = new HashMap<>();
        randomKeys = new HashMap<>();
        uniqueKeys = new HashMap<>();

        initBitmapsValue(R.drawable.tree_002_4_2, "tree_002_4_2", (int) (DefaultValue.DEFAULT_FIELD_SIZE / 3 * 2), 4, 2, 0, false);
        initBitmapsValue(R.drawable.tree_001_2_2, "tree_001_2_2", (int) (DefaultValue.DEFAULT_FIELD_SIZE / 3 * 2), 2, 2, 0, false);
        initBitmapsValue(R.drawable.tree_003_2_1, "tree_003_2_1", (int) (DefaultValue.DEFAULT_FIELD_SIZE / 3 * 2), 2, 1, 0, false);
        initBitmapsValue(R.drawable.treasure_rabbit, "treasure_rabbit", (int) (DefaultValue.DEFAULT_FIELD_SIZE / 3 * 2), 1, 1, 2, true);

    }


    public static PrototypeDecor getInstance() {
        PrototypeDecor singleton = prototypeDecor;
        if (singleton == null) {
            synchronized (PrototypeDecor.class) {
                if (prototypeDecor == null) {
                    prototypeDecor = new PrototypeDecor();
                }
            }
        }
        return prototypeDecor;
    }

    private void initBitmapsValue(int id, String key, int size, int rowNumber, int rowMax, int distance, boolean unique) {
        Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), id);
        int height = bitmap.getHeight() / rowMax;
        int width = bitmap.getWidth() / rowNumber;

        BitmapConfig bitmapConfig = new BitmapConfig(key);

        for (int i = 0; i < rowNumber * rowMax; i++) {
            String newKey = key + "_" + (i + 1);
            BitmapMulti bitmapMulti = new BitmapMulti(MainActivity.getContext(), id, size, width * (i % rowNumber), height * (i / rowNumber), width, height, distance);
            bitmaps.put(newKey, bitmapMulti);
            bitmapConfig.addKey(newKey);

        }
        if (!unique) {
            randomKeys.put(key, bitmapConfig);
        } else {
            uniqueKeys.put(key, bitmapConfig);
        }


    }


    public Bitmap getBackGroundBitmap(String item, double resolution) {
        String key = item + df.format(resolution);
        return cashedBitmaps.computeIfAbsent(key, r -> {
            Bitmap newBitmap = Objects.requireNonNull(bitmaps.get(item)).getBitmap();
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(newBitmap, (int) (newBitmap.getWidth() * resolution), (int) (newBitmap.getHeight() * resolution), false);
            cashedBitmaps.put(key, scaledBitmap);
            return scaledBitmap;
        });
    }


}
