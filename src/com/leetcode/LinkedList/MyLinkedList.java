package LinkedList;

import java.util.Arrays;

public class MyLinkedList {

    class LinkedList {
        int val;
        LinkedList next;
        public LinkedList() {
        }
        public LinkedList(int val, LinkedList next) {
            this.val = val;
            this.next = next;
        }
    }

    LinkedList head;
    int size;

    public MyLinkedList() {

    }

    public int get(int index) {
        if (index > size - 1 || index < 0) {
            return -1;
        }

        LinkedList tempHead = head;
        while (index>0) {
            tempHead = tempHead.next;
            index--;
        }
        return tempHead.val;
    }

    public void addAtHead(int val) {
        LinkedList newHead = new LinkedList(val, head);
        head = newHead;
        size++;
    }

    public void addAtTail(int val) {
        if (head == null) {
            head = new LinkedList(val, null);
            size++;
            return;
        }
        LinkedList tempHead = head;
        while (tempHead.next != null) {
            tempHead = tempHead.next;
        }
        tempHead.next = new LinkedList(val, null);
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
            return;
        } else if (index == size) {
            addAtTail(val);
            return;
        } else if (index > size) {
            return;
        }

        LinkedList pre = new LinkedList(0, head);
        while (index > 0) {
            pre = pre.next;
            index--;
        }
        pre.next = new LinkedList(val, pre.next);
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index > size -1) {
            return;
        }

        LinkedList pre = new LinkedList(0, head);
        LinkedList newhead = pre;
        while (index > 0) {
            pre = pre.next;
            index--;
        }
        LinkedList nextNode = pre.next.next;
        pre.next.next = null;
        pre.next = nextNode;
        head = newhead.next;
        size--;
    }

    public static void main(String[] args) {

//        ["MyLinkedList","addAtTail","get"]
//[[],[1],[0]]
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtTail(1);
        linkedList.get(0);
//        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1,2);
//        linkedList.get(1);
//        linkedList.deleteAtIndex(0);
//        linkedList.get(1);

//        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.addAtHead(2);
//        System.out.println(Arrays.toString(linkedList.array));
//        linkedList.addAtIndex(0, 1);
//
//        System.out.println(Arrays.toString(linkedList.array));
//        System.out.println(linkedList.get(1));
//
//
//        linkedList.deleteAtIndex(1);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(7);
//        linkedList.addAtHead(3);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(5);
//        linkedList.addAtTail(5);
//        System.out.println(Arrays.toString(linkedList.array));
//
//
//        System.out.println("linkedList.get(5) = " + linkedList.get(5));
//
//        linkedList.deleteAtIndex(6);
//        linkedList.deleteAtIndex(4);
//        System.out.println(Arrays.toString(linkedList.array));

//        linkedList.addAtTail(3);
//        System.out.println(Arrays.toString(linkedList.array));
//
//
//        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//
//        System.out.println(Arrays.toString(linkedList.array));
//
//        System.out.println("linkedList.get(1) = " + linkedList.get(1));            //返回2
//        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//        System.out.println(Arrays.toString(linkedList.array));
//
//        System.out.println("linkedList.get(1) = " + linkedList.get(1));           //返回3

    }
}
