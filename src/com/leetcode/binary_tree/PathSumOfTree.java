package com.leetcode.binary_tree;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PathSumOfTree {

    /**
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     */

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> path = new ArrayList<>();
        preTraversal(root, targetSum, path, result);
        return result;
    }

    public void preTraversal(TreeNode root, int targetSum, List<TreeNode> path, List<List<Integer>> result) {
        // 说明到达了叶子节点
        if (root.left == null && root.right == null) {
            targetSum -= root.val;
            path.add(root);
            if (targetSum == 0) {
                result.add(path.stream().map(treeNode -> treeNode.val).collect(Collectors.toList()));
            }
            targetSum += root.val;
            path.remove(path.size() - 1); // 到达叶子节点
        }

        if (root.left != null) {
            targetSum -= root.val;
            path.add(root);
            preTraversal(root.left, targetSum, path, result);
            targetSum += root.val; // 回溯路径和
            path.remove(path.size() - 1); //回溯路径
        }

        if (root.right != null) {
            targetSum -= root.val;
            path.add(root);
            preTraversal(root.right, targetSum, path, result);
            targetSum += root.val; // 回溯路径和
            path.remove(path.size() - 1); //回溯路径
        }
    }


    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>(); // 存放每一个节点
        Stack<List<Integer>> pathStack = new Stack<>(); // 存放每一节点的路径

        if (root != null) {
            stack.add(root);

            List<Integer> rootPath = new ArrayList<>();
            rootPath.add(root.val);
            pathStack.add(rootPath);
        }

        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = stack.pop();
                List<Integer> path = pathStack.pop();

                if (cur.left == null && cur.right == null) {
                    if (path.stream().mapToInt(Integer::intValue).sum() == targetSum) {
                        result.add(path);
                    }
                }

                if (cur.right != null) {
                    stack.add(cur.right); // 节点入栈，作为迭代

                    List<Integer> curPath = new ArrayList<>();
                    curPath.addAll(path);
                    curPath.add(cur.right.val);
                    pathStack.add(curPath); // 存入路径栈中，此节点的路径
                    // 路径回溯, 不容回溯了，因为使用了新的curPath
                }

                if (cur.left != null) {
                    stack.add(cur.left);

                    List<Integer> curPath = new ArrayList<>();
                    curPath.addAll(path);
                    curPath.add(cur.left.val);
                    pathStack.add(curPath); // 存入路径栈中，此节点的路径
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Integer[] array = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        int targetSum = 22;
        TreeNode root = ConstructBinaryTreeOfLevelOrder.constructTree(array, TreeNode.class);

        PathSumOfTree pp = new PathSumOfTree();
        pp.pathSum2(root, targetSum);


    }
}

