package com.leetcode.binary_tree;

public class BSTSearchInBinarySearchTree {

    // 二叉搜索树，他的特性决定了，按照左->中->右的顺序遍历（即中序遍历），遍历结果是有序的。

    /**
     * 给定二叉搜索树（BST）的根节点和一个值。
     * 你需要在BST中找到节点值等于给定值的节点。返回以该节点为根的子树。
     * 如果节点不存在，则返回 NULL。
     */

    public TreeNode searchBST(TreeNode root, int val) {
        // 终止条件
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }
        //搜索子树时，可以简化一点，
        // 因为是二叉搜索树，所以左子树的节点都是小于根节点
        // 右子树的节点都是大于根节点
        if (root.val > val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);//搜索右子树
    }



    // 二叉搜索树，在写迭代的方法时，不需要回溯了，
    // 因为由于节点有序，有序的节点，已经指明了查找的方法，不需要【回溯尝试其他】方向
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val)
                return root;
            if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }



}
