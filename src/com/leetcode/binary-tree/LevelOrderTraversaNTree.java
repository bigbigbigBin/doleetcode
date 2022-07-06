import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversaNTree {

    /**
     * 给定一个 N 叉树，返回其节点值的层序遍历。(即从左到右，逐层遍历)。
     * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
     *
     * 例如，给定一个 3叉树 :
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[[1],[3,2,4],[5,6]]
     */

    class Node {
        int val;
        List<Node> children;

        public Node() {
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                curList.add(cur.val);

                if (cur.children != null && !cur.children.isEmpty()) {
                    queue.addAll(cur.children);
                }
            }
            result.add(curList);
        }
        return result;
    }

}
