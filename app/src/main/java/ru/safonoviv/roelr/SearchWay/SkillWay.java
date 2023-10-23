package ru.safonoviv.roelr.SearchWay;


import ru.safonoviv.roelr.Common.DefaultValue;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Graphics.Layer.GridType;

import java.util.*;

public class SkillWay {
    private Set<Integer> path;
    private FieldSetting fieldSetting;

    public SkillWay(int coordinate, int distance) {
        this.fieldSetting = Setting.getInstance().getFieldSetting();
        loadAdjacentNode(coordinate, distance+2);

    }

    private void loadAdjacentNode(int coordinate, int distance) {

        List<List<VectorMove>> moveVectors;
        List<VectorMove> vectors;

        if (DefaultValue.GRID_TYPE== GridType.HexHorizontal) {
            moveVectors = VectorAdjacent.getHorizontalVectorMove();
        } else  if(DefaultValue.GRID_TYPE== GridType.HexVertical){
            moveVectors = VectorAdjacent.getHorizontalVectorMove();
        }else{
            throw new IllegalArgumentException("No implement grid");
        }


        Set<Integer> visited = new HashSet();
        Queue<Integer> unvisited = new LinkedList<>(Collections.singleton(coordinate));

        int counterUnvisited=1;


        while (distance>0) {
            Integer curCoordinate = unvisited.poll();
            if(curCoordinate==null)
                break;


            if (fieldSetting.getAreaY(curCoordinate) % 2 == 0) {
                vectors = moveVectors.get(0);
            } else vectors = moveVectors.get(1);


            vectors.stream().filter(t -> visited.contains(curCoordinate)).forEach(n -> {

            });

            vectors.forEach(n->{
                int next = VectorAdjacent.getPositionVector(fieldSetting,curCoordinate,n.getVectorX(),n.getVectorY());
                if(!visited.contains(next) && next>=0){
                    visited.add(next);
                    unvisited.add(next);
                }


            });

            counterUnvisited--;
            if(counterUnvisited==0){
                counterUnvisited=unvisited.size();
                distance--;
            }

        }


        path = visited;
    }

    public Set<Integer> getPath() {
        return path;
    }
}








