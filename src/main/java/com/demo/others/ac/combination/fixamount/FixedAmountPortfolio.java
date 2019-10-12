package com.demo.others.ac.combination.fixamount;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 固定金额组合测试
 * 有[1, 2, 5, 10]四种面额的钱，用组合的方式凑成10元整，有多少种组合方式（去重）
 *
 * @author plum
 * 2018-06-20
 */
public class FixedAmountPortfolio {

    private static final Logger logger = LoggerFactory.getLogger(FixedAmountPortfolio.class);

    /**
     * 结果去重
     */
    private static final Set<Result> resultSet = new LinkedHashSet<>();

    private static final Integer[] money = new Integer[]{1, 2, 5, 10};

    /**
     * 递归方式逐步扣减金额，并且记录扣减的组合
     *
     * @param totalAmount 总金额
     * @param lastResult  上一个执行结果
     * @return
     */
    public static void get(Integer totalAmount, Result lastResult) {
        // 跳出条件
        if (totalAmount == 0) {
            resultSet.add(lastResult);
        } else {
            // 扣成复数说明这个方案失败
            if (totalAmount > 0) {
                for (Integer m : money) {
                    Result result = new Result();
                    result.add(lastResult, m);

                    // 递归
                    get(totalAmount - m, result);
                }
            }
        }
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) {
        get(10, new Result());

        logger.info("size = {}", resultSet.size());
        for (Result result : resultSet) {
            logger.info("result = {}", result);
        }
    }


}
