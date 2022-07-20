package com.leetcode.dp.split_character;

public class EditDistance {
    /**
     * 给你两个单词word1 和word2， 请返回将word1转换成word2 所使用的最少操作数。
     *
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * 示例 1：输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     *
     * 示例 2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     *
     * 提示：
     * 0 <= word1.length, word2.length <= 500
     * word1 和 word2 由小写英文字母组成
     */

    // dp[i][j] 以下标i-1为结尾的子串A，以下标j-1为结尾的子串B，相同时，所使用的最小操作数
    //      当word1(i-1) == word2[j-1]，不进行任何操作；
    //      当word1(i-1) != word2[j-1]，可以进行的操作为增、删、替换：
    //          在word1上增加字符后，使之相等    dp[i][j-1] + 1
    //          在word1上删除字符后，使之相等    dp[i-1][j] + 1
    //          在word1上替换字符后，使之相等    dp[i-1][j-1] + 1
    //          在word2上增加字符后，使之相等    dp[i-1][j] + 1，发现等同于word1上删除一个字符
    //          在word2上删除字符后，使之相等    dp[i][j-1] + 1，发现等同于word1上添加一个字符
    //          在word2上替换字符后，使之相等    dp[i-1][j-1] + 1

    // dp[i][j] = dp[i-1][j-1]   当word1(i-1) == word2[j-1]
    //          = min(dp[i][j-1] + 1， dp[i-1][j] + 1, dp[i-1][j-1] + 1)

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]; // 相等，则此次不需要编辑。此位置所需最小编辑数，等于上一下标所对应的最小编辑数
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + 1, Math.min(dp[i][j-1] + 1, dp[i-1][j-1] + 1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
