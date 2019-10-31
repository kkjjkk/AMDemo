package com.demo.others.map.dijkstra;

import java.util.Comparator;
import java.util.Map;

public class MapNodeComparator implements Comparator<Map.Entry<MapNode, Integer>> {
    @Override
    public int compare(Map.Entry<MapNode, Integer> o1, Map.Entry<MapNode, Integer> o2) {
        if (o1.getValue() > o2.getValue()) {
            return 1;
        } else if (o1.getValue() < o2.getValue()) {
            return -1;
        }
        return 0;
    }
}
