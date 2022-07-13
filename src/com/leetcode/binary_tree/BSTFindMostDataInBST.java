package com.leetcode.binary_tree;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BSTFindMostDataInBST {

    /**
     * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
     * 如果树中有不止一个众数，可以按 任意顺序 返回。
     *
     * 假定 BST 满足如下定义：
     * 结点左子树中所含节点的值 小于等于 当前节点的值
     * 结点右子树中所含节点的值 大于等于 当前节点的值
     * 左子树和右子树都是二叉搜索树
     */


    // 此方法，适用于普通二叉树、二叉搜索树
    Map<Integer, Integer> dataMap = new HashMap<>();
    public int[] findMode2(TreeNode root) {
        preTraversal(root);
        int maxOccurs = 0;
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : dataMap.entrySet()) {
            if (entry.getValue() > maxOccurs) {
                maxOccurs = entry.getValue();
                if (list.isEmpty()) {
                    list.add(entry.getKey());
                } else {
                    list = new ArrayList<>();
                    list.add(entry.getKey());
                }
            } else if (entry.getValue() == maxOccurs) {
                list.add(entry.getKey());
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public void preTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        dataMap.put(root.val, dataMap.getOrDefault(root.val, 0) + 1);

        preTraversal(root.left);
        preTraversal(root.right);
    }

    // 利用二叉搜索树，避免了普通方法中，需要对结果集中进行二次搜索
    public int[] findMode(TreeNode root) {
        inOrderTraversal(root);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    int maxOccurs = 0;
    int count = 0;
    TreeNode pre = null;
    List<Integer> list = new ArrayList<>();
    public void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left); // 左

        // 根
        if (pre == null) {
            count = 1;
        } else if (pre.val == root.val) {
            count++;
        } else if (root.val > pre.val) {
            count = 1;
        }
        pre = root;  //更新前指针，所指向的节点

        if (count == maxOccurs) {
            list.add(root.val);
        } else if (maxOccurs < count) {
            maxOccurs = count; // 更新最大出现次数
            list = new ArrayList<>();
            list.add(root.val);
        }

        inOrderTraversal(root.right);// 右
    }

    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Integer[] arrays = {1,null,2,null, null,2,null};
        TreeNode root = ConstructBinaryTreeOfLevelOrder.constructTree(arrays, TreeNode.class);
        BSTFindMostDataInBST ff = new BSTFindMostDataInBST();
        ff.findMode(root);
    }

}
