package com.leetcode.binary_tree;

import java.util.HashMap;
import java.util.Map;

public class PathSumValOfTree {

    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     * 解释：等于目标和的根节点到叶节点路径如上图所示。
     */

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return preTraversal(root, 0, targetSum);
    }

    public boolean preTraversal(TreeNode root, int sum, int target) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            sum += root.val;
            if (sum == target) {
                return true;
            }
        }

        boolean leftHas = false;
        if (root.left != null) {
            sum += root.val;
            leftHas = preTraversal(root.left, sum, target);
            sum -= root.val; //回溯
        }

        boolean rightHas = false;
        if (root.right != null) {
            sum += root.val;
            rightHas = preTraversal(root.right, sum, target);
            sum -= root.val; //回溯
        }
        return leftHas || rightHas;
    }
}
