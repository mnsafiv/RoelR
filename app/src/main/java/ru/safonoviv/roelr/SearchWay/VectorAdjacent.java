package ru.safonoviv.roelr.SearchWay;

import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VectorAdjacent {
    public static List<List<VectorMove>> getVerticalVectorMove(){
        List<VectorMove> vector01 = Arrays.asList(
                new VectorMove(0, -2),
                new VectorMove(0, -1),
                new VectorMove(0, 1),
                new VectorMove(0, 2),
                new VectorMove(-1, 1),
                new VectorMove(-1, -1));


        List<VectorMove> vector02 = Arrays.asList(
                new VectorMove(0, -2),
                new VectorMove(1, -1),
                new VectorMove(1, 1),
                new VectorMove(0, 2),
                new VectorMove(0, 1),
                new VectorMove(0, -1));

        List<List<VectorMove>> vectors = new ArrayList<>();
        vectors.add(vector01);
        vectors.add(vector02);
        return vectors;
    }
    public static List<List<VectorMove>> getHorizontalVectorMove(){
        List<VectorMove> vector01 = Arrays.asList(
                new VectorMove(-1, -1),
                new VectorMove(0, -1),
                new VectorMove(1, 0),
                new VectorMove(0, 1),
                new VectorMove(-1, 1),
                new VectorMove(-1, 0));


        List<VectorMove> vector02 = Arrays.asList(
                new VectorMove(0, -1),
                new VectorMove(1, -1),
                new VectorMove(1, 0),
                new VectorMove(1, 1),
                new VectorMove(0, 1),
                new VectorMove(-1, 0));

        List<List<VectorMove>> vectors = new ArrayList<>();
        vectors.add(vector01);
        vectors.add(vector02);
        return vectors;
    }
    public static int getPositionVector(FieldSetting setting, int point, int vectorX, int vectorY) {
        return point + vectorX + vectorY * setting.getSetting().getCapacity();
    }
}
