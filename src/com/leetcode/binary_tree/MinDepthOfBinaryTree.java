package com.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepthOfBinaryTree {

    // 最小深度是从根节点到最近【叶子节点】的最短路径上的节点数量。
    // 所以一定的要判断到是叶子节点，即 left==null && right == null

    // 迭代法
    public int minDepth1(TreeNode root) {
        int minDepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            minDepth++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return minDepth;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return minDepth;
    }

    // 递归法
    public int minDepth(TreeNode root) {
        return getDepth(root, 0);
    }

    public int getDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;

        int leftDepth = getDepth(root.left, depth);
        int rightDepth = getDepth(root.right, depth);

        // 不能像求最大深度那样直接递归，必须要分情况讨论，原因还是因为
        // 最小深度是从根节点到最近【叶子节点】的最短路径上的节点数量。
        // 所以一定的要判断到是叶子节点，即 left==null && right == null
        if (root.left == null && root.right == null) {
            return depth;
        }

        if (root.left == null && root.right != null) {
            return rightDepth;
        }

        if (root.left != null && root.right == null) {
            return leftDepth;
        }

        return Math.min(leftDepth, rightDepth);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
