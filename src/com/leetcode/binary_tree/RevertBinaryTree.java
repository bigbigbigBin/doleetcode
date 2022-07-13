package com.leetcode.binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class RevertBinaryTree {

    // 方法一、深度优先遍历的递归版本（前序遍历、后序遍历均可）
    // 深度优先遍历的递归写法(本写法，实际上是后序遍历)
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tempLeft = invertTree(root.left);
        TreeNode tempRight =invertTree(root.right);
        root.left = tempRight;
        root.right = tempLeft;
        return root;
    }

    // 方法二、深度优先遍历的迭代版本（前序遍历、后序遍历均可）
    // 深度优先遍历迭代法
    public TreeNode invertTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.add(root);
        }

        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            TreeNode leftNode = cur.left;
            TreeNode rightNode = cur.right;

            cur.left = rightNode;
            cur.right = leftNode;

            stack.pop();

            if (cur.left != null)
                stack.add(cur.left);
            if (cur.right != null)
                stack.add(cur.right);
        }
        return root;
    }

    // 方法三、深度优先遍历的递归版本（前序遍历、后序遍历均可）
    // 还可以使用广度优先遍历
    public TreeNode invertTree3(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0 ; i < size; i++) {
                TreeNode cur = queue.poll();
                TreeNode leftNode = cur.left;
                cur.left = cur.right;
                cur.right = leftNode;

                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
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
