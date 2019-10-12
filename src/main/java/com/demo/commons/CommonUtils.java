package com.demo.commons;

import java.util.Random;

/**
 * 一些公共工具，后期再细分
 */
public class CommonUtils {

    private static final Random random = new Random();

    /**
     * 生成一个固定长度的随机数组
     *
     * @param size
     * @return
     */
    public static int[] randomList(int size) {
        if (size <= 0) {
            return new int[]{};
        }

        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = random.nextInt(size);
        }

        return result;
    }
}
