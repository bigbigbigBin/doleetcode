import java.util.Arrays;

public class ConstructMaxValBinaryTree {

    /**
     * 给定一个不重复的整数数组nums 。
     *
     * 最大二叉树可以用下面的算法从nums 递归地构建:
     * 创建一个根节点，其值为nums 中的最大值。
     * 递归地在最大值 左边 的子数组前缀上构建左子树。
     * 递归地在最大值 右边 的子数组后缀上构建右子树。
     * 返回nums 构建的 最大二叉树 。
     *
     */

    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        // 第一步：递归终止条件
        if (start >= end) {
            return null;
        }

        // 第二步： 找出最大值以及下标
        int[] maxValInd = findMaxNum(nums, start, end);
        TreeNode root = new TreeNode(maxValInd[0]);

        // 第三步：切割数组   不使用copy函数了，直接借用递归函数+下标来处理子数组

        // 第四步：递归左右子树
        root.left = constructMaximumBinaryTree(nums, start, maxValInd[1]);
        root.right = constructMaximumBinaryTree(nums, maxValInd[1] + 1, end);
        return root;
    }

    /**
     * 找出最大值 & 最大值的下标
     */
    private int[] findMaxNum(int[] nums, int start, int end) {
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = start; i < end; i++) {
            if (nums[i] > maxValue) {
                maxIndex = i;
                maxValue = nums[i];
            }
        }
        return new int[] {maxValue, maxIndex};
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,6,0,5};
        ConstructMaxValBinaryTree cc = new ConstructMaxValBinaryTree();
        cc.constructMaximumBinaryTree2(nums);

    }
}
