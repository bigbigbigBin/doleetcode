package microsoft;

public class SwapListNode {

    //假设 1 <= a,b <= 节点总数n
//    public void swap(ListNode head, int a, int b){
//        ListNode dummyHead = new ListNode(0, head);
//        ListNode preA = dummyHead;
//        ListNode preB = dummyHead;
//
//        // 找到a的前驱节点
//        while(a > 1) {
//            preA = preA.next;
//            a--;
//        }
//
//        // 找到b的前驱节点
//        while(b > 1) {
//            preB = preB.next;
//            b--;
//        }
//        ListNode tempA = preA.next;
//        ListNode tempB = preB.next;
//        ListNode sufixA = preA.next;
//    }

    public void swap(ListNode head, int a, int b){
        ListNode dummyHead = new ListNode(0, head);
        ListNode preA = dummyHead;
        ListNode preB = dummyHead;

        if (a > b) {
            int tempa = a;
            a = b;
            b = tempa;
        }
        a = a - b;

        while(b > 1) {
            preB = preB.next;
            b--;
            if (a >= 0) {
                preA = preA.next;
            }
            a++;
        }

        ListNode nodeA = preA.next;
        ListNode nodeB = preB.next;

        int tempVal = nodeA.val;
        nodeA.val = nodeB.val;
        nodeB.val = tempVal;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8};
        SwapListNode s = new SwapListNode();

        ListNode head = s.makeListNodeFromArray(array);
        s.printListNode(head);
        s.swap(head, 3,8);
        s.printListNode(head);
    }


    ListNode makeListNodeFromArray(int[] array) {
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
