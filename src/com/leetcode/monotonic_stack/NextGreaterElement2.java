package com.leetcode.monotonic_stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement2 {
    /**
     * 链接：https://leetcode-cn.com/problems/next-greater-element-ii/
     *
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     *
     * 示例 1:
     *
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；数字 2 找不到下一个更大的数；第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     */

    public int[] nextGreater(int[] nums) {
        int [] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = -1;
        }
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < 2 * nums.length; i++) {
            while(!stack.isEmpty() && stack.peek()[0] < nums[i%nums.length]) {
                int[] top = stack.pop();
                result[top[1] % nums.length] = nums[i%nums.length];
            }
            stack.push(new int[]{nums[i% nums.length], i % nums.length});
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        NextGreaterElement2 nn = new NextGreaterElement2();
        System.out.println(Arrays.toString(nn.nextGreater(nums)));

    }

}
