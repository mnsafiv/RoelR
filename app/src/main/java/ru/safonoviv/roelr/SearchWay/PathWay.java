package ru.safonoviv.roelr.SearchWay;


import lombok.Getter;
import ru.safonoviv.roelr.Active.PathControl;
import ru.safonoviv.roelr.Common.Setting;
import ru.safonoviv.roelr.Graphics.Layer.FieldSetting;
import ru.safonoviv.roelr.Common.DefaultValue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathWay implements Runnable {
    @Getter
    private final FieldPrototype field;
    private final Map<Integer, Node> checkedPosition;
    @Getter
    private Map<Integer, Node> pathWayMax;
    @Getter
    private Map<Integer, Node> pathWay;

    private int fieldSequenceCounter = DefaultValue.PATHWAY_NO_INIT;

    private int maxDistance = Integer.MAX_VALUE;
    private int distance = Integer.MAX_VALUE;

    private Map<Integer, Node> newPathWayMax;
    private Map<Integer, Node> newPathWay;


    private int startCoordinate = 0;
    private boolean objectExist = true;
    private final FieldSetting setting;


    public PathWay(int startCoordinate, FieldPrototype field) {
        this.startCoordinate = startCoordinate;
        this.field = field;
        this.setting = Setting.getInstance().getFieldSetting();

        newPathWayMax = new HashMap<>();
        newPathWay = new HashMap<>();

        checkedPosition = new HashMap<>();

        pathWayMax = new HashMap<>();
        pathWay = new HashMap<>();

        PathControl.getInstance().getPathWays().add(this);


    }


    private void loadCheckedPositionAndAdjacentNodes() {
        field.getMapExploreModel().getMapValues().forEach((key, value) -> {
            if (value.getWeight() != 0) {
                checkedPosition.put(key, new Node(value));
            }
        });

        switch (setting.getSetting().getGrid()) {
            case HexVertical:
                break;
            case HexHorizontal:
                break;
        }

        checkedPosition.forEach((s, integer) -> {
            Node node = checkedPosition.get(s);

            List<VectorMove> vectors;
            if (setting.getAreaY(s) % 2 == 0) {
                vectors = field.getVectors(0);
            } else vectors = field.getVectors(1);


            Set<Integer> way = vectors.stream().map(t ->
                    VectorAdjacent.getPositionVector(setting, s, t.getVectorX(), t.getVectorY())).collect(Collectors.toSet());


            Set<Node> neighboringNodes = new HashSet<>();
            way.forEach(t -> {
                Node curNode = checkedPosition.get(t);
                if (curNode != null) {
                    neighboringNodes.add(curNode);
                }
            });
            neighboringNodes.forEach(t -> Objects.requireNonNull(node).addNode(t, t.getWeight()));
        });


    }

    public boolean pathIsReady() {
        return fieldSequenceCounter == field.getSequenceCounter();
    }

    private void searchPath() {
        if (fieldSequenceCounter != field.getSequenceCounter()) {
            loadCheckedPositionAndAdjacentNodes();
            searchShortestPath();
            fieldSequenceCounter = field.getSequenceCounter();
        } else {
            try {
                synchronized (this) {
                    wait(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }




    }

    private void searchShortestPath() {
        Node nodeStart = checkedPosition.get(startCoordinate);
        if (nodeStart == null)
            return;
        Set<Node> visited = searchShortestWay(nodeStart);
        loadWayDistance(visited);


    }

    private Set<Node> searchShortestWay(Node startNode) {
        startNode.setDistance(0);
        Set<Node> visited = new HashSet<>();
        Queue<Node> unvisited = new PriorityQueue<>(Collections.singleton(startNode));

        while (!unvisited.isEmpty()) {
            Node curNode = unvisited.poll();
            if (Objects.requireNonNull(curNode).getDistance() > maxDistance) {
                continue;
            }
            curNode.getNeighboringNode().stream().filter(entry -> !visited.contains(entry)
                            && !field.isBusyNoActive(entry.getNodeName()))
                    .forEach(entry -> {
                        getDistanceAndShortestWay(entry, entry.getWeight(), curNode);
                        unvisited.add(entry);
                    });
            visited.add(curNode);
        }
        visited.remove(startNode);
        return visited;

    }

    private void loadWayDistance(Set<Node> visited) {
        newPathWay.clear();
        newPathWayMax.clear();
        visited.forEach(entry -> {
            if (entry.getDistance() <= distance) {
                newPathWay.put(entry.getNodeName(), entry);
            }
            newPathWayMax.put(entry.getNodeName(), entry);

        });
        pathWayMax = newPathWayMax;
        pathWay = newPathWay;


    }


    private void getDistanceAndShortestWay(Node neighboringNode, int weight, Node curNode) {
        int newDistance = curNode.getDistance() + weight;
        if (newDistance < neighboringNode.getDistance()) {
            neighboringNode.setDistance(newDistance);
            neighboringNode.setShortestWay(Stream.concat(curNode.getShortestWay().stream(), Stream.of(curNode)).collect(Collectors.toList()));
        }

    }


    @Override
    public void run() {
        while (objectExist) {
            searchPath();
        }
    }

    public boolean isAvailableCoordinate(int coordinate, boolean useMaxPath) {
        if (pathIsReady()) {
            if (useMaxPath) {
                return pathWayMax.containsKey(coordinate);
            } else return pathWay.containsKey(coordinate);
        }
        return false;
    }


    public void setCoordinate(int coordinate) {
        if (startCoordinate != coordinate) {
            this.startCoordinate = coordinate;
            fieldSequenceCounter = DefaultValue.PATHWAY_NO_INIT;
        }
    }

    public void setCoordinate(double positionX, double positionY) {
        if (startCoordinate != setting.getCoordinate(positionX, positionY)) {
            this.startCoordinate = setting.getCoordinate(positionX, positionY);
            fieldSequenceCounter = DefaultValue.PATHWAY_NO_INIT;
        }
    }

    public void setDistance(int maxDistance, int distance) {
        this.maxDistance = maxDistance;
        this.distance = distance;
        fieldSequenceCounter = DefaultValue.PATHWAY_NO_INIT;
    }

    public Node getNode(int coordinate, boolean useMaxPath) {
        if (useMaxPath) {
            return pathWayMax.get(coordinate);
        }
        return pathWay.get(coordinate);
    }

    public boolean isAvailableCoordinate(double positionX, double positionY, boolean useMaxPath) {
        return isAvailableCoordinate(Setting.getInstance().getFieldSetting().getCoordinate(positionX, positionY), useMaxPath);
    }

    public Node getNode(double positionX, double positionY, boolean useMaxPath) {
        return getNode(Setting.getInstance().getFieldSetting().getCoordinate(positionX, positionY), useMaxPath);

    }
}
