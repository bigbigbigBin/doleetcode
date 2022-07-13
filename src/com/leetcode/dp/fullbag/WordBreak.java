package dp.fullbag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    /**
     * 题目链接：https://leetcode-cn.com/problems/word-break/
     *
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     *
     * 说明：
     *
     * 拆分时可以重复使用字典中的单词。
     *
     * 你可以假设字典中没有重复的单词。
     *
     * 示例 1：
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     *
     * 示例 2：
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     *
     * 示例 3：
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     */

    // dp[i] 表示 长度为i的字符串，如果 = true，则表示可以拆分为一个或者多个字典中的单词
    // dp[i] = true,  if dp[j] == true && [j -> i]所组成的子串叶也在字典中
    //       = false  其他情况
    // dp[i] = true
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < wordDict.size(); j++){
                if (i - wordDict.get(j).length() >= 0 && set.contains(s.substring(i - wordDict.get(j).length(), i)) && dp[i - wordDict.get(j).length()]) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("lee", "code");
        WordBreak ww = new WordBreak();
        System.out.println(ww.wordBreak(s, wordDict));
    }

}
