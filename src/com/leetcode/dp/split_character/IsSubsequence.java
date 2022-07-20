package com.leetcode.dp.split_character;

public class IsSubsequence {
    /**
     * 题目链接：https://leetcode-cn.com/problems/is-subsequence/
     *
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     *
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * 示例 1：
     * 输入：s = "abc", t = "ahbgdc"
     * 输出：true
     *
     * 示例 2：
     * 输入：s = "axc", t = "ahbgdc"
     * 输出：false
     *
     * 提示：
     *
     * 0 <= s.length <= 100
     * 0 <= t.length <= 10^4
     * 两个字符串都只由小写字符组成。
     */

    // dp[i][j] 表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度。

    // dp[i][j] = dp[i-1][j-1] + 1    s.charAt(i-1) == s.charAt(j-1)
    //          = dp[i][j-1]          s.charAt(i-1) != s.charAt(j-1)
    boolean isSubsequence(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        // 如果是子序列的话，则最长子序列的长度，就应当等于s的长度
        return dp[s.length()][t.length()] == s.length();
    }

    /**
     * 方法二：
     * 双指针
     */
    boolean isSubsequence2(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;

        while(sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
                tIndex++;
            } else {
                tIndex++;
            }
        }

        if (sIndex == s.length()) {
            return true;
        }
        return false;
    }

}
