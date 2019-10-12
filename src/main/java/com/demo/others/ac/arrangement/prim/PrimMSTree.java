package com.demo.others.ac.arrangement.prim;

/**
 * prim算法实现最小生成树
 *
 * @author plum
 * 2018-07-01
 */
public class PrimMSTree {

    // 替代正无穷，即不可达到的远方
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
            // 在所有已寻找到的顶点中，寻找下一个最近顶点
            for (int top : result.getTops()) {
                for (int i = 0; i < map.length; i++) {
                    // 已经找到的跳过
                    if (result.getTops().contains(i)) {
                        continue;
                    }

                    // 比较权重
                    if (map[top][i] < weight) {
                        fromTop = top;
                        toTop = i;
                        weight = map[top][i];
                    }
                }
            }

            // 每个剩余顶点都比较一遍，直到找到最小
            result.getTops().add(toTop);// 添加目标顶点
            result.getParents()[toTop] = fromTop;// 记录父顶点
        }

        return result;
    }

    public static void main(String[] args) {
        // 目标图的领接矩阵
        int[][] map = new int[][]{
                {0, 4, 3, I, I},
                {4, 0, 8, 7, I},
                {3, 8, 0, I, 1},
                {I, 7, I, 0, 9},
                {I, I, 1, 9, 0}
        };

        Result result = prim(map);
        System.out.println(result);
    }
}
