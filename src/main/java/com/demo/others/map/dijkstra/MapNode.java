package com.demo.others.map.dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 地图节点
 */
public class MapNode {

    /**
     * 路径上的上一个节点
     */
    public MapNode lastNode;
    /**
     * 地图点
     */
    private String spot;
    /**
     * 相邻节点
     */
    private HashSet<MapNode> adjacentNodes;
    /**
     * 相邻节点权重，可以是相邻距离/到达时间等
     */
    private Map<String, Integer> weight;
    /**
     * 上个节点到start节点的总权重
     */
    private Integer lastWeight;

    public MapNode() {
    }

    public MapNode(String spot) {
        this.spot = spot;
        this.weight = new HashMap<>();
        this.adjacentNodes = new HashSet<>();
    }

    /**
     * 添加节点，并且赋予权重
     *
     * @param adjacentNode
     * @param nodeWeight
     * @return
     */
    public MapNode add(MapNode adjacentNode, Integer nodeWeight) {
        if (this.adjacentNodes == null) {
            this.setAdjacentNodes(new HashSet<>());
        }
        this.adjacentNodes.add(adjacentNode);

        if (this.weight == null) {
            this.setWeight(new HashMap<>());
        }
        this.weight.put(adjacentNode.getSpot(), nodeWeight);

        return this;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spotId) {
        this.spot = spot;
    }

    public HashSet<MapNode> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(HashSet<MapNode> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Map<String, Integer> getWeight() {
        return weight;
    }

    public void setWeight(Map<String, Integer> weight) {
        this.weight = weight;
    }

    /**
     * 从指定节点获取权重
     *
     * @param spot
     * @return
     */
    public Integer getNodeWeight(String spot) {
        Integer nodeWeight = 0;

        if (this.weight != null) {
            nodeWeight = this.weight.get(spot);
            if (nodeWeight == null) {
                nodeWeight = 0;
            }
        }

        return nodeWeight;
    }

    public MapNode getLastNode() {
        return lastNode;
    }

    public void setLastNode(MapNode lastNode) {
        this.lastNode = lastNode;
    }

    public Integer getLastWeight() {
        return lastWeight;
    }

    public void setLastWeight(Integer lastWeight) {
        this.lastWeight = lastWeight;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = super.equals(obj);

        if (!flag) {
            if (obj instanceof MapNode) {
                MapNode objUserNode = (MapNode) obj;
                if (this.spot.equals(objUserNode.getSpot())) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    @Override
    public int hashCode() {
        return this.spot.hashCode();
    }
}
