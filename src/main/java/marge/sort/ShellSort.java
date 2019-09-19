package marge.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 希尔排序，进阶版插入排序
 *
 * @author plum
 * 2019-08-12
 */
public class ShellSort {

    private static final Logger logger = LoggerFactory.getLogger(ShellSort.class);

    /**
     * 排序
     *
     * @param list
     * @param shellSize 希尔值
     * @return
     */
    public static final void sort(int[] list, int shellSize) {
        if (list.length > 1) {
            while (shellSize >= 1) {
                // 按照希尔值分组粗排
                for (int i = 0; i < shellSize; i++) {
                    // 每组内粗排
                    for (int j = i + shellSize; j < list.length; j += shellSize) {// 当前比较的元素
                        int temp = list[j];

                        int k = j - shellSize;
                        for (; k >= 0 && list[k] > temp; k -= shellSize) {// 有序区比较
                            list[k + shellSize] = list[k];
                        }

                        list[k + shellSize] = temp;// 替换
                    }
                }

                shellSize = shellSize / 2;
            }
        }
    }

    public static void main(String[] args) {
        int list[] = {12, 1, 3, 46, 5, 0, -3, 12, 35, 16};
        logger.info("sort before : {}", list);
        sort(list, 4);
        logger.info("sort after : {}", list);
    }
}
