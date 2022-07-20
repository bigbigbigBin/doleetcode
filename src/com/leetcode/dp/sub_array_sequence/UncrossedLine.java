package com.leetcode.dp.sub_array_sequence;

public class UncrossedLine {
    /**
     * https://leetcode.cn/problems/uncrossed-lines/
     *
     * 我们在两条独立的水平线上按给定的顺序写下 A 和 B 中的整数。
     *
     * 现在，我们可以绘制一些连接两个数字 A[i] 和 B[j] 的直线，只要 A[i] == B[j]，且我们绘制的直线不与任何其他连线（非水平线）相交。
     *
     * 以这种方法绘制线条，并返回我们可以绘制的最大连线数。
     *
     * 示例 1：
     * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
     * 输出：2
     * 解释：可以画出两条不交叉的线，如上图所示。
     * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
     *
     * 示例 2：
     * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
     * 输出：3
     *
     *  示例 3：
     * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
     * 输出：2
     *
     */

    /**
     * 这个题目，看规律：
     * 本题说是求绘制的最大连线数，其实就是求两个字符串的最长公共子序列的长度！
     */

    // 本题目求解的是【子序列】，子序列要求序列中的字符在原串中不连续
    // dp[i][j]表示text1字符串中以i-1为结尾的字符串和text2字符串中以j-1为结尾的字符串的公共子序列的长度
    // dp[i][j] = dp[i-1][j-1] + 1, if text1[i] == text2[j]
    //          = Max(dp[i-1][j],dp[i][j-1])     if text1[i] != text2[j]
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        int maxLength = 0;

        // 因为dp[i][j]定义为以i-1为结尾的字符串A和以j-1为结尾的字符串B
        //  1 <= i, j <= text.length()
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                maxLength = Math.max(dp[i][j], maxLength);
            }
        }
        return maxLength;
    }
}
