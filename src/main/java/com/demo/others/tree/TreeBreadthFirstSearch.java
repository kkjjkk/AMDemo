package com.demo.others.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 树的广度优先搜索，我们以用户的
 */
public class TreeBreadthFirstSearch {

    private static final Logger logger = LoggerFactory.getLogger(TreeBreadthFirstSearch.class);

    /**
     * 双向搜索
     *
     * @param userA
     * @param userB
     * @return
     */
    public static Integer BidirectionalSearch(UserNode userA, UserNode userB) {
        Integer resultDegree = 0;

        Queue<UserNode> queueA = new LinkedList<>();
        queueA.offer(userA);
        Queue<UserNode> queueB = new LinkedList<>();
        queueB.offer(userB);

        // 已访问节点
        Set<UserNode> visitedA = new HashSet<>();
        visitedA.add(userA);
        Set<UserNode> visitedB = new HashSet<>();
        visitedB.add(userB);

        // 设置一个总阈值，一般人用户好友层级数不会超过6度（详细可以参考资料，主要因为每个人的好友数平均在100左右，6度已经超过全球人口总数）
        Integer maxDegree = 12;// 每个人6度，总长度就是12

        Integer degreeA = 1;
        Integer degreeB = 1;

        while (degreeA + degreeB <= maxDegree) {
            boolean hasNextA = getFriends(queueA, visitedA, degreeA);
            boolean hasNextB = getFriends(queueB, visitedB, degreeB);

            if (!hasNextA && !hasNextB) {
                logger.info("用户：{}和用户：{}不存在共同好友", userA.getUserId(), userB.getUserId());
                break;
            }

            // 是否存在交集
            resultDegree = isIntersection(visitedA, visitedB);
            if (resultDegree > 0) {
                logger.info("用户：{}和用户：{}存在共同好友", userA.getUserId(), userB.getUserId());
                break;
            } else {
                if (hasNextA) {
                    degreeA++;
                }
                if (hasNextB) {
                    degreeB++;
                }
            }
        }

        return resultDegree;
    }

    /**
     * 按层扩展用户好友
     *
     * @param queue
     * @param visite
     * @param degree
     */
    private static boolean getFriends(Queue<UserNode> queue, Set<UserNode> visite, Integer degree) {
        boolean flag = false; // 是否还有下一度好友
        List<UserNode> temp = new LinkedList<>();
        while (!queue.isEmpty()) {
            UserNode user = queue.poll();
            HashSet<UserNode> friends = user.getFriends();
            if (friends != null && !friends.isEmpty()) {
                for (UserNode friend : friends) {
                    if (visite.contains(friend)) {
                        continue;
                    }
                    friend.setDegree(degree);
                    temp.add(friend);
                }
            }
        }

        if (!temp.isEmpty()) {
            for (UserNode userNode : temp) {
                queue.offer(userNode);
            }
            flag = true;
            visite.addAll(temp);
        }

        return flag;
    }

    /**
     * 是否存在交集
     *
     * @param visitedA
     * @param visitedB
     * @return
     */
    private static Integer isIntersection(Set<UserNode> visitedA, Set<UserNode> visitedB) {
        Integer degree = 0;
        // 先用粗糙的方式做交集吧，后期优化
        for (UserNode userNodeA : visitedA) {
            for (UserNode userNodeB : visitedB) {
                if (userNodeA.equals(userNodeB)) {
                    degree = userNodeA.getDegree() + userNodeB.getDegree();
                    logger.info("共同好友：{}，分别是{}度和{}度", userNodeA.getUserId(), userNodeA.getDegree(), userNodeB.getDegree());
                    break;
                }
            }
        }

        return degree;
    }

    public static void main(String[] args) {
        // 生成用户树，先简单处理，后续可升级
        UserNode userA = new UserNode(31L);
        UserNode userA1 = new UserNode(100L);
        UserNode userA2 = new UserNode(4001L);
        UserNode userA3 = new UserNode(520L);
        UserNode userA4 = new UserNode(19L);
        userA.getFriends().add(userA1);
        userA1.getFriends().add(userA2);
        userA1.getFriends().add(userA3);
        userA3.getFriends().add(userA4);

        UserNode userB = new UserNode(37L);
        UserNode userB1 = new UserNode(101L);
        UserNode userB2 = new UserNode(401L);
        UserNode userB3 = new UserNode(5230L);
        UserNode userB4 = new UserNode(1224L);
        UserNode userB5 = new UserNode(441L);
        UserNode userB6 = new UserNode(9196L);
        UserNode userB7 = new UserNode(7372L);
        UserNode userB8 = new UserNode(19L);
        UserNode userB9 = new UserNode(35L);
        userB.getFriends().add(userB1);
        userB1.getFriends().add(userB2);
        userB1.getFriends().add(userB3);
        userB3.getFriends().add(userB4);
        userB3.getFriends().add(userB5);
        userB4.getFriends().add(userB6);
        userB6.getFriends().add(userB7);
        userB6.getFriends().add(userB8);
        userB8.getFriends().add(userB9);

        // 进行双向广度优先搜索
        Integer degree = BidirectionalSearch(userA, userB);

        if (degree > 0) {
            logger.info("用户：{}和用户：{}是{}度好友", userA.getUserId(), userB.getUserId(), degree);
        }
    }
}
