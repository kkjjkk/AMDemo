package com.demo.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 归并排序
 *
 * @author plum
 * 2019-07-05
 */
public class MargeSort {

    private static final Logger logger = LoggerFactory.getLogger(MargeSort.class);

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

    /**
     * 归并排序
     *
     * @param list  数组
     * @param start 数组开始位置
     * @param end   数组结束位置
     * @return
     */
    public static int[] margeSort(int[] list, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            margeSort(list, start, mid);
            margeSort(list, mid + 1, end);

            marge(list, start, end, mid);
        }

        return list;
    }

    /**
     * 合并排序
     *
     * @param list  数组
     * @param start 数组开始位置
     * @param end   数组结束位置
     * @param mid   数组中位
     */
    public static void marge(int[] list, int start, int end, int mid) {
        // 临时数组
        int[] temp = new int[end - start + 1];

        // 比较数组的两边进行排序
        int leftStart = start, rightStart = mid + 1, tempIndex = 0;
        while (leftStart <= mid && rightStart <= end) {
            if (list[leftStart] <= list[rightStart]) {
                temp[tempIndex++] = list[leftStart++];
            } else {
                temp[tempIndex++] = list[rightStart++];
            }
        }

        // 剩余数据依次填充
        if (leftStart <= mid) {
            while (leftStart <= mid) {
                temp[tempIndex++] = list[leftStart++];
            }
        } else if (rightStart <= end) {
            while (rightStart <= end) {
                temp[tempIndex++] = list[rightStart++];
            }
        }

        // temp数组拷贝回list数组中
        for (int i : temp) {
            list[start++] = i;
        }
    }

    public static void main(String[] args) {
        int[] list = randomList(51);// 随机51个数
        logger.info("oldList : {}", list);

        int[] result = margeSort(list, 0, list.length - 1);
        logger.info("newList : {}", result);
    }
}
