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

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        // 找出最大值、最大值的下标
        int rootIndex = 0;
        int rootVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > rootVal) {
                rootIndex = i;
                rootVal = nums[i];
            }
        }
        // 构建出根节点
        TreeNode root = new TreeNode(rootVal);             // 根节点

        // 切分出来左子树的数组、右子树的数组
        int[] leftNums = Arrays.copyOfRange(nums, 0, rootIndex);
        int[] rightNums = Arrays.copyOfRange(nums, rootIndex + 1, nums.length);

        root.left = constructMaximumBinaryTree(leftNums);  // 左节点
        root.right = constructMaximumBinaryTree(rightNums);// 右节点
        return root;
    }
    // 看右边的注解，发现实际上，是前序遍历  跟 -> 左 -> 右
    // 事实上，也应该是前序遍历，因为要先构建出来根节点，然后才能构建左子树、右子树。

    // 另外，递归技巧
    // 一般情况来说：如果让空节点（空指针）进入递归，就不加if，
    //             如果不让空节点进入递归，就加if限制一下， 终止条件也会相应的调整。
    //     本题目中 root.left = constructMaximumBinaryTree(leftNums);
    //             说明是允许空节点进入递归，所以终止条件就是if if (nums.length == 0) { return null;}
    //             如果不允许空节点进入，则递归终止条件，相应的要改为当遇到叶子节点时退出。

}
