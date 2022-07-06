package backtracking;

import java.util.ArrayList;
import java.util.List;

public class PossibleSubsets {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/subsets/
     *
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     *
     * 输出: [ [3],   [1],   [2],   [1,2,3],   [1,3],   [2,3],   [1,2],   [] ]
     */

    List<List<Integer>> result = new ArrayList<>();

    // 解法二 完全当成一个树问题来解
    // 那么搜集所有子集，就是把树中的每个节点（而不单单是叶子节点）都要记录下来，
    // 相应的backTracing函数要做一点点改动
    public List<List<Integer>> subsets(int[] nums) {
        backTracing(nums,0, new ArrayList<>());
        return result;
    }



    public void backTracing(int[] nums, int startIndex, List<Integer> path) {
        // 要记录树的每个节点
        result.add(new ArrayList<>(path));

        // 终止条件
        if (startIndex > nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backTracing(nums, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    // 解法一
    // 此种解法，是借鉴了选取K个数，能有多少组合问题。
    public List<List<Integer>> subsets2(int[] nums) {
        result.add(new ArrayList<>());
        for (int k = 1; k <= nums.length; k++) {
            backTracing(nums, k, 0, new ArrayList<>());
        }
        return result;
    }


    public void backTracing(int[] nums, int k, int startIndex, List<Integer> path) {
        // 终止条件
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backTracing(nums, k, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,3};
        int[] nums = {};
        PossibleSubsets pp = new PossibleSubsets();
        System.out.println(pp.subsets(nums));
    }
}
