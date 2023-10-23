package ru.safonoviv.roelr.Graphics.Display;


import android.graphics.Canvas;
import ru.safonoviv.roelr.Graphics.Panel.PanelInt;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ActionOnClick;

import java.util.*;
import java.util.stream.Stream;

public class PanelControl {
    private static volatile PanelControl panelControl;
    private PanelInt active;
    private final Set<PanelInt> noActivePanels = Collections.synchronizedSet(new HashSet<>());
    private final Set<PanelInt> activePanels = Collections.synchronizedSet(new HashSet<>());

    private PanelControl() {
    }

    public static PanelControl getInstance() {
        PanelControl singleton = panelControl;
        if (singleton == null) {
            synchronized (PanelControl.class) {
                if (panelControl == null) {
                    panelControl = new PanelControl();
                }
            }
        }
        return panelControl;
    }

    public void addPanel(PanelInt panel) {
        noActivePanels.add(panel);
    }

    public void addActivePanel(PanelInt panel) {
        activePanels.add(panel);
    }


    public boolean getCollision(int positionX, int positionY) {
        final Optional<PanelInt> first = Stream.of(noActivePanels, activePanels).flatMap(Collection::stream).filter(t -> t.getCollision(positionX, positionY)).findFirst();
        return first.isPresent();
    }

    public boolean getActionCollision(int positionX, int positionY) {
        final Optional<PanelInt> first = Stream.of(noActivePanels, activePanels).flatMap(Collection::stream).filter(t -> t.getActiveCollision(positionX, positionY) != null).findFirst();
        return first.isPresent();
    }


    public void addPanel(Set<PanelEditMap> allIcons) {
        noActivePanels.addAll(allIcons);
    }

    public void removePanel(Set<PanelEditMap> allIcons) {
        allIcons.forEach(noActivePanels::remove);
    }

    public void shift(int distanceX, int distanceY) {
        if (active != null) {
            active.shift(distanceX, distanceY);
        }

    }

    public void draw(Canvas canvas) {
        Stream.of(activePanels, noActivePanels).flatMap(Collection::stream).forEach(n -> n.draw(canvas));

    }


    public void removeActivePanel(PanelInt panel) {
        activePanels.remove(panel);

    }

    public boolean getCollisionAndSetAction(int positionX, int positionY) {
        final Optional<PanelInt> panelObject = Stream.of(noActivePanels, activePanels).flatMap(Collection::stream).filter(t -> t.getCollision(positionX, positionY)).findFirst();
        if (panelObject.isPresent()) {
            ActionOnClick activeCollision = panelObject.get().getActiveCollision(positionX, positionY);
            if (activeCollision != null) {
                boolean action = activeCollision.getAction();
                if (action) {
                    activePanels.remove(panelObject.get());
                }
            }

            return true;
        } else return false;
    }
}
