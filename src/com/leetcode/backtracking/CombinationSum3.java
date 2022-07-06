package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum3 {
    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 说明：所有数字（包括目标数）都是正整数。解集不能包含重复的组合。
     *
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为: [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
     *
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为: [   [1,2,2],   [5] ]
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        boolean[] used = new boolean[candidates.length]; // 记录元素的访问情况
        Arrays.sort(candidates); // 先升序排列，让相同元素紧挨着，方便进行去重判断
        combinationSum2(candidates, target, 0, new ArrayList<>(), 0, used);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    public void combinationSum2(int[] candidates, int target, int startIndex, List<Integer> path, int sum, boolean[] used) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            // 说明前面的递归中，已经访问了以经访问了以candidates[i-1]为根的树枝
            if (i > 0 && candidates[i] == candidates[i-1] && used[i-1] == false) {
                continue;
            }

            path.add(candidates[i]);
            sum += candidates[i];
            used[i] = true;

            combinationSum2(candidates, target, i + 1, path, sum, used);

            sum -= candidates[i];
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
