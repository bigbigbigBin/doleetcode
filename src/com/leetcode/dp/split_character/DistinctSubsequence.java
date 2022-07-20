package com.leetcode.dp.split_character;

public class DistinctSubsequence {
    /**
     * https://leetcode.cn/problems/distinct-subsequences/
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     *
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     *
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     *
     * 示例1：
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * 解释：
     * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
     * rabbbit
     * rabbbit
     * rabbbit
     *
     * 示例2：
     * 输入：s = "babgbag", t = "bag"
     * 输出：5
     * 解释：
     * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     */

    // dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]。
    // 这一类问题，基本是要分析两种情况:
    // (1) s[i - 1] 与 t[j - 1]相等: dp[i][j]可以有【两部分】组成。
    //          一部分是用s[i - 1]来匹配，那么个数为dp[i - 1][j - 1]。
    //          一部分是不用s[i - 1]来匹配，个数为dp[i - 1][j]。
    //          例如：s：bagg 和 t：bag ，s[3] 和 t[2]是相同的，
    //          字符串s可以用s[3]来匹配，即：s[0]s[1]s[3]组成的bag。
    //          但是字符串s也可以不用s[3]来匹配，即用s[0]s[1]s[2]组成的bag。
    //    因此：当s[i - 1] 与 t[j - 1]相等时，dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
    // (2)s[i - 1] 与 t[j - 1] 不相等
    //      dp[i][j]只有一部分组成，不用s[i - 1]来匹配，即：dp[i - 1][j]

    public int distinctSubsequence(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        // dp[0][0] == 1  因为空串s，出现空串t的次数只能为1
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1; // 表示以i-1为结尾的s的子序列中，出现空串t的次数
        }

        for (int i = 1; i < t.length(); i++) {
            dp[0][i] = 0; // 表示空串 中，出现以i-1为结尾的t的子串 的个数
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }


}
