package com.demo.leetcodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 两个升序数组，求出两个数组（归并之后）的中位数，
 * 需要考虑时间复杂度和空间复杂对相对较低
 *
 * @author plum
 * 2019-08-21
 */
public class TwoListMedian {

    private static final Logger logger = LoggerFactory.getLogger(TwoListMedian.class);

    /**
     * 排序
     *
     * @param list1
     * @param list2
     * @return
     */
    public static final double find(int[] list1, int[] list2) {
        // 入参可能需要交换顺序，所以，我们内部规定，A是较短的数组，B是较长的数组
        int[] a, b = null;
        if (list1.length > list2.length) {
            a = list2;
            b = list1;
        } else {
            a = list1;
            b = list2;
        }

        /*
         * a和b的合集，可以划分出左边和右边，这两部分都是由a和b的某部分组成，设a和b的最终合集左左分界线的index是i和j，a和b的总长度是m和n
         * 那么有以下规律
         * 1.m+n是奇数：i+j = (m+n+1)/2，median=MAX(a[i-1], b[j-1])
         * 2.m+n是偶数：i+j = (m+n)/2，median=(MAX(a[i-1], b[j-1]) + MIN(a[i], b[j]))/2
         */

        int m = a.length;// a的长度，之所以不用lenth-1，是因为下面计算i的公式(start + end) / 2会自动截断小数位，相当于缩小了数组index范围
        int n = b.length;// b的长度

        // 游标
        int start = 0;
        int end = m;
        int mid = (m + n + 1) / 2;// 不论奇数还是偶数，除不尽都会自动截断小数位，所以+1可以不用考虑奇数还是偶数的中位index计算方式不同的问题

        int i = 0;// a的整体左半部分集合index
        int j = 0;// b的整体左半部分集合index

        // 从数组长度较小的a数组开始二分查找
        while (start <= end) {
            i = (start + end) / 2;
            j = mid - i;// j的值是伴随i的值浮动的
            if (i < end && a[i] < b[j - 1]) {// a的右部第一个元素小于b的左部最后一个元素，说明i值偏小了
                start = i + 1;
            } else if (i > start && a[i - 1] > b[j]) {// a的左部最后一个元素大于b的右部第一个元素，说明i值偏大了
                end = i - 1;
            } else {// 寻找到刚好合适的i值
                int maxLeft = 0;
                int minRight = 0;

                if (i == 0) {// a的所有元素都大于b
                    maxLeft = b[j - 1];
                } else if (j == 0) {// b的所有元素都大于a
                    maxLeft = a[i - 1];
                } else {
                    maxLeft = Math.max(a[i - 1], b[j - 1]);
                }

                if ((m + n) % 2 == 1) {// 奇数长度
                    return maxLeft;
                } else {// 偶数长度
                    if (i == m) {// a的所有元素都大于b
                        minRight = b[j];
                    } else if (j == n) {// b的所有元素都大于a
                        minRight = a[i];
                    } else {
                        minRight = Math.min(a[i], b[j]);
                    }

                    return (maxLeft + minRight) / 2;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] list1 = new int[]{3, 5, 6, 7, 8, 12, 20};
        int[] list2 = new int[]{1, 10, 17, 18};
        double median = find(list1, list2);

        logger.info("list1 : {}", list1);
        logger.info("list2 : {}", list2);
        logger.info("奇数median : {}", median);

        list1 = new int[]{3, 5, 6, 7, 8, 12, 20};
        list2 = new int[]{1, 10, 17, 18, 24};
        median = find(list1, list2);

        logger.info("list1 : {}", list1);
        logger.info("list2 : {}", list2);
        logger.info("偶数median : {}", median);

        list1 = new int[]{1, 2, 3, 4, 5};
        list2 = new int[]{6, 7, 8, 9};
        median = find(list1, list2);

        logger.info("list1 : {}", list1);
        logger.info("list2 : {}", list2);
        logger.info("极端(a<b)median : {}", median);

        list1 = new int[]{6, 7, 8, 9};
        list2 = new int[]{1, 4, 5};
        median = find(list1, list2);

        logger.info("list1 : {}", list1);
        logger.info("list2 : {}", list2);
        logger.info("极端(a>b)median : {}", median);
    }
}
