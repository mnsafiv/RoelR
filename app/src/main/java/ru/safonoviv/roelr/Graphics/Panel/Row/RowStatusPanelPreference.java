package ru.safonoviv.roelr.Graphics.Panel.Row;

import android.graphics.PointF;
import android.graphics.Rect;
import lombok.Getter;
import ru.safonoviv.roelr.Graphics.Panel.RectItem.ActionOnClickImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RowStatusPanelPreference {
    @Getter
    private final int id;
    private final List<ActionOnClickImpl> messages;
    private final List<PointF> pointFs = new LinkedList<>();
    private Rect rect;


    public RowStatusPanelPreference(int groupId, List<ActionOnClickImpl> messages) {
        this.id = groupId;
        this.messages = messages;
    }


    public List<PointF> getPosition() {
        return pointFs;
    }

    public boolean alignment(Rect rectCur) {
        rect = rectCur;
        pointFs.clear();
        messages.forEach(t -> pointFs.add(new PointF(t.getRect().left, t.getRect().top)));
        return checkPosition();
    }

    private boolean checkPosition() {
        Optional<ActionOnClickImpl> first = messages.stream().filter(t -> !rect.contains(t.getRect())).findFirst();
        return first.isPresent();
    }

    public void setRect(Rect rectCur) {
        this.rect = rectCur;
    }
}
