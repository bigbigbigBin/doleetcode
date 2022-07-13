package com.leetcode.binary_tree;

import java.lang.reflect.InvocationTargetException;

public class Test {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // 借助前序遍历
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        if (sb.length() > 0)
            sb.deleteCharAt(0);
        return sb.toString();
    }

    private StringBuilder serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return sb;
        }

        sb.append(",").append(root.val);

        serialize(root.left, sb);
        serialize(root.right, sb);

        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] array = data.split(",");
        return deserialize(array, 0, array.length - 1);
    }

    private TreeNode deserialize(String[] data, int start, int end) {
        if (start <= end) {
            int value = Integer.parseInt(data[start]);
            TreeNode root = new TreeNode(value);
            int i = start +1;
            for (; i <= end; i++) {
                if (Integer.parseInt(data[i]) < Integer.parseInt(data[start])) {
                    continue;
                } else {
                    break;
                }
            }
            root.left = deserialize(data, start + 1, i - 1);
            root.right = deserialize(data, i, end);
            return root;
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Integer[] array = {2,1,3};
        TreeNode root = ConstructBinaryTreeOfLevelOrder.constructTree(array, TreeNode.class);

        Test tt = new Test();
        String data = tt.serialize(root);
        System.out.println("序列化之后  = " + data);
        TreeNode newNode = tt.deserialize(data);
    }

}
