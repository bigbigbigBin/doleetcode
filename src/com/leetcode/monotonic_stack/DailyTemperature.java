package com.leetcode.monotonic_stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperature {
    /**
     * https://leetcode-cn.com/problems/daily-temperatures/
     *
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     */



    // 栈顶元素 保持最小，当新入栈的元素，大于当前栈顶元素，则出站栈顶，然后计算距离的下标有多远。
    //                 当新入栈的元素，小于当前栈顶元素，入栈。
    public int[] dailyTemperature(int[] temperatures) {
        int[] result = new int[temperatures.length];

        Stack<int[]> stack = new Stack<>();
        int count = 0;
        while(count < temperatures.length) {

            while (!stack.isEmpty() && stack.peek()[0] < temperatures[count]) {
                int[] top = stack.pop();
                result[top[1]] = count - top[1];
            }
            stack.push(new int[]{temperatures[count], count});

            count++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperature dd = new DailyTemperature();
        System.out.println(Arrays.toString(dd.dailyTemperature(temperatures)));
    }
}
