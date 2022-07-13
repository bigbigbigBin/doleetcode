package com.leetcode.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversalFromRight {

    /**
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 输入:[1,2,3,null,5,null,4]
     * 输出:[1,3,4]
     *
     * 输入:[1,null,3]
     * 输出:[1,3]
     *
     * 输入:[]
     * 输出:[]
     */

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


    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);

        List<Integer> rightSideView = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0 ; i < size; i++) {
               TreeNode cur = queue.poll();
               if (i == size -1) {
                   rightSideView.add(cur.val);
               }

               if (cur.left != null) {
                   queue.add(cur.left);
               }
               if (cur.right != null) {
                   queue.add(cur.right);
               }
           }
        }
        return rightSideView;
    }

}
