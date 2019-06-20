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
     * @param amount
     */
    public void add(Integer amount) {
        resultList.add(amount);
        Collections.sort(resultList);
    }

    /**
     * 添加全部
     *
     * @param lastResult
     */
    public void addAll(Result lastResult) {
        resultList.addAll(lastResult.getResultList());
        Collections.sort(resultList);
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
