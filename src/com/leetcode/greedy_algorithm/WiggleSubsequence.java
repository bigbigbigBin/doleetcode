package com.leetcode.greedy_algorithm;

public class WiggleSubsequence {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/wiggle-subsequence
     *
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
     * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
     *
     * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
     * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     *
     * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。
     * 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
     *
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 【最长子序列的长度】。
     *
     *
     * 示例 1:
     * 输入: [1,7,4,9,2,5]
     * 输出: 6
     * 解释: 整个序列均为摆动序列。
     *
     * 示例 2:
     * 输入: [1,17,5,10,13,15,10,5,16,8]
     * 输出: 7
     * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
     *
     * 示例 3:
     * 输入: [1,2,3,4,5,6,7,8,9]
     * 输出: 2
     */

    /**
     * 把它想象成一个图形，
     * 比方[1,17,5,10,13,15,10,5,16,8]
     *                         15
     *                       /   \
     *          17         13     \
     *        /   \       /        10      16
     *      /      \     10         \     /  \
     *     /        \  /             \   /    8
     *    1          5                5
     *    有了这个图形之后，就可以发现，
     *    局部最优：删除单调坡度上的节点（不包括单调坡度两端的节点），那么这个坡度就可以有两个局部峰值。
     *    整体最优：整个序列有最多的局部峰值，从而达到最长摆动序列。
     *
     * 贪心的题目说简单有的时候就是常识，说难就难在都不知道该怎么用贪心。
     * 本题大家如果要去模拟删除元素达到最长摆动子序列的过程，那指定绕里面去了，一时半会拔不出来。
     * 而这道题目有什么技巧说一下子能想到贪心么？
     * 其实也没有，类似的题目做过了就会想到。
     * 此时大家就应该了解了：保持区间波动，只需要把单调区间上的元素移除就可以了。
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int preDiff = 0;
        int curDiff = 0;
        int length = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            curDiff = nums[i+1] - nums[i];
            if (curDiff > 0 && preDiff <= 0 || curDiff < 0 && preDiff >= 0) {
                length++;
                preDiff = curDiff;
            }
        }
        return length;
    }

    public static void main(String[] args) {

    }
}
