package com.leetcode.greedy_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/merge-intervals
     *
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 示例 1:
     *
     * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: intervals = [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * 注意：输入类型已于2019年4月15日更改。请重置默认代码定义以获取新方法签名。
     * 提示：
     *
     * intervals[i][0] <= intervals[i][1]
     */

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        // 首先排序，按照左边界升序排序，然后重叠区间时，尽可能选择最远的右节点，将之合并
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        List<int[]> res = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) { // 说明有重叠部分
                intervals[i][1] = Math.max(intervals[i][1], intervals[i-1][1]);
            } else { // 没有重复部分
                res.add(new int[] {start, intervals[i][1]});
                start = intervals[i][0];
            }
        }
        res.add(new int[]{start, intervals[intervals.length - 1][1]});

        return res.toArray(new int[res.size()][2]);
    }

    public static void main(String[] args) {
//        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = {{1,4},{4,5}};
        MergeIntervals mm = new MergeIntervals();
        int[][] finalArray = mm.merge(intervals);

        for (int i = 0; i < finalArray.length; i++) {
            System.out.println(Arrays.toString(finalArray[i]));
        }
    }
}
