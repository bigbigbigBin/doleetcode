package microsoft;

import java.util.Stack;

public class MergeSort {

     class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public Node merge(Node head1, Node head2) {
        Node newHead = new Node(0, null);
        Node newlist = newHead;
        while(head1 != null && head2!= null) {
            if (head1.val < head2.val) {
                newlist.next = head1;
                head1 = head1.next;
            } else {
                newlist.next = head2;
                head2 = head2.next;
            }
            newlist = newlist.next;
        }
        if (head1 != null) {
            newlist.next = head1;
        } else {
            newlist.next = head2;
        }
        return newHead.next;
    }

    public Node prepareTestData() {
        //        int []array = {1, 10, 5, 3, 8};
        Node node1 = new Node(1, null);
        Node node10 = new Node(10, null);
        Node node5 = new Node(5, null);
        Node node3 = new Node(3, null);
        Node node8 = new Node(8, null);
        node1.next = node10;
        node10.next = node5;
        node5.next = node3;
        node3.next = node8;
        return node1;
    }

    public static void main(String[] args) {
        // 归并排序的核心思想就是，
        // 1、先对序列进行细分，分为一个个的长度为1的子序列，
        //    长度为1，必然序列【有序】。
        // 2、对有序子序列进行合并。

        // 题目为奇数位升序，偶数位降序。已经暗含了有序，因此只需合并有序子序列即可。
        MergeSort mergeSort = new MergeSort();
        // 测试数据
        Node head = mergeSort.prepareTestData();

        Node head1 = mergeSort.new Node(0, null); // 只起一个头指针作用，不参与实际排序
        Node head2 = mergeSort.new Node(0, null); // 只起一个头指针作用，不参与实际排序
        Node p = head;

        Node p1 = head1;
        Node p2 = head2;

        while (p != null) {
            p1.next = p;
            p2.next = p.next;

            p1 = p1.next; // 新链表1 游标后移
            p2 = p2.next; // 新链表2 游标后移

            p = p.next; // 原始链表的游标后移2位，为了防止空指针，中间需要做判断以便跳出
            if (p == null) {
                break;
            }
            p = p.next;
        }

        head1 = head1.next; // 删掉多余的头指针
        head2 = head2.next; // 删掉多余的头指针
        // 翻转head2, 以保证也是有序；
        Stack<Node> stack = new Stack<>();
        while(head2 != null) {
            stack.push(head2);
            head2 = head2.next;
        }
        Node newHead2 = stack.pop();
        Node currentIndex = newHead2;
        while (!stack.isEmpty()) {
            currentIndex.next = stack.pop();
            currentIndex = currentIndex.next;
        }
        currentIndex.next = null;

        // 执行合并排序，合并排序的前提必须是，子序列是有序的。且两个子序列是要么都递增，要么都递减
        Node sortedNode = mergeSort.merge(head1, newHead2);

        while (sortedNode != null) {
            System.out.print(sortedNode.val + ", ");
            sortedNode = sortedNode.next;
        }
    }
}
