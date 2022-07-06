package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PossibleSubsetsII {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/subsets-ii/
     *
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: [1,2,2]
     * 输出: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
     */

    // 问题的关键是去重 转换为树形结构，
    // 去重分为树枝去重（一个组合即为一个树枝）、树层去重（不同组合即为不同树层）
    // 本题目是要求树层去重，即同一层的左右不能有相同的，所以要去重。
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums); // 保证有序，便于后面判断相同元素是否应该舍弃
        backTracing(nums, used, 0, new ArrayList<>());
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    public void backTracing(int[] nums, boolean[] used, int startIndex, List<Integer> path) {
        result.add(new ArrayList<>(path));

        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                // todo :分析，为什么一开始会写break导致结果不对？？？
                //  想想一下同一层，从左到右，如果中间某个位置break，会导致后面的树枝不会被遍历到。
                //  所以要用continue，跳过本树枝的节点，继续后面的其他树枝.
                if (!used[i-1])
                    continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backTracing(nums, used, i+1, path);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2};
        PossibleSubsetsII pp = new PossibleSubsetsII();
        System.out.println(pp.subsetsWithDup(nums));
    }


}
