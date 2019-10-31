package com.demo.others.map.prim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * prim算法实现最小生成树
 * 注:用最小权重,走完图中所有的节点,不能重复走相同节点,最终生成树形结构并且总权重最小
 *
 * @author plum
 * 2018-07-01
 */
public class PrimMSTree {
    private static final Logger logger = LoggerFactory.getLogger(PrimMSTree.class);

    // 替代正无穷,即不可达到的远方
    private static final int I = Integer.MAX_VALUE;

    public static final Result prim(int[][] map) {
        Result result = new Result(map.length);

        // 选取0作为初始顶点
        int fromTop = 0;// 原顶点
        int toTop = 0;// 目标顶点
        int weight;// 边权重

        result.getTops().add(0);// 选定初始顶点
        result.getParents()[0] = -1;// 初始顶点没有父顶点

        // 直到寻找出所有顶点
        while (result.getTops().size() < map.length) {
            weight = I;// 重置权重
            // 在所有已寻找到的顶点中,寻找下一个最近顶点
            for (int top : result.getTops()) {// 前面找过的顶点依然会遍历,但是会被丢弃
                logger.info("当前top:{}", top);

                for (int i = 0; i < map.length; i++) {
                    // 已经找到的跳过
                    if (result.getTops().contains(i)) {
                        logger.info("当前节点已被找到:{}", i);
                        continue;
                    }

                    // 比较权重
                    if (map[top][i] < weight) {
                        fromTop = top;
                        toTop = i;
                        weight = map[top][i];

                        logger.info("从节点{}到达节点{},权重为{}", fromTop, toTop, weight);
                    }
                }
            }

            logger.info("从节点{}出发,寻找到最近节点{},最小权重为{}", fromTop, toTop, weight);

            // 每个剩余顶点都比较一遍,直到找到最小
            result.getTops().add(toTop);// 添加目标顶点
            result.getParents()[toTop] = fromTop;// 记录父顶点
        }

        return result;
    }

    public static void main(String[] args) {
        // 目标图的领接矩阵,表示点0,1,2,3,4和他们互相联通的权重
        int[][] map = new int[][]{
                //0, 1, 2, 3, 4
                {0, 4, 3, I, I},//0
                {4, 0, 8, 7, I},//1
                {3, 8, 0, I, 1},//2
                {I, 7, I, 0, 9},//3
                {I, I, 1, 9, 0} //4
        };

        Result result = prim(map);
        System.out.println(result);
    }
}
