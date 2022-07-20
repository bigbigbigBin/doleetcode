package com.leetcode.dp.sub_array_sequence;

public class LongestContinueIncreasingSubSequence {
    /**
     * 题目链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
     *
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     *
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     *
     * 和上一题的区别在于：【连续】
     *
     * 示例 1：
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     *
     * 示例 2：
     * 输入：nums = [2,2,2,2,2]
     * 输出：1
     * 解释：最长连续递增序列是 [2], 长度为1。
     *   提示：
     *
     * 0 <= nums.length <= 10^4
     * -10^9 <= nums[i] <= 10^9
     */


    // dp[j] 表示 以下标j为结尾的连续子序列（其实就是子数组）的长度
    //  因为本题要求连续递增子序列，所以就只要比较nums[i]与nums[i-1]，而不用去比较nums[j]与nums[i] （j是在0到i之间遍历）。
    //  既然不用j了，那么也不用两层for循环，本题一层for循环就行，比较nums[i] 和 nums[i - 1]。

    // dp[j] = dp[j-1] + 1,   nums[j] > nums[j-1]
    //       = 1;

    public int continuesLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLength = dp[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = 1;
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }
}
