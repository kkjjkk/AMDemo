package com.demo.others.map.dijkstra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Dijkstra算法,用作计算地图两点之间最短距离/最少时间等最值问题
 * 注:
 * 1.从初始节点s开始,进行广度优先搜索（但是这里需要将同一层的节点进行权重排序,按顺序广度优先搜索）
 * 2.用一个权重距离列表minWeight记录开始节点s到达每个节点的最短距离,遍历到一个节点就记录一次
 * 3.遍历到了不同通路的相同节点,已经遍历过加入了finishList就跳过
 * 4.最终遍历完所有节点,并且得到一个minWeight列表,里面有开始节点到达每一个节点的最短距离
 */
public class DijkstraTest {

    private static final Logger logger = LoggerFactory.getLogger(DijkstraTest.class);

    // 替代正无穷,即不可达到的远方
    private static final int I = Integer.MAX_VALUE;

    private static final NodeComparator nodeComparator = new NodeComparator();// 比较器

    /**
     * 计算起点到相应节点的最小权重
     *
     * @param nodeIndex
     * @param map
     * @return
     */
    public static Integer dijkstra(int nodeIndex, int[][] map) {
        Integer result = null;

        List<Integer> finish = new LinkedList<>();// 用作记录已经完成的节点
        Map<Integer, Integer> minWeightTemp = new LinkedHashMap<>();// s点到达每个节点的最小权重
        Map<Integer, Integer> minWeight = new LinkedHashMap<>();// 已确定的mw
        minWeightTemp.put(0, 0);// 起始点
        minWeight.put(0, 0);

        while (finish.size() < map.length) {
            // 寻找minWeight最小值
            Map.Entry<Integer, Integer> minNode = findGeoWithMinWeight(minWeightTemp);
            minWeightTemp.remove(minNode.getKey());// 移除
            logger.info("当前最小nw节点:{}", minNode.getKey());

            if (finish.contains(minNode.getKey())) {// 过滤已完成
                logger.info("节点{}已完成", minNode.getKey());
                continue;
            } else {
                finish.add(minNode.getKey());// 结束一个点的遍历
            }

            // 更新minWeight最小节点相邻节点的minWeight值
            updateWeight(minNode, minWeightTemp, minWeight, map);
        }

        result = minWeight.get(nodeIndex);
        return result;
    }

    /**
     * 寻找minWeight最小值
     *
     * @param minWeightTemp
     * @return
     */
    public static Map.Entry<Integer, Integer> findGeoWithMinWeight(Map<Integer, Integer> minWeightTemp) {
        List<Map.Entry<Integer, Integer>> minList = new LinkedList<>(minWeightTemp.entrySet());
        // 排序
        Collections.sort(minList, nodeComparator);
        return minList.get(0);
    }

    /**
     * 更新minWeight最小节点相邻节点的minWeight值
     *
     * @param minNode
     * @param minWeightTemp
     * @param minWeight
     * @param map
     */
    public static void updateWeight(Map.Entry<Integer, Integer> minNode, Map<Integer, Integer> minWeightTemp, Map<Integer, Integer> minWeight, int[][] map) {
        Integer lastNodeIndex = minNode.getKey();
        Integer lastMinWeight = minNode.getValue();// 上一节点的最小minWeight
        int[] nodes = map[minNode.getKey()];

        for (int i = 0; i < nodes.length; i++) {
            if (lastNodeIndex == i) {// 过滤自己
                continue;
            }
            if (nodes[i] == I) {// 过滤不可达
                continue;
            }

            Integer oldMinWeight = minWeightTemp.get(i);// 旧的最小权重
            Integer newMinWeight = lastMinWeight + nodes[i];
            if (oldMinWeight == null || newMinWeight < oldMinWeight) {
                minWeightTemp.put(i, newMinWeight);
                minWeight.put(i, newMinWeight);
            }

            logger.info("更新节点{}:lastMinWeight={},oldMinWeight={}, newMinWeight={}", i, lastMinWeight, oldMinWeight, newMinWeight);
        }
    }

    public static void main(String[] args) {
        // 构建图,设有s(起始点),a,b,c,d,e,f,g,h各点,他们的连通图和权重如下
        int[][] map = new int[][]{
                //s, a, b, c, d, e, f, g, h
                {0, 5, 3, 2, 4, I, I, I, I},//s
                {I, 0, I, I, I, 3, I, I, I},//a
                {I, 2, 0, I, I, I, 1, I, I},//b
                {I, I, I, 0, I, I, 4, I, 8},//c
                {I, I, I, 1, 0, I, I, I, 6},//d
                {I, I, I, I, I, 0, I, 1, I},//e
                {I, I, I, I, I, 1, 0, I, 2},//f
                {I, I, I, I, I, I, I, 0, I},//g
                {I, I, I, I, I, I, I, 4, 0},//h
        };

        Integer nodeIndex = 7;// g
        Integer result = dijkstra(7, map);

        logger.info("节点0到达节点{}的最小权重是:{}", nodeIndex, result);
    }
}
