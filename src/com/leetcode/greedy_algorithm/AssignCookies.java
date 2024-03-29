package com.leetcode.greedy_algorithm;

import java.util.Arrays;

public class AssignCookies {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/assign-cookies
     *
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     *
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * 示例 1:
     *
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1 解释:你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。所以你应该输出1。
     * 示例 2:
     *
     * 输入: g = [1,2], s = [1,2,3]
     * 输出: 2
     * 解释:你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。你拥有的饼干数量和尺寸都足以让所有孩子满足。所以你应该输出2.
     * 提示：
     *
     * 1 <= g.length <= 3 * 10^4
     * 0 <= s.length <= 3 * 10^4
     * 1 <= g[i], s[j] <= 2^31 - 1
     */


    // 更加优化：只需单层循环
    // 思想就是，大饼干尽量喂给胃口大的孩子，这样充分利用饼干尺寸
    // 或者，也可以小饼干喂给胃口小的孩子
    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            // 为了防止饼干选择完毕，index已经减为0，所以必须要加上这个判断
            if (index >= 0 && g[i] <= s[index]) {
                count++;
                index--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] g = {1,2,3}, s = {1,1};
        int[] g = {1,2}, s = {1,2,3};
        AssignCookies ag = new AssignCookies();
        System.out.println(ag.findContentChildren(g, s));

    }


    // 贪心
    // 思想就是，大饼干尽量喂给胃口大的孩子，这样充分利用饼干尺寸
    // 本体双层循环，效率不好，继续改进，就是只是用一个for循环
//    public int findContentChildren(int[] g, int[] s) {
//        int count = 0;
//
//        for (int i = 0; i < g.length; i++) {
//            int minIndex = -1;
//            int minValue = -1;
//            for (int j = 0; j < s.length; j++) {
//                if (s[j] == g[i]) {
//                    minIndex = j;
//                    break;
//                }
//                if (s[j] > g[i]) {
//                    if (minIndex == -1) {
//                        minIndex = j;
//                        minValue = s[j];
//                    } else if (minValue > s[j]) {
//                        minIndex = j;
//                        minValue = s[j];
//                    }
//                }
//            }
//            if (minIndex != -1) {
//                count++;
//                s[minIndex] = 0;
//            }
//        }
//        return count;
//    }
}
