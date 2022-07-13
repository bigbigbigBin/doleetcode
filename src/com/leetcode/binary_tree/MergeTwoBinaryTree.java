package com.leetcode.binary_tree;

import java.lang.reflect.InvocationTargetException;

public class MergeTwoBinaryTree {

    /**
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     *
     * 你需要将他们合并为一个新的二叉树。
     * 合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
     * 否则不为 NULL 的节点将直接作为新二叉树的节点。
     * */

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode newRoot = new TreeNode();
        return preOrderTraversal(root1, root2, newRoot);

//        下面这个写法是错误的，因为java是值传递，newRoot（假设此刻指向Obj1）的引用被拷贝了一份（假设为newRoot2）
//        在方法中，我只是将newRoot2指向的对象，由原来指向Obj1，改为了指向root2，newRoot的指向还是Obj1，所以直接返回Obj1是错误的。
//        preOrderTraversal(root1, root2, newRoot);
//        return newRoot;
    }

    public TreeNode preOrderTraversal(TreeNode root1, TreeNode root2, TreeNode newRoot) {
        if (root1 == null && root2 == null) {
            return null;
        }

        if (root1 != null && root2 != null) {
            if (newRoot != null) {
                newRoot.val = root1.val + root2.val;
            } else{
                newRoot = new TreeNode(root1.val + root2.val);
            }
        } else if (root1 != null && root2 == null) {
            newRoot = root1;
            return newRoot;
        } else if (root1 == null && root2 != null) {
            newRoot = root2;
            return newRoot;
        }

        newRoot.left = preOrderTraversal(root1.left, root2.left, newRoot.left);
        newRoot.right = preOrderTraversal(root1.right, root2.right, newRoot.right);
        return newRoot;
    }

    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Integer[] root1 = {};
        Integer[] root2 = {1};
        TreeNode tree1 = ConstructBinaryTreeOfLevelOrder.constructTree(root1, TreeNode.class);
        TreeNode tree2 = ConstructBinaryTreeOfLevelOrder.constructTree(root2, TreeNode.class);
        MergeTwoBinaryTree mm = new MergeTwoBinaryTree();
        mm.mergeTrees(tree1, tree2);
    }
}
