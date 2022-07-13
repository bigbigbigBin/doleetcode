package com.leetcode.binary_tree;

public class BSTDeleteNodeInBST {

    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；如果找到了，删除它。说明：要求算法时间复杂度为 O(h)，h 为树的高度。
     *
     * */

    // 和添加元素不一样，在二叉搜索树中添加元素，可以只在叶子节点添加元素来保证仍然是二叉搜索树，不用调整树的结构
    // 但是删除元素，需要调整树的结构

    // 递归函数有返回值，所以【上一层的递归】要【接收】【本层的返回值】来处理
    public TreeNode deleteNode(TreeNode root, int key) {
        // 终止条件
        // 1、如果没有找到想要删除的节点，则本次递归到达了最下层，返回null
        if (root == null) {
            return null;
        }

        // 2、找到了节点，且恰好是叶子节点
        if (root.val == key && root.left == null && root.right == null) {
            return null;
        }

        // 3、如果找到了节点，但是左子树为null，则删除本节点，然后让右子树的头结点顶替在删除位置上
        if (root.val == key && root.left == null) {
            return root.right;
        }

        // 4、找到了节点，但是右子树为null，则删除本节点，然后让左子树的头结点顶替在删除位置上
        if (root.val == key && root.right == null) {
            return root.left;
        }

        // 5、找到了节点，且左右子树都不为null，则将左子树连接到，右子树的最左节点处，让原来的左子树成为右子树的最左节点的左子树
        if (root.val == key && root.left !=null && root.right != null) {
            // 5.1找到右子树的最左节点
            TreeNode leftNodeOfRightTree = root.right;
            // 因为二叉搜索树的有序特点，所以左子树节点一定小于右子树节点
            while (leftNodeOfRightTree.left != null) {
                leftNodeOfRightTree = leftNodeOfRightTree.left;
            }
            // 5.2将左子树，连接在右子树的最左节点的左子树位置
            leftNodeOfRightTree.left = root.left;
            // 5.3返回右子树的根节点，供上层递归抓住处理
            return root.right;
        }

        if (root.val > key)
            root.left = deleteNode(root.left, key);
        else
            root.right = deleteNode(root.right, key);
        return root;
    }
}
