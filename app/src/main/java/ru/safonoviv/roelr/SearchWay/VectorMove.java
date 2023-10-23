package ru.safonoviv.roelr.SearchWay;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VectorMove {
    private int vectorX;
    private int vectorY;

    public VectorMove(int vectorX, int vectorY) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }
}
