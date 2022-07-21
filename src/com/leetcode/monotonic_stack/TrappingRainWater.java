package com.leetcode.monotonic_stack;

import java.util.Stack;

public class TrappingRainWater {

    /**
     * 题目链接：https://leetcode-cn.com/problems/trapping-rain-water/
     *
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 示例 1：
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     */

    // 单调栈，从栈尾到栈顶，是降序。 当出现大于栈顶元素时，说明出现了凹槽。可以接水了
    // 每次只出战栈顶元素。保持循环不变量。
    // 自己想不懂的原因是，在进行计算面积时。出站了栈顶，又出栈了栈顶左边元素，这个是错的。
    // 不必出栈栈顶左边元素，因为，在计算面积时，总高度 = 左右两边元素的最小值，减去中间元素的高度。中间元素的意义在这里提现了
    public int trappingWater(int[] heights) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                int top = stack.pop();
                if (!stack.isEmpty()) {
                    int left = stack.peek();
                    int height = Math.min(heights[i], heights[left]) - heights[top];
                    int width = i - left - 1;
                    result += height * width;
                }
            }
            if (!stack.isEmpty() && heights[i] == heights[stack.peek()]) {
                stack.pop();
                stack.push(i);
            } else {
                stack.push(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater tp = new TrappingRainWater();
        System.out.println(tp.trappingWater(nums));
    }
}
