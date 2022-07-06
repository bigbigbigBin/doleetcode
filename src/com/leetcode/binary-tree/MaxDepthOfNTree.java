import java.util.List;

public class MaxDepthOfNTree {

    /**
     * 给定一个 N 叉树，找到其最大深度。
     *
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     *
     * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
     *
     * 输入：root = [1,null,3,2,4,null,5,6]    PS :输入中，每一层的结尾用null表示
     * 输出：3
     */

    // 递归法
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return getDepth(root, 1);
    }

    public int getDepth(Node root, int depth) {
        int curDepth = depth;
        if (root == null) {
            return curDepth;
        }
        curDepth++;

        for (Node child : root.children) {
            depth = Math.max(depth, getDepth(child, curDepth));
        }
        return depth;
    }

    // 迭代法，使用层序遍历

    class Node {
        public int val;
        public List<Node> children;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
