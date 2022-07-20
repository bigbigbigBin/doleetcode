package com.leetcode.dp.split_character;

public class DeleteOperationForTwoStrings {
    /**
     * 题目链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings/
     *
     * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
     *
     * 示例：
     *
     * 输入: "sea", "eat"
     * 输出: 2 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     */

    // dp[i][j] 以下标i-1位结尾的子串，和以下标j-1为结尾的子串，相同时，所删除的字符个数
    // dp[i][j] = dp[i-1][j-1], word1[i-1] == word2[j-1]
    //          = min(dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1]dp[j-1] + 2)
    //            min(删除word1中的字符， 删除word2中的字符, 同时删除word1、word1)
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 初始化
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + 1, Math.min(dp[i][j-1] + 1, dp[i-1][j-1] + 2));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
