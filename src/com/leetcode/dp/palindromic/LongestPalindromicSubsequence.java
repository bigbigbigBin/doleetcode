package com.leetcode.dp.palindromic;

public class LongestPalindromicSubsequence {
    /**
     * 题目链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence/
     *
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     *
     * 示例 1:
     * 输入: "bbbab"
     * 输出: 4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:"cbbd"
     * 输出: 2
     * 一个可能的最长回文子序列为 "bb"。
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 只包含小写英文字母
     */

    // 最长回文子序列
    // dp[i][j] 表示 从下标i到下标j所组成的子串中，最长回文串的长度
    // dp[i][j] = dp[i+1][j-1] + 2
    //          = max(dp[i][j-1], dp[i+1][j])
    public int longestPalindromicSubsequence(String str) {
        int[][] dp = new int[str.length()][str.length()];
        int max = 0;
        for (int i = str.length() - 1; i >=0; i--) {
            for (int j = i; j < str.length(); j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (str.charAt(i) == str.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence ll = new LongestPalindromicSubsequence();
        System.out.println(ll.longestPalindromicSubsequence("cbbd"));
        System.out.println(ll.longestPalindromicSubsequence("bbbab"));
    }
}
