package marge.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
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
     * @param list
     * @return
     */
    public static int[] margeSort(int[] list) {
        if (list == null) {
            return new int[]{};
        }

        // 已经拆到最小单位
        if (list.length <= 1) {
            return list;
        }
        // 拆分
        int size = list.length / 2;
        int[] list_1 = Arrays.copyOfRange(list, 0, size);
        int[] list_2 = Arrays.copyOfRange(list, size, list.length);

        list_1 = margeSort(list_1);
        list_2 = margeSort(list_2);

        // 归并
        int[] result = marge(list_1, list_2);

        return result;
    }

    /**
     * 合并排序
     *
     * @param list_1
     * @param list_2
     * @return
     */
    public static int[] marge(int[] list_1, int[] list_2) {
        if (list_1 == null || list_1.length == 0) {
            return list_2;
        }

        int indexR = 0;// result当前下标
        int index_1 = 0;
        int index_2 = 0;
        int[] result = new int[list_1.length + list_2.length];

        // 交叉对比
        while (index_1 < list_1.length && index_2 < list_2.length) {
            if (list_1[index_1] < list_2[index_2]) {
                result[indexR] = list_1[index_1];
                index_1++;
            } else {
                result[indexR] = list_2[index_2];
                index_2++;
            }

            indexR++;
        }

        // 剩余补充入
        if (index_1 < list_1.length) {
            for (int i = index_1; i < list_1.length; i++) {
                result[indexR] = list_1[i];
                indexR++;
            }
        } else {
            for (int i = index_2; i < list_2.length; i++) {
                result[indexR] = list_2[i];
                indexR++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] list = randomList(51);// 随机51个数
        int[] result = margeSort(list);

        logger.info("oldList : {}", list);
        logger.info("newList : {}", result);
    }
}
