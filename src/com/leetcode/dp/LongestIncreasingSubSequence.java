package dp;

import java.util.PriorityQueue;

public class LongestIncreasingSubSequence {

    public int lengthOfLIS(int[] nums) {
        // 1、dp[i] 表示到下标i时，最长严格递增子序列的长度
        // dp[i] = max(dp[j] + 1, dp[j]) ,   nums[i] > nums[j]    j从 0----i-1
        int[] dp = new int[nums.length + 1];
        dp[0] = 1;
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i - 1; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        LongestIncreasingSubSequence ll = new LongestIncreasingSubSequence();
        System.out.println(ll.lengthOfLIS(nums));

    }
}
