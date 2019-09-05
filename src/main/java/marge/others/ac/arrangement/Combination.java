package marge.others.ac.arrangement;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合
 * <p>
 * 从 n 个不同元素中取出 m（0≤m≤n）个不同的元素，组合是不考虑顺序的
 * 对于所有 m 取值的组合之全集合，我们可以叫作全组合
 * <p>
 * 组合数值=(n! / (n-m)!) / m!
 * 全组合数值=2^n
 *
 * @author plum
 * 2018-09-04
 */
public class Combination {

    private static final Logger logger = LoggerFactory.getLogger(Combination.class);

    /**
     * 组合
     *
     * @param list   需要组合的列表
     * @param result 组合后的结果集
     * @param m      排列的步长(0≤m≤n)
     */
    public static void combination(List<Integer> list, List<Integer> result, int m) {
        // 如果组合结果的长度已经达到要求，返回
        if (result.size() == m) {
            logger.info("组合结果：{}", JSONObject.toJSONString(result));
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            // 马匹列表挑选一匹马，并生成新的镜像
            List<Integer> newlist = new ArrayList<>(list.subList(i + 1, list.size()));// 因为组合不考虑顺序，所以之前的元素都不用再重复组合了

            // 已排列马匹列表添加新马，并生成镜像
            List<Integer> newResult = new ArrayList<>(result);
            newResult.add(list.get(i));

            combination(newlist, newResult, m);
        }
    }

    public static void main(String[] args) {
        combination(Arrays.asList(new Integer[]{1, 2, 3}), new ArrayList<>(), 2);
    }
}
