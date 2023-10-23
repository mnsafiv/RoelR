package ru.safonoviv.roelr.Graphics.Display;


public class UpdateSelectCursor {
    private int sequence =-1;
    private final CursorPosition cursorPosition;

    public UpdateSelectCursor(CursorPosition cursorPosition) {
        this.cursorPosition = cursorPosition;
    }

    public boolean isUpdatedCursorPosition(){
        if(sequence != cursorPosition.getControlNumber()){
            sequence =cursorPosition.getControlNumber();
            return true;
        }
        return false;
    }
}
