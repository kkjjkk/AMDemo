package com.demo.others.ac.arrangement.prim;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * prim算法返回结果
 * 图最小生成树结果
 *
 * @author plum
 * 2019-07-01
 */
public class Result {

    // 图长
    private int size;

    // 顶点集合
    private List<Integer> tops;

    // 父节点集合
    private int[] parents;

    public Result(int size) {
        this.size = size;
        this.tops = new ArrayList();
        this.parents = new int[size];
    }

    public List<Integer> getTops() {
        return tops;
    }

    public int[] getParents() {
        return parents;
    }

    @Override
    public String toString() {
        return "tops : " + JSONObject.toJSONString(this.tops) + " - parants : " + JSONObject.toJSONString(this.parents);
    }
}
