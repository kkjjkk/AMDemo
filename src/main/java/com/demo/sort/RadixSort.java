package com.demo.sort;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基数排序，在计数排序的基础上，进行逐层的拆分排序，即进行多次的计数排序，最终打到排序的目的
 * 1.有一个硬性要求，就是作为基础的计数排序阶段，必须是稳定排序，所以本次实现的计数排序部分会比基础版的计数排序更复杂
 * 2.用字符串排序为例子，演示基数排序的逐层推进操作
 */
public class RadixSort {

    private static final Logger logger = LoggerFactory.getLogger(RadixSort.class);

    /**
     * 基数排序
     *
     * @param list
     * @return
     */
    private static String[] radixSort(String[] list) {
        // 找出最长的字符数
        int maxSize = 0;
        for (String s : list) {
            if (s.length() > maxSize) {
                maxSize = s.length();
            }
        }

        logger.info("maxSize : {}", maxSize);

        // 临时数组，用于对每一位上的字母ASCII编码进行排序
        String[] temp = new String[list.length];// 临时拷贝数组
        // 需要做maxSize轮排序，因为采用的是末位补0的方式，所以从后面开始排序
        for (int i = maxSize - 1; i >= 0; i--) {
            int[] charList = new int[122 - 97 + 1 + 1];// ASCII的取值范围
            for (int j = 0; j < list.length; j++) {
                charList[getCharIndex(list[j], i)]++;
            }

            // 进行稳定版本的计数排序
            // 1.将charList的元素值变形，当前元素的值是前一个元素的值+当前值（代表了在当前位置之前，还有多少个元素）
            for (int j = 1; j < charList.length; j++) {
                charList[j] += charList[j - 1];
            }

            logger.info("{}：charList={}", i, charList);

            // 2.倒叙遍历list，将匹配的元素填到temp中
            for (int j = list.length - 1; j >= 0; j--) {
                int index = getCharIndex(list[j], i);
                int tempIndex = charList[index] - 1;// 计算出temp的坐标
                temp[tempIndex] = list[j];
                charList[index]--;
            }

            logger.info("{}：temp={}", i, temp);

            // 3.将第i轮排序的temp拷贝到list中，进行下一轮排序
            list = temp.clone();
        }

        return list;
    }

    /**
     * 获取字符串第k位字符所对应的ascii码序号
     *
     * @param str
     * @param k
     * @return
     */
    private static int getCharIndex(String str, int k) {
        //如果字符串长度小于k，直接返回0，相当于给不存在的位置补0
        if (str.length() < k + 1) {
            return 0;
        }
        return str.charAt(k) - 97 + 1;// 腾出一个位置给0补位
    }


    public static void main(String[] args) {
        String[] list = new String[]{"abc", "aa", "bc", "ddef", "zbc", "hk", "china", "mmp", "mdzz", "xml"};
        logger.info("oldList : {}", JSONObject.toJSONString(list));

        String[] result = radixSort(list);
        logger.info("newList : {}", JSONObject.toJSONString(result));
    }
}
