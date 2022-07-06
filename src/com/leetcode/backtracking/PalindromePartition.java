package backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {

    // 分割回文串
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/palindrome-partitioning/
     *
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * 返回 s 所有可能的分割方案。
     *
     * 示例:
     * 输入: "aab"
     * 输出: [ ["aa","b"], ["a","a","b"] ]
     */

    // 分割问题，也是类似一个组合问题，自然想到了，可以转换成一个树形问题来分析
    public List<List<String>> partition(String s) {
        partition(s, 0, new ArrayList<>());
        return result;
    }

    List<List<String>> result = new ArrayList<>();

    public void partition(String s, int startIndex, List<String> path) {

        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)) {
                path.add(s.substring(startIndex, i+1)); // path.add(); // ??? 添加的是一个回文串
            } else {
                continue;
            }
            partition(s, i+1, path);
            path.remove(path.size() - 1);// 回溯
        }
    }

    public boolean isPalindrome(String s, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            if (s.charAt(startIndex) != s.charAt(endIndex))
                return false;
            startIndex++;
            endIndex--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "abccd";
        PalindromePartition pp = new PalindromePartition();
        pp.partition(str);
    }

}
