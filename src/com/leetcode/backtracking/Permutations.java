package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/permutations/
     *
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
     */

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        backTracing(nums, used, new ArrayList<>());
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 由于每一层选的元素可重复，所以for循环的下标，不必使用startIndex了
     * 每次判断是否使用了某个元素，借助used数组
     */
    public void backTracing(int[] nums, boolean[] used, List<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0 ; i < nums.length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
            } else {
                // 如果元素使用过了，则继续判断下一个元素
                continue;
            }
            backTracing(nums, used, path);
            path.remove(path.size() - 1);
            used[i] = false;

        }
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,3};
        int[] nums = {0,1};
        Permutations pp = new Permutations();
        System.out.println(pp.permute(nums));
    }
}
