package array;

import java.util.Arrays;

public class RemoveRepeatableElement {

    //要求：不使用额外空间
    // [1,1,2,2,3,4]
    public int removeElement(int[] A) {
        int size = A.length;
        int preIndex = 0;
        int lastIndex = 1;
        while (lastIndex < size) {
            if (A[preIndex] == A[lastIndex]) { // 说明有重复元素
                remove(A, lastIndex, lastIndex+1, size);
                size--;
            } else {
                preIndex++;
                lastIndex++;
            }
        }
        return size;
    }

    private void remove(int[] A, int baseIndex, int startIndex, int size) {
        for (int i = startIndex; i < size; i++) {
            A[baseIndex] = A[i];
            baseIndex++;
        }
    }

    public static void main(String[] args) {
//        int[] array = {1,1,2,2,3,4};
        int[] array = {1,1,2};
        RemoveRepeatableElement rr = new RemoveRepeatableElement();

        System.out.println(rr.removeElement(array));
        System.out.println(Arrays.toString(array));
    }
}
