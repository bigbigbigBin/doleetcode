package backtracking;

import java.util.ArrayList;
import java.util.List;

public class IncreasingSubSequences {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/increasing-subsequences/
     *
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     *
     * 示例:
     *
     * 输入: [4, 6, 7, 7]
     * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     *
     * 说明:
     * 给定数组的长度不会超过15。
     * 数组中的整数范围是 [-100,100]。
     * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     * */

    public List<List<Integer>> findSubsequences(int[] nums) {
        boolean[] used = new boolean[nums.length];
        backTracing(nums, 0, new ArrayList<>(), used);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    public void backTracing(int[] nums, int startIndex, List<Integer> path, boolean[] used) {
        if (path.size() >= 2) { // 相当于路径上的每个节点都要添加到最后结果里面
            result.add(new ArrayList<>(path));
        }

        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (!shouldAdd(nums, startIndex, i, used)) {
                continue;
            } else if (path.size() == 0 || path.get(path.size() - 1) <= nums[i]) {
                used[i] = true;
                path.add(nums[i]);
            } else {
                continue;
            }

            backTracing(nums, i+1, path, used);
            used[i] = false;
            path.remove(path.size() - 1); // 回溯本次添加的
        }
    }

    // 本题的去重逻辑：从startIndex开始，到某个数，这个区间内，没有重复出现
    private boolean shouldAdd(int[] nums,int startIndex, int index, boolean[] used) {
        // todo 为什么要从startIndex开始？？
        // 如样例：[100,90,80,70,60,50,60,70,80,90,100]
        // 假设当前第一个元素为90，要是i = 0开始，那么当判断最后面的100时，会认为之前已经出现了，此时不应该添加
        // 但实际上，本此第一个元素为90，[90,100]完全可以组成一个递增序列。
        // todo 可以继续优化，没必要每次都遍历一遍数组，可以使用set或者可以使用哈希（因为本题目说了范围为-100--100）
        for (int i = startIndex ; i < index; i++) {
            if (nums[i] == nums[index] && !used[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        int[] nums = {4,6,7,7};
        int[] nums = {4,4,1,2};
        IncreasingSubSequences ii = new IncreasingSubSequences();
        System.out.println(ii.findSubsequences(nums));
    }





}
