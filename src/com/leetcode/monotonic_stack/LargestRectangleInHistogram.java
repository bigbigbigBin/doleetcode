package com.leetcode.monotonic_stack;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {
    /**
     * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     *
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * 输入：heights = [2,1,5,6,2,3]
     * 输出：10
     * 解释：最大的矩形为图中红色区域，面积为 10
     *
     * 输入： heights = [2,4]
     * 输出： 4
     */

    // 题解讲得有点复杂，不利于理解。。。
    // 说白了，这题考的基础模型其实就是：在一维数组中对每一个数找到第一个比自己小的元素。这类“在一维数组中找第一个满足某种条件的数”的场景就是典型的单调栈应用场景。


    // 我们归纳一下枚举「高」的方法：
    // 首先我们枚举某一根柱子 i 作为高 h = heights[i];
    // 随后我们需要进行向左右两边扩展，使得扩展到的柱子的高度均不小于h。换句话说，我们需要找到左右两侧最近的高度小于h 的柱子，这样这两根柱子之间（不包括其本身）的所有柱子高度均不小于 h，并且就是 i 能够扩展到的最远范围。

    // 找到每个柱子左右两边第一个小于该柱子的柱子
    // 【栈顶】和【栈顶的下一个元素】以及【要入栈的】三个元素组成了我们要求最大面积的高度和宽度
    // 从栈顶 到 栈尾，是从大到小
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();

        // 为了防止数组已经是从小到大有序了，导致无法出栈，所以在原数组上，额外添加尾部元素0
        // 为了防止 前面已经都出栈了，后面的元素往前找高度小于heights[top]的柱子时无法找到，额外在头部添加0
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        heights = newHeights;
        for (int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int top = stack.pop();
//                if (!stack.isEmpty()) {
                    int left = stack.peek();
                    int width = i - left - 1; // 只要中间的，
                    int height = heights[top];
                    max = Math.max(height * width, max);
//                } else {   // 有了首元素0，就可以不用走到这个分支了
//                    int width = i - top;
//                    int height = heights[top];
//                    max = Math.max(height * width, max);
//                }
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
//        int[]heights = {2,1,5,6,2,3};
//        int[]heights ={2,4};
        int[]heights ={2,1,2};
        LargestRectangleInHistogram ll = new LargestRectangleInHistogram();
        System.out.println(ll.largestRectangleArea(heights));
    }
}
