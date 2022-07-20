package com.leetcode.dp.house_robber;

import com.leetcode.binary_tree.TreeNode;

public class HouseRobber3 {
    /**
     * 题目链接：https://leetcode-cn.com/problems/house-robber-iii/
     *
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     */

    // 树形dp的入门题
    // dp数组以及下标的含义：下标为0记录不偷该节点所得到的的最大金钱，下标为1记录偷该节点所得到的的最大金钱。
    public int houseRobber(TreeNode root) {
        int[] dp = houseRobber(root, new int[2]);
        return Math.max(dp[0], dp[1]);
    }

    public int[] houseRobber(TreeNode root, int[] dp) {
        if (root == null) {
            return new int[]{0,0};
        }

        int[] leftVal = houseRobber(root.left, new int[2]);
        int[] rightVal = houseRobber(root.right, new int[2]);
        // 偷父节点，那么直接子孩子不能偷
        int robberRoot = root.val + leftVal[0] + rightVal[0];
        // 不投父节点，则左右直接子孩子可能偷，也可能不投，具体要看偷与不偷的最大值

        int robberChild = Math.max(leftVal[0], leftVal[1]) + Math.max(rightVal[0], rightVal[1]);
        return new int[]{robberRoot, robberChild};
    }


//    // 暴力法
//    public int houseRobber(TreeNode root) {
//        return postOrderTraversal(root);
//    }
//
//    public int postOrderTraversal(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        if (root.left == null && root.right == null) {
//            return root.val;
//        }
//
//        int onlyRobberChild = postOrderTraversal(root.left) + postOrderTraversal(root.right);
//
//        int leftChildChildVal = 0;
//        if (root.left != null) {
//            leftChildChildVal = postOrderTraversal(root.left.left) + postOrderTraversal(root.left.right);
//        }
//
//        int rightChildChildVal = 0;
//        if (root.right != null) {
//            rightChildChildVal =postOrderTraversal(root.right.left) + postOrderTraversal(root.right.right);
//        }
//        int robberRootAndRootChildChild = root.val + leftChildChildVal + rightChildChildVal;
//        return Math.max(robberRootAndRootChildChild, onlyRobberChild);
//    }
}
