package com.leetcode.greedy_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QueueReconstructionByHeight {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
     *
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     *
     * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     *
     * 示例 1：
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     *
     * 示例 2：
     * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
     * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
     * 提示：
     *
     * 1 <= people.length <= 2000
     * 0 <= hi <= 10^6
     * 0 <= ki < people.length
     * 题目数据确保队列可以被重建
     */

    /**
     * 又是两个维度，非常像是分糖果那个题目
     *
     * 一次只考虑一个维度，否则便会顾此失彼
     * 本题目，到是考虑那个那个维度？一个个来先手动模拟
     *
     * Arrays.sort()  数组排序方法
     */
    public int[][] reconstructQueue(int[][] people) {
        // 数组排
        Arrays.sort(people, (o1, o2) -> o2[0] - o1[0]);

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
        return list.toArray(new int[people.length][people[0].length]);
    }

    public static void main(String[] args) {
        int[][] people = {{7,0},{4,4}, {7,1},{5,0},{6,1},{5,2}};
        QueueReconstructionByHeight rr = new QueueReconstructionByHeight();

        System.out.println("==========queue2");
        System.out.println(rr.reconstructQueue(people));
    }

}
