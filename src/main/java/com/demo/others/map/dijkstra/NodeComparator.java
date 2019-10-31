package com.demo.others.map.dijkstra;

import java.util.Comparator;
import java.util.Map;

/**
 * 对minWeight的value进行排序
 */
public class NodeComparator implements Comparator<Map.Entry<Integer, Integer>> {
    @Override
    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
        if (o1.getValue() > o2.getValue()) {
            return 1;
        } else if (o1.getValue() < o2.getValue()) {
            return -1;
        }
        return 0;
    }
}
