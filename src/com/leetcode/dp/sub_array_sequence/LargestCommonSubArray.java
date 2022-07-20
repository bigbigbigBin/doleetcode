package com.leetcode.dp.sub_array_sequence;

import java.util.Arrays;

public class LargestCommonSubArray {

    /**
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     *
     * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1] 。
     */


//    // 求最值，且能看出递推规则的，一般用动态规划
//    // dp[i][j] 表示以nums1[i-1]为子数组的结尾，nums2[j]为子数组的结尾, 这俩个子数组所组成的公共子数组的长度
//    // dp[i][j] = dp[i-1][j-1] + 1，  if nums1[i-1] == nums2[j-1]
//    //          = 0                      nums1[i-1] != nums2[j-1]
//    public int findLength(int[] nums1, int[] nums2) {
//        // 默认数组里面都是0
//        int[][] dp = new int[nums1.length+1][nums2.length+1];
//        int maxLength = 0;
//        for (int i = 0; i < nums1.length; i++) {
//            for (int j = 0; j< nums2.length; j++) {
//                if (nums1[i] == nums2[j]) {
////                    System.out.println("i = " + i + ", j = " + j);
//                    dp[i+1][j+1] = dp[i][j] + 1;
//                    maxLength = Math.max(maxLength, dp[i+1][j+1]);
//                }
//
//            }
//        }
//        for (int i = 0; i < dp.length ; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        return maxLength;
//    }

    // 求最值，且能看出递推规则的，一般用动态规划
    // dp[i][j] 表示以下标i为结尾的子数组A，和以下标j为结尾的子数组B, 这俩个子数组所组成的公共子数组的长度
    // dp[i][j] = dp[i-1][j-1] + 1，  if nums1[i] == nums2[j]
    //          = 0                      nums1[i] != nums2[j]

    // 知道递推公式，下一步就是看看初始条件以及遍历循序
    // dp[i][j] 依赖于前一步的dp[i-1][j-1]，所以正常从左到右开始遍历即可。
    // 正因为这个依赖于前一步，所以初始的dp[0][j]，dp[i][0]都必须要初始化好，否则遇到-1，就会出现数组越界。
    // 也整因为此，所以遍历的起点，应该是从下标1开始，而不是0
    public int findLength2(int[] nums1, int[] nums2) {
        // 默认数组里面都是0
        int[][] dp = new int[nums1.length][nums2.length];
        int maxLength = 0;

        // 初始化基础条件 dp数组的第一列为0
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = nums1[i] == nums2[0] ? 1 : 0;
            maxLength = Math.max(dp[i][0], maxLength);
        }
        // 初始化基础条件 dp数组的第一行为0
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = nums2[j] == nums1[0] ? 1 : 0;
            maxLength = Math.max(dp[0][j], maxLength);
        }

        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j< nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
//        for (int i = 0; i < dp.length ; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        return maxLength;
    }



    /**
     * 上一个版本的动态规划，初始化部分比较麻烦，要写两层循环来初始化
     *
     * 那么怎么优化呢？
     * 这个数组的含义，既然是自己定义的，那么我可否定义成其他含义呢？
     * dp[i][j]，定义成该位置存储的是：以下标i-1为结尾的A 和 以下标j-1为结尾的B，所组成的最长相同公共子串长度
     *
     * 那么 dp[i][j] = dp[i-1][j-1] + 1， if( A[i-1]== B[j-1] )
     *          如果A[i-1]和B[j-1]相等，则以下标i-1结尾的A、和以下标j-1结尾的B，最长公共子串的长度等于以下标i-2结尾的A、和以下标j-2结尾的B，公共子串的长度 + 1；
     *          这个+1就是指的A[i-1] ==B[j-1]时所带来的增量长度， 以下标i-2结尾的A、和以下标j-2结尾的B，他们的公共子串的长度所存放的位置在dp[i-1][j-1];
     */
    public int findLength3(int[] nums1, int[] nums2) {
        // 因为我用dp[i][j]位置，存的是以num1[i-1],num2[j-1]为结尾的公共子数组的长度
        //  i-1这个值，最大可达到nums1.length；    j-1这个值最大可达到nums2.length
        //  由此，我们需要将dp数组的长度申请为[nums1.length+1][nums2.length+1]，这样才会存的下最长情况的公共子串。
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        int maxLength = 0;

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j<= nums2.length; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
//        for (int i = 0; i < dp.length ; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        return maxLength;
    }



    public static void main(String[] args) {
        int[] nums1 = {1,2,3,2,1}, nums2 = {3,2,1,4,7};
//        int[] nums1 = {0,0,0,0,0}, nums2 = {0,0,0,0,0};
//        int[] nums1 = {0,0,0,0,1}, nums2 = {1,0,0,0,0};
//        int[] nums1 = {1,2,3,2,1}, nums2 ={3,2,1,4};
        LargestCommonSubArray l = new LargestCommonSubArray();
//        System.out.println(l.findLength(nums1, nums2));
        System.out.println(l.findLength2(nums1, nums2));
        System.out.println(l.findLength3(nums1, nums2));
    }
}
