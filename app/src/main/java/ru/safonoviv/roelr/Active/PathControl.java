package ru.safonoviv.roelr.Active;

import lombok.Getter;
import ru.safonoviv.roelr.SearchWay.PathWay;

import java.util.HashSet;
import java.util.Set;

@Getter
public class PathControl {
    private static volatile PathControl pathControl;
    private final Set<PathWay> pathWays = new HashSet<>();


    private PathControl() {
    }

    public static synchronized PathControl getInstance() {
        PathControl singleton = pathControl;
        if (singleton == null) {
            synchronized (PathControl.class) {
                if (pathControl == null) {
                    pathControl = new PathControl();
                }
            }
        }
        return pathControl;
    }
}
