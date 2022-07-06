package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    /**
     * 找出所有相加之和为n 的k个数的组合，且满足下列条件：
     *
     * 只使用数字1到9
     * 每个数字最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     *
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * 解释:
     * 1 + 2 + 6 = 9
     * 1 + 3 + 5 = 9
     * 2 + 3 + 4 = 9
     * 没有其他符合的组合了。
     *
     * 输入: k = 4, n = 1
     * 输出: []
     * 解释: 不存在有效的组合。
     * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
     *
     * */

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum3(k, 1, n, new ArrayList<>(), 0);
        return result;
    }

    // 找出 相加之和为n的k个数的组合  仅使用数字1--9
    public void combinationSum3(int k, int start, int n, List<Integer> path, int pathSum) {
        // 剪枝
        if (pathSum > n) {
            return;
        }
        if (path.size() == k && pathSum == n) {
            result.add(new ArrayList<>(path));
            return;
        } else if (path.size() > k) {
            return;
        }

        // 开始尝试剪枝：需要k个数据，当已经有path.size个数据时，剩下还需要 k - path.size个数据
        // 为什么最后还要+1，因为包含起始位置
        for (int i = start; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            pathSum += i;
            combinationSum3(k, i+1, n, path, pathSum);
            pathSum -= i;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum cc = new CombinationSum();
        cc.combinationSum3(9,45);
    }
}
