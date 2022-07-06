package huixiaoer;

import java.util.List;

public class TwoSum {

    class ListNode {
        private int val;
        private ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode sum(ListNode num1, ListNode num2) {
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;

        ListNode l1 = num1;
        ListNode l2 = num2;
        int yushy = 0;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + yushy;
            int newVal = val % 10;
            yushy = val / 10;
            cur.next = new ListNode(newVal);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            if (yushy != 0) {
                int val = l1.val + yushy;
                int newVal = val % 10;
                yushy = val / 10;
                cur.next = new ListNode(newVal);
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l1.next;
                break;
            }
        }

        while (l2 != null) {
            if (yushy != 0) {
                int val = l2.val + yushy;
                int newVal = val % 10;
                yushy = val / 10;
                cur.next = new ListNode(newVal);
                cur = cur.next;
                l2 = l2.next;
            } else {
                cur.next = l2.next;
                break;
            }
        }

        if (yushy != 0) {
            cur.next = new ListNode(yushy);
        }
        // 链表此时是倒转的，所以需要翻转
        // 双指针
        dummyHead = dummyHead.next;
        ListNode right = dummyHead.next;
        while (right != null) {
            ListNode newRight = right.next;
            right.next = dummyHead;
            dummyHead.next = null;
            dummyHead = right;
            right = newRight;
        }
        return dummyHead;
    }

    private ListNode makeStringToNode(String num) {
        int length = num.length() - 1;
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while (length >= 0) {
            System.out.println("length = " + length);
            cur.next = new ListNode(num.charAt(length) - '0' );
            cur = cur.next;
            length--;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        ListNode num1 = ts.makeStringToNode("123");
        ListNode num2 = ts.makeStringToNode("1234");
        ListNode res = ts.sum(num1, num2);
        System.out.println(res);
    }
}
