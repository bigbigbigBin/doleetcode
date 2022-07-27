package com.leetcode.greedy_algorithm;

public class JumpGameII {
    /**
     * 力扣题目链接 https://leetcode-cn.com/problems/jump-game-ii/
     *
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 示例:
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 说明: 假设你总是可以到达数组的最后一个位置。
     */

    /**
     * 解题的时候，要从覆盖范围出发，不管怎么跳，覆盖范围内一定是可以跳到的，以最小的步数增加覆盖范围，
     * 覆盖范围一旦覆盖了终点，得到的就是最小步数！
     * 这里需要统计两个覆盖范围，当前这一步的最大覆盖和下一步最大覆盖。
     */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int count = 0;
        int curMaxJumpScale = 0;
        int nextMaxJumpScale = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + nums[i] > nextMaxJumpScale) { //
                nextMaxJumpScale = i + nums[i];
            }
            if (curMaxJumpScale == i) { // i已经走到了 本次跳跃的范围的最后
                if (curMaxJumpScale != nums.length -1) { // 如果还没到达最后一位
                    count++; // 继续跳跃
                    curMaxJumpScale = nextMaxJumpScale;
                    if (curMaxJumpScale >= nums.length - 1) {
                        break;
                    }
                } else {  // 到达了最后一位
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
//        int[] nums = {2,3,0,1,4};
//        int[] nums = {1,2,1,1,1};
        JumpGameII jj = new JumpGameII();
        System.out.println(jj.jump(nums));
    }

}
