package com.leetcode.binary_tree;

public class BSTConvertArrayToBST {

    /**
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * 高度平衡二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     *
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     *
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
     */

    // 不用过于纠结题目所说的平衡二叉树，因为大家默认都是从数组中间位置取值作为节点元素，一般不会随机取，所以想构成不平衡的二叉树是自找麻烦。

    // 根据数组构造一颗二叉树。 本质就是寻找分割点，分割点作为当前节点，然后递归左区间和右区间。

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int startIndex, int endIndex) {
        // 递归终止条件
        if (startIndex > endIndex) {
            return null;
        }

        int mid = startIndex + (endIndex - startIndex) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortedArrayToBST(nums, startIndex, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, endIndex);
        return root;
    }

}
