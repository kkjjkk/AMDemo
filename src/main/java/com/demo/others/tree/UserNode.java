package com.demo.others.tree;

import java.util.HashSet;

/**
 * 用户节点
 */
public class UserNode {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 朋友节点
     */
    private HashSet<UserNode> friends;

    /**
     * 度（层，朋友亲密度）
     */
    private Integer degree;

    public UserNode() {
    }

    public UserNode(Integer userId) {
        this.userId = userId;
        this.friends = new HashSet<>();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public HashSet<UserNode> getFriends() {
        return friends;
    }

    public void setFriends(HashSet<UserNode> friends) {
        this.friends = friends;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = super.equals(obj);

        if (!flag) {
            if (obj instanceof UserNode) {
                UserNode objUserNode = (UserNode) obj;
                if (this.userId.equals(objUserNode.getUserId())) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    @Override
    public int hashCode() {
        return this.userId.hashCode();
    }
}
