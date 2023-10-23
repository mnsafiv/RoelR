package ru.safonoviv.roelr.SearchWay;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.safonoviv.roelr.Map.Value.MapValue;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class Node implements Comparable<Node>, Serializable {
    private final int nodeName;
    private int distance = Integer.MAX_VALUE;
    private int weight;
    private final MapValue value;
    private List<Node> shortestWay = new LinkedList<>();
    private final Set<Node> neighboringNode = new HashSet<>();


    public Node(MapValue value) {
        this.nodeName = value.getCoordinate();
        this.weight = value.getWeight();
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return nodeName == node.nodeName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName);
    }

    public void addNode(Node node, int weight) {
        neighboringNode.add(node);
    }


    @Override
    public int compareTo(@NotNull Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

}
