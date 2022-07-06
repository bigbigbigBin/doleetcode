package string;

import java.util.Arrays;

public class KMPString2 {

    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     *
     * 示例 1:
     * 输入: "abab"
     * 输出: True
     * 解释: 可由子字符串 "ab" 重复两次构成。
     *
     * 示例 2:
     * 输入: "aba"
     * 输出: False
     *
     * 示例 3:
     * 输入: "abcabcabcabc"
     * 输出: True
     * 解释: 可由子字符串 "abc" 重复四次构成。(或者子字符串 "abcabc" 重复两次构成。)
     */

    /**
     * 本体属于，KMP的next数组的巧用
     * 如果字符串的next数组中，最后一位 > 0 说明字符串中是存在前缀子串 == 后缀子串的，那么原字符串有可能会出现子串多次重复构成；
     *
     * 下一步，如果刨去前缀&后缀，中间的部分也是由前缀（或后缀）构成，那么整个子串就是由前缀构成；
     *
     * 有点不明所以？？？ 为什么
     *
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() == 0) {
            return false;
        }
        int[] next = new int[s.length()];
        int i = 1;
        int now = 0;

        while(i < s.length()) {
            if (s.charAt(i) == s.charAt(now)) {
                next[i] = now + 1;
                now++;
                i++;
            } else {
                if (now > 0) {
                    now = next[now - 1];
                } else {
                    i++;
                }
            }
        }

        int k = next[s.length() - 1];
        int commonStrLength = s.length() - k;

        return k > 0 && k % commonStrLength == 0;
    }


    public static void main(String[] args) {
        String s = "abcabcabcabc";
        KMPString2 kmp = new KMPString2();
        System.out.println(kmp.repeatedSubstringPattern(s));
    }
}
