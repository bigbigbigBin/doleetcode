package com.leetcode.binary_tree;

import java.lang.reflect.InvocationTargetException;

public class BSTMinimumDifferenceInBST {


    /**
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * 差值是一个正数，其数值等于两值之差的绝对值。
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     *
     */


    // 在递归中使用了一个指针，指向前一个最小节点
    TreeNode preNode = null;
    Integer minValue = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 左节点
        getMinimumDifference(root.left);

        int tempValue = 0;
        if (preNode == null) { // 找到最左下角节点，此节点为二叉搜索树的最小节点
            preNode = root;
        } else {
            tempValue = root.val - preNode.val;
            if (tempValue < minValue) {
                minValue = tempValue;
            }
            preNode = root;
        }

        // 右节点
        getMinimumDifference(root.right);
        return minValue;
    }

    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Integer[] aray = {9,0,48,null,null,12,49};
        TreeNode root = ConstructBinaryTreeOfLevelOrder.constructTree(aray, TreeNode.class);
        BSTMinimumDifferenceInBST bb = new BSTMinimumDifferenceInBST();
        bb.getMinimumDifference(root);
    }

}
