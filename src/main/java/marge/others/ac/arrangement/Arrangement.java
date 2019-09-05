package marge.others.ac.arrangement;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 排列
 * <p>
 * 从 n 个不同的元素中取出 m（0≤m≤n）个不同的元素，按顺序排列.
 * m=n 这种特殊情况出现的时候，为全排列
 * 排列数=n!/(n-m)!
 * <p>
 * 以田忌赛马为例
 *
 * @author plum
 * 2018-09-04
 */
public class Arrangement {

    /**
     * 田忌的马
     * key-马标号，value-马
     */
    public static final Map<String, Integer> tMap = new HashMap<>(3, 1);
    public static final List<String> tList = new ArrayList<>(3);
    /**
     * 齐王的马
     */
    public static final Map<String, Integer> qMap = new HashMap<>(3, 1);
    public static final List<String> qList = new ArrayList<>(3);
    private static final Logger logger = LoggerFactory.getLogger(Arrangement.class);

    // 初始化
    static {
        tMap.put("t1", 1);
        tMap.put("t2", 3);
        tMap.put("t3", 5);
        tList.addAll(tMap.keySet());

        qMap.put("q1", 2);
        qMap.put("q2", 4);
        qMap.put("q3", 6);
        qList.addAll(qMap.keySet());
    }

    /**
     * 将田忌的马列表进行排列
     *
     * @param houses 当前的马匹列表
     * @param result 已经排列了的马匹列表
     */
    public static void arrangement(List<String> houses, List<String> result) {
        // 如果已经一组方案排列完成，进行这一组方案的比较
        if (houses.size() <= 0) {
            logger.info("出站方案：{}", JSONObject.toJSONString(result));
            compare(result);
            return;
        }

        for (int i = 0; i < houses.size(); i++) {
            // 马匹列表挑选一匹马，并生成新的镜像
            List<String> newHouses = new ArrayList<>(houses);
            newHouses.remove(i);

            // 已排列马匹列表添加新马，并生成镜像
            List<String> newResult = new ArrayList<>(result);
            newResult.add(houses.get(i));

            // 递归调用，进行排列推进
            arrangement(newHouses, newResult);
        }
    }

    /**
     * 田忌的马匹和齐王的马匹比较
     *
     * @param tHouses
     */
    public static void compare(List<String> tHouses) {
        int totalVin = 0;// 总胜场
        for (int i = 0; i < tHouses.size(); i++) {
            int tValue = tMap.get(tHouses.get(i));
            int qValue = qMap.get(qList.get(i));
            if (tValue > qValue) {
                totalVin++;
                if (totalVin > (tList.size() / 2)) {
                    logger.info("田忌胜");
                    return;
                }
            }
        }

        logger.info("齐王胜");
    }

    public static void main(String[] args) {
        arrangement(tList, new ArrayList<>());
    }
}
