package ru.safonoviv.roelr.Active;

import lombok.Getter;
import ru.safonoviv.roelr.Object.AbstractActiveObject;
import ru.safonoviv.roelr.Object.ObjectProperties;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ObjectObserver {
    private static volatile ObjectObserver objectObserver;
    private final DisplayControl displayControl;
    private final Set<AbstractActiveObject> existObjects = new HashSet<>();

    private ObjectObserver() {
        displayControl = DisplayControl.getInstance();
    }

    public static ObjectObserver getInstance() {
        ObjectObserver singleton = objectObserver;
        if (singleton == null) {
            synchronized (ObjectObserver.class) {
                if (objectObserver == null) {
                    objectObserver = new ObjectObserver();
                }
            }
        }
        return objectObserver;
    }

    public void update() {
        displayControl.update();
        existObjects.forEach(ObjectProperties::update);
    }
}
