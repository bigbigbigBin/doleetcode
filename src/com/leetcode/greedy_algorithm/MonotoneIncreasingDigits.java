package com.leetcode.greedy_algorithm;

public class MonotoneIncreasingDigits {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/monotone-increasing-digits
     *
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     *
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     *
     * 示例 1:
     *
     * 输入: N = 10
     * 输出: 9
     * 示例 2:
     *
     * 输入: N = 1234
     * 输出: 1234
     * 示例 3:
     *
     * 输入: N = 332
     * 输出: 299
     * 说明: N 是在 [0, 10^9] 范围内的一个整数。
     * */

    /**
     * 不能从前往后遍历，会改变已经遍历的结果
     * 如332， 满足nun[i-1] > num[i], num[i-1]减一, 然后nums[i]改为9。
     * 3 3 不满足nun[i-1] > num[i]，所以不变
     *     3 2 满足nun[i-1] > num[i], 变成 2 9， 但是2此刻又变得小于了第一个3，打破了上一轮的规则结果
     *
     * 所以，一定要从后往前遍历
     */
    public int monotoneIncreasingDigits(int n) {
        char[] str = String.valueOf(n).toCharArray();
        int start = str.length;

        for (int i = str.length - 1; i > 0; i--) {
            if (str[i] < str[i-1]) {
                str[i-1]--;
                start = i;
            }
        }

        for (int i = start; i < str.length; i++) {
            str[i] = '9';
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            s.append(str[i]);
        }
        return Integer.parseInt(s.toString());
    }

    public static void main(String[] args) {
        int str = 100;
        MonotoneIncreasingDigits mm = new MonotoneIncreasingDigits();
        System.out.println(mm.monotoneIncreasingDigits(str));
    }


    // 从左到右遍历，处理逻辑复杂
//    public int monotoneIncreasingDigits(int n) {
//        char[] str = String.valueOf(n).toCharArray();
//        //1、 找到第一个非递增的数字所在位置，将此位置，及之后位置全部改为最大值9
//
//        //2、 将左边的位置减一，判断是否为递增，不是的话，则将此位置改为最大值，继续左边的数字减一
//        int firstNotInc = 0;
//        for (int i = 0; i < str.length -1 ; i++) {
//            if (str[i] > str[i+1]) {
//                firstNotInc = i+1;
//            }
//        }
//
//        if (firstNotInc == 0) {
//            return n;
//        }
//
//        for (int i = firstNotInc; i < str.length; i++) {
//            str[i] = '9';
//        }
//        str[firstNotInc] = (char) (str[firstNotInc] - 1);
//        int stopInde = firstNotInc;
//        firstNotInc--;
//
//        while(firstNotInc > 0 && str[firstNotInc - 1] > str[firstNotInc]) {
//            for (int i = firstNotInc; i < stopInde; i++) {
//                str[i] = '9';
//            }
//            str[firstNotInc] = (char) (str[firstNotInc] - 1);
//            stopInde = firstNotInc;
//            firstNotInc--;
//        }
//        return Integer.valueOf(String.valueOf(str));
//    }

}
