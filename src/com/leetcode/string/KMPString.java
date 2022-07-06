package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMPString {

    /**
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 示例 1: 输入: haystack = "hello", needle = "ll" 输出: 2
     *
     * 示例 2: 输入: haystack = "aaaaa", needle = "bba" 输出: -1
     *
     * 说明: 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     */

    // 所以kmp算法的本质是用已经比较过的k-1个后缀字符代替与之相同的(k-1)个前缀字符的比较。
    // 构建next数组, 回退数组
    private int[] buildNext(String pattern) {
        /**
         * 如何求出第x位置的next值呢？
         * 已知next[x-1]存放的是第0 ~ x-1位置时，最长k个字符组成的前缀（前缀不能包含最后一个字符）和k个字符组成的后缀（不能包含第一个字符）恰好相等。
         *    next[x-1]=k，说明是第0~k-1位置的字符串所组成的前缀 == 第 x-k ~ x-1所组成的后缀
         *
         * 对应这种格式，next[x] 存放的是从第0 ~ x位置时，最长有多少个字符组成的前缀和k个字符组成的后缀恰好相等。
         *
         *
         * 如果 Pattern[x] == Pattern[next[x-1]] ，则next[x] = next[x-1] + 1;
         *      这是为什么呢？
         *      因为next[x-1] = k，表示从下标0开始到k-1位置（为什么是k-1，因为下标是从0开始），有连续k个字符所组成的字符串（即前缀），与从下标x-k ~ x-1所组成的字符串（后缀）相同。
         *      所以，如果第k个元素与第x个元素相同，（即 Pattern[next[x-1]] == Pattern[x]），那么截止到第x位置，所能组成的最长前后缀相等的子串长度，必定等于next[x-1]+1；
         *
         * 如果 Pattern[x] != Pattern[next[x-1]]
         *      假设next[x]=m，表示从下标0 ~ m-1 所组成的前缀和 从下标x-m-1 ~ x 所组成的后缀一样长。
         *      已知next[x-1]=k, 即Pattern[0 ~ k-1] == Pattern[x-k ~ x-1]
         *
         *      为了能够利用上 已经比较过的k-1个后缀字符代替与之相同的(k-1)个前缀字符进行比较，
         *      我们需要知道Pattern[0 ~ k-1]组成的最长前缀长度next[k-1]，第next[k-1]位与Pattern[x]是否相等，这样又回到了一个循环判断
         *
         */
        int[] next = new int[pattern.length()];
        int i = 1;
        int now = next[i-1];
        while(i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(now)) {
                next[i] = now + 1;
                i++; // 那么i、now继续往下走一位置，重新去判断
                now++;
            } else {
                if (now > 0) {
                    now = next[now -1]; //
                } else { // 说明已经回退到最头上了，还是没有找到前缀 = 后缀，那么久彻底没有前缀=后缀的情况了
                    next[i] = 0;
                    i++;
                }
            }
        }
        return next;
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int[] next = buildNext(needle);
        int i = 0;
        int j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == needle.length() - 1) {
                    return i - needle.length() + 1;
                }
                i++;
                j++;
            } else {
                if (j > 0) {
                    j = next[j-1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }


    // 暴力法
    public int strStr2(String haystack, String needle) {
        int resIndex = -1;

        int startIndex = 0;
        int j = 0;
        int i = 0;
        while (j < needle.length() && i < haystack.length()) {
            if (haystack.charAt(i) != needle.charAt(j)) {
                startIndex = startIndex + 1;
                i = startIndex;
                j = 0;
            } else {
                i++;
                j++;
            }
        }
        if (j == needle.length()) {
            return startIndex;
        }
        return resIndex;
    }



    public static void main(String[] args) {
//        String haystack = "hello", needle = "ll";
//        String haystack = "aaaaa", needle = "bba";
//        String haystack = "", needle = "";
//        String haystack = "aabaabaafa",  needle = "aabaaf";
//        String haystack = "aaa", needle = "aaaa";
        KMPString kmp = new KMPString();
//        System.out.println(kmp.strStr(haystack, needle));

//        String pattern = "abcabdddabcabc";
        String pattern = "abcabcabc";
        System.out.println(Arrays.toString(kmp.buildNext(pattern)));
//        System.out.println(kmp.strStr(haystack, needle));
    }

}
