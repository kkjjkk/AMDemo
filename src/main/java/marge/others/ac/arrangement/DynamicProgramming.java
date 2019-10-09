package marge.others.ac.arrangement;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态规划
 * <p>
 * 概念：通过不断分解问题，可以将复杂的问题分解为最基本的最小问题单元，在每一步的局部求解中，找到最优的局部节，最终路由到整体最优解
 * 关键：最重要的是从一个子问题转移到另一个子问题的状态变化，即找到状态转换方程
 * <p>
 * 例：有面额2元，3元，7元的3种货币，用最少的货币数量凑齐100元整
 *
 * @author plum
 * 2018-09-18
 */
public class DynamicProgramming {

    private static final Logger logger = LoggerFactory.getLogger(DynamicProgramming.class);

    /**
     * 面额
     */
    private static final int[] dens = {2, 3, 7};

    /**
     * 动态规划
     *
     * @param totalAmount 总面额
     */
    public static void dynamicProgramming(int totalAmount) {
        if (totalAmount <= 0) {
            logger.error("总额必须大于0");
            return;
        }

        /*
         * 构建一个二维数组
         * 行代表总额值
         * 列代表面额值，最后一列代表最小货币组合数
         */
        int[][] table = new int[totalAmount + 1][dens.length + 1];

        for (int j = 1; j <= totalAmount; j++) {
            Integer min = null;
            for (int i = 0; i < dens.length; i++) {
                int money = dens[i];// 当前面额
                // 如果当前面额-当前货币面额在之前的结果中能找到值，说明此方案可行
                if (j - money >= 0) {
                    /*
                     * 上一个匹配面额计算出来的最小值，
                     * 此处就是动态规划的核心思想，每一步都要通过上一步的最优解来进行递推
                     */
                    int lastMinCount = table[j - money][dens.length];
                    // 填充当前面额货币的货币数量
                    int nowCount = lastMinCount + 1;
                    table[j][i] = nowCount;
                    if (min == null) {
                        min = nowCount;
                    } else if (nowCount < min) {
                        min = nowCount;
                    }
                }
            }
            // 填充最小货币数
            if (min != null) {
                table[j][dens.length] = min;
            }

            logger.info("面额：{} {}", j, JSON.toJSONString(table[j]));
        }

        logger.info("达到总额为：{}时，需要最少货币数为：{}", totalAmount, table[totalAmount][dens.length]);
    }

    public static void main(String[] args) {
        dynamicProgramming(100);// 求100的总面额
    }
}
