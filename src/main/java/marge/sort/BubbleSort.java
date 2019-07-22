package marge.sort;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 冒泡排序，最基础的排序之一
 *
 * @author plum
 * 2019-07-22
 */
public class BubbleSort {

    private static final Logger logger = LoggerFactory.getLogger(BubbleSort.class);

    /**
     * 排序
     *
     * @param list
     * @return
     */
    public static final void sort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    int temp = list[j];
                    list[j] = list[i];
                    list[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 2, 1, 5, 6, 7, 8, 30, 50, 1, 33, 24, 5, -4, 7, 0};
        logger.info("sort before : {}", list);
        sort(list);
        logger.info("sort after : {}", list);
    }

}
