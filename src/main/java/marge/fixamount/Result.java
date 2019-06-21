package marge.fixamount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 递归单词运行的结果
 *
 * @author plum
 * 2019-06-20
 */
public class Result {

    /**
     * 用于排序的结果
     */
    private List<Integer> resultList = null;

    public Result() {
        resultList = new ArrayList<>();
    }

    /**
     * 添加金额
     *
     * @param lastResult 上一个统计结果
     * @param money     本次追加的金额
     */
    public void add(Result lastResult, Integer money) {
        resultList.addAll(lastResult.getResultList());
        resultList.add(money);
        Collections.sort(resultList); // 排序
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof Result) {
            Result r = (Result) obj;
            return this.resultList.toString().equals(r.resultList.toString());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return resultList.toString().hashCode();
    }

    @Override
    public String toString() {
        return resultList.toString();
    }

    public List<Integer> getResultList() {
        return resultList;
    }
}
