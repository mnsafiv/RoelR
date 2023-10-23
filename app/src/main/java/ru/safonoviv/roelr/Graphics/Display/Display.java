package ru.safonoviv.roelr.Graphics.Display;

import android.graphics.Canvas;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Map.Value.MapExploreModel;
import ru.safonoviv.roelr.Map.Value.MapValueExploreImpl;

public interface Display {

    void updateBoundsMap();

    void updateDrawArea();

    void draw(Canvas canvas);

    void setExploreMap(MapExploreModel exploreMap);

    void addToDrawArea(@NotNull MapValueExploreImpl mapValue);
}
