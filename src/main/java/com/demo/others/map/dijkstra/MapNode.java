package com.demo.others.map.dijkstra;

import java.util.HashMap;
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
     * key-节点
     * value-距离相邻节点的权重
     */
    private Map<MapNode, Integer> adjacentNodes;

    /**
     * 上个节点到start节点的总权重
     */
    private Integer lastWeight;

    public MapNode() {
    }

    public MapNode(String spot) {
        this.spot = spot;
        this.adjacentNodes = new HashMap<>();
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
            this.setAdjacentNodes(new HashMap<>());
        }
        this.adjacentNodes.put(adjacentNode, nodeWeight);

        return this;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spotId) {
        this.spot = spot;
    }

    public Map<MapNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<MapNode, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    /**
     * 从指定节点获取权重
     *
     * @param node
     * @return
     */
    public Integer getNodeWeight(MapNode node) {
        Integer nodeWeight = 0;

        if (this.adjacentNodes != null) {
            nodeWeight = this.adjacentNodes.get(node);
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
