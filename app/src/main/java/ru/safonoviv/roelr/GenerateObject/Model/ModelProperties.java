package ru.safonoviv.roelr.GenerateObject.Model;

import lombok.Getter;

@Getter
public class ModelProperties {
    private final int id;
    private final int idBitmap;
    private final int row;
    private final int number;

    public ModelProperties(int id, int idBitmap, int row, int rowNumber) {
        this.id = id;
        this.idBitmap = idBitmap;
        this.row = row;
        this.number = rowNumber;
    }

    public ModelProperties(int id, int idBitmap) {
        this.id = id;
        this.idBitmap = idBitmap;
        this.row = 1;
        this.number = 1;
    }
}