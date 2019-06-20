package test.marge.fixamount;

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
    private List<Integer> result = new ArrayList<>();

    /**
     * 添加金额
     *
     * @param amount
     */
    public void add(Integer amount) {
        result.add(amount);
        Collections.sort(result);
    }

    /**
     * 添加全部
     *
     * @param lastResult
     */
    public void addAll(Result lastResult) {
        result.addAll(lastResult.getResult());
        Collections.sort(result);
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
            return this.result.toString().equals(r.result.toString());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return result.toString().hashCode();
    }

    @Override
    public String toString() {
        return result.toString();
    }

    public List<Integer> getResult() {
        return result;
    }
}
