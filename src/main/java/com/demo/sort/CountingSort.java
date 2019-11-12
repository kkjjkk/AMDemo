package com.demo.sort;

import com.demo.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计数排序，一个标准的空间复杂度换时间复杂度的排序算法
 * 1.适用数据离散度比较密集的排序，如果太松散，就很浪费内存
 * 2.如果不需求稳定排序（元素顺序不能错乱），可以采用基础版的计数排序
 * 3.我们这里只实现一个基础版的计数排序，更好的实现请参考基数排序中的实现
 */
public class CountingSort {

    private static final Logger logger = LoggerFactory.getLogger(CountingSort.class);

    /**
     * 计数排序
     *
     * @param list
     * @return
     */
    private static int[] countingSort(int[] list) {
        // 计算List数组最大元素和最小元素的差值
        int min = 0;
        int max = 0;

        for (int i : list) {
            if (i < min) {
                min = i;
            } else if (i > max) {
                max = i;
            }
        }

        logger.info("min : {}, max : {}", min, max);

        // 生成辅助数组
        int[] temp = new int[max - min + 1];

        // 利用temp对list的元素进行计数存储
        for (int i : list) {
            temp[i]++;
        }

        logger.info("   temp : {}", temp);

        // 根据temp的计数，将数据导入list中
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            int num = temp[i];// 代表list的i元素出现了几次
            if (num > 0) {
                for (int j = num; j > 0; j--) {
                    list[index] = i;
                    index++;
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[] list = CommonUtils.randomList(20);// 随机20个数
        logger.info("oldList : {}", list);

        int[] result = countingSort(list);
        logger.info("newList : {}", result);
    }
}
