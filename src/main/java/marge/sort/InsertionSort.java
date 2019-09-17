package marge.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 插入排序（快速排序）
 *
 * @author plum
 * 2019-08-12
 */
public class InsertionSort {

    private static final Logger logger = LoggerFactory.getLogger(InsertionSort.class);

    /**
     * 排序
     *
     * @param list
     * @return
     */
    public static final void sort(int[] list) {
        if (list.length > 1) {
            // 从第二个元素开始,需要进行size-1轮
            for (int i = 1; i < list.length; i++) {
                int now = list[i];// 备份当前元素

                // 有序区比较
                int j = i - 1;
                // 复制
                for (; j >= 0; j--) {
                    if (list[j] > now) {
                        list[j + 1] = list[j];
                    } else {
                        break;// 已经寻找到了应该所在的位置
                    }
                }

                list[j + 1] = now;// 替换
            }
        }
    }

    public static void main(String[] args) {
        int list[] = {12, 1, 3, 46, 5, 0, -3, 12, 35, 16};
        logger.info("sort before : {}", list);
        sort(list);
        logger.info("sort after : {}", list);
    }
}
