package com.leetcode.greedy_algorithm;

public class MaximumSubArray {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/maximum-subarray
     *
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * [-2,1,-3,99]
     */

    // 贪心算法，只要我获得的【当前和 + num[i]】 < 0
    // 那么就要舍弃num[i] 然后从num[i+1]开始计算一个新的子序列
    // 贪心，贪在，出现和为负数之后，就舍弃这个新计算的数字，因为是这个数字让子数组的和变成负数
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int i = 0;
        while (i < nums.length) {
            sum += nums[i];
            if (sum > maxSum) {
                maxSum = sum;
            }

            if (sum < 0) {
                sum = 0;
            }
            i++;
        }
        return maxSum;
    }

    // 动态规划
    // dp[i][j]表示以下标i为起点，下标j为结束点的子数组中子数组的和
    // dp[i][j] = dp[i][j-1] + num[j] , if num[j]
//    public int maxSubArray2(int[] nums) {
//
//    }


    /**
     * 暴力法  O(n^2)
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int maxSubArraySum = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.length; i++) {
            int tempSum = 0;
            for (int j = i; j < nums.length; j++) {
                tempSum += nums[j];
                if (maxSubArraySum < tempSum) {
                    maxSubArraySum = tempSum;
                }
            }
        }
        return maxSubArraySum;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums = {5,4,-1,7,8};
        MaximumSubArray mm = new MaximumSubArray();
        System.out.println(mm.maxSubArray1(nums));
        System.out.println(mm.maxSubArray(nums));
    }
}
