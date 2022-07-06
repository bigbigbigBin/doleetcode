package LinkedList;

import java.util.Arrays;

public class MyLinkedList1 {

    // 使用数组实现
    /**
     * 本题目其实想考察的是链表，所以请看实现2
     */
    public int[] array = new int[16];
    public int size;

    public MyLinkedList1() {

    }

    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        return array[index];
    }

    public void addAtHead(int val) {
        int[] newArray = new int[size+1];
        System.arraycopy(array, 0, newArray, 1, size);
        newArray[0] = val;
        array = newArray;
        size++;
    }

    public void addAtTail(int val) {
        int[] newArray = new int[size+1];
        System.arraycopy(array, 0, newArray, 0, size);
        newArray[size] = val;
        array = newArray;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index <=0) {
            addAtHead(val);
            return;
        } else if (index > size) {
            return;
        }
        int[] newArray = new int[size+1];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = val;
        System.arraycopy(array, index, newArray, index+1, size - index);
        array = newArray;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index > size-1 || index < 0) {
            return;
        }
        int[] newArray = new int[size-1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size - index -1);
        array = newArray;
        size--;
    }

    public static void main(String[] args) {

        MyLinkedList1 linkedList = new MyLinkedList1();
        linkedList.addAtHead(2);
        System.out.println(Arrays.toString(linkedList.array));
        linkedList.addAtIndex(0, 1);

        System.out.println(Arrays.toString(linkedList.array));
        System.out.println(linkedList.get(1));


        linkedList.deleteAtIndex(1);
        linkedList.addAtHead(2);
        linkedList.addAtHead(7);
        linkedList.addAtHead(3);
        linkedList.addAtHead(2);
        linkedList.addAtHead(5);
        linkedList.addAtTail(5);
        System.out.println(Arrays.toString(linkedList.array));


        System.out.println("linkedList.get(5) = " + linkedList.get(5));

        linkedList.deleteAtIndex(6);
        linkedList.deleteAtIndex(4);
        System.out.println(Arrays.toString(linkedList.array));

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
