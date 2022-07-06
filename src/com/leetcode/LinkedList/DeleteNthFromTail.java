package LinkedList;

import java.util.List;

public class DeleteNthFromTail {

    /**
     * 描述：
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    // 快慢双指针，快指针先走n步（或者N+1步，当有虚头结点），然后在快慢指针一次只走一步，
    // 当快指针到达最尾巴时，慢指针也到了想要删除的位置
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;

        while (n >= 0) {
            fast = fast.next;
            n--;
        }
        // 快慢双指针，当fast到达末尾null节点时，slow到达想删除的节点的前一个节点
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode temp = slow.next.next;
        slow.next.next = null;
        slow.next = temp;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
//        int[] array = {1,2};
//        int[] array = {1};
//        int[] array = {};

        DeleteNthFromTail dd = new DeleteNthFromTail();
        ListNode head = dd.makeListNodeFromArray(array);
        ListNode newHead = dd.removeNthFromEnd(head, 2);
        dd.printListNode(newHead);
    }

    private ListNode makeListNodeFromArray(int[] array) {
        ListNode dummyHead = new ListNode(0, null);
        ListNode current = dummyHead;
        for (int i = 0; i < array.length; i++) {
            current.next = new ListNode(array[i], null);
            current = current.next;
        }
        return dummyHead.next;
    }

    private void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
        System.out.println();
    }


    class ListNode {
        int val;
        ListNode next;
        public ListNode() {
        }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
