package com.demo.others.map.dijkstra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Dijkstra算法，用作计算地图两点之间最短距离/最少时间等最值问题
 * 注：
 * 1.从初始节点s开始，进行广度优先搜索（但是这里需要将同一层的节点进行权重排序，按顺序广度优先搜索）
 * 2.用一个权重距离列表minWeight记录开始节点s到达每个节点的最短距离，遍历到一个节点就记录一次
 * 3.遍历到了不同通路的相同节点，已经遍历过加入了finishList就跳过
 * 4.最终遍历完所有节点，并且得到一个minWeight列表，里面有开始节点到达每一个节点的最短距离
 */
public class Dijkstra {

    private static final Logger logger = LoggerFactory.getLogger(Dijkstra.class);

    /**
     * 找出任意起始点到任意一点的最短距离
     *
     * @param start
     * @param node
     * @return
     */
    public static Integer getMinWeight(MapNode start, MapNode node) {
        if (start.equals(node)) {
            return 0;// 自己到自己的最小权重为0
        }

        Map<String, Integer> minWeight = new HashMap<>();// 存放start到达每个节点的最小权重
        Set<MapNode> finishSet = new HashSet<>();// 已完成的数组

        // 遍历所有节点，更新start节点到达所有节点的最小权重
        Queue<MapNode> queue = new LinkedList<>();
        queue.offer(start);
        forEatchNodes(queue, minWeight, finishSet);

        // 获取所需到达的节点最小权重
        Integer result = minWeight.get(node.getSpot());

        return result;
    }

    /**
     * 根据最小权重，遍历所有节点，并计算更新start到达每个节点的最小权重
     *
     * @param queue
     * @param minWeight
     * @param finishSet
     */
    private static void forEatchNodes(Queue<MapNode> queue, Map<String, Integer> minWeight, Set<MapNode> finishSet) {
        while (!queue.isEmpty()) {
            MapNode node = queue.poll();

            // 因为在遍历节点之前，已经对每层的节点进行过排序，如果再次遍历到，说明在此通路上的节点效率一定比之前遍历到更低
            if (finishSet.contains(node)) {
                continue;
            }

            logger.info("当前遍历节点{}", node.getSpot());

            // 计算当前路径下该节点的权重
            MapNode lastNode = node.getLastNode();
            Integer nodeWeight = 0;
            if (lastNode != null) {
                nodeWeight = lastNode.getNodeWeight(node);
            }
            Integer weight = (node.getLastWeight() != null ? node.getLastWeight() : 0) + nodeWeight;

            // 只要遍历到本节点，就加入已完成节点裂变
            finishSet.add(node);

            // 打印一下通路
            StringBuilder accessRoad = new StringBuilder(node.getSpot());
            while (lastNode != null) {
                accessRoad.append(lastNode.getSpot());
                lastNode = lastNode.getLastNode();
            }
            logger.info("当前通路：{}", accessRoad.toString());

            // 遍历相邻节点
            Map<MapNode, Integer> adjacents = node.getAdjacentNodes();
            if (adjacents != null || !adjacents.isEmpty()) {
                // 根据当前节点到达相邻节点的距离排序
                List<Map.Entry<MapNode, Integer>> tempList = new ArrayList<>(adjacents.entrySet());
                Collections.sort(tempList, new MapNodeComparator());
                for (Map.Entry<MapNode, Integer> mapNodeIntegerEntry : tempList) {
                    MapNode adjacent = mapNodeIntegerEntry.getKey();
                    // 记录暂时的数据，后续可能更新掉
                    adjacent.setLastNode(node);
                    minWeight.put(adjacent.getSpot(), weight + node.getNodeWeight(adjacent));
                    adjacent.setLastWeight(weight);

                    // 加入队尾
                    queue.offer(adjacent);
                }
            }
        }
    }

    /**
     * 初始化一张图
     *
     * @return
     */
    public static MapNode initMap() {
        MapNode s = new MapNode("s");// 初始节点权重为0
        MapNode a = new MapNode("a");
        MapNode b = new MapNode("b");
        MapNode c = new MapNode("c");
        MapNode d = new MapNode("d");
        MapNode e = new MapNode("e");
        MapNode f = new MapNode("f");
        MapNode g = new MapNode("g");
        MapNode h = new MapNode("h");

        s.add(a, 5);
        s.add(b, 3);
        s.add(c, 2);
        s.add(d, 4);

        a.add(e, 3);
        b.add(a, 2);
        b.add(f, 1);
        c.add(f, 4);
        c.add(h, 8);
        d.add(c, 1);
        d.add(h, 6);

        e.add(g, 1);
        f.add(e, 1);
        f.add(h, 2);

        h.add(g, 4);

        return s;
    }

    public static void main(String[] args) {
        MapNode s = initMap();
        MapNode g = new MapNode("g");

        Integer result = getMinWeight(s, g);

        logger.info("起始点{}到点{}的最短通路距离是：{}", s.getSpot(), g.getSpot(), result);
    }
}
