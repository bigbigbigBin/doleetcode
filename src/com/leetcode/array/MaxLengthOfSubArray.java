package array;

import java.util.HashSet;

public class MaxLengthOfSubArray {


    /**
     * 描述
     * 给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
     * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
     *
     * 数据范围：0 ≤ arr.length ≤ 10^5，0 < arr[i] ≤ 10^5
     *
     */

    // 双指针解题，此题也算作是滑动窗口
    public int maxLength (int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int i = 0;

        HashSet<Integer> set = new HashSet<>();
        set.add(arr[i]);
        int maxLength = 1;
        for (int j = 1; j < arr.length; j++) {
            //  System.out.println("进入前  i = " + i +", j = " + j);
            if (set.contains(arr[j])) {
                while (set.contains(arr[j])) { // while循环，来决定窗口移动多少距离
                    set.remove(arr[i]);
                    i++;
                }
                set.add(arr[j]);
                // System.out.println("i 发生了变化，i = " + i);
            } else {
                maxLength = maxLength < j - i +1  ? j - i +1 : maxLength;
                set.add(arr[j]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
//        int[] arr = {2,3,4,5};
        int[] arr = {2,2,3,4,3};
//        int[] arr = {9};
//        int[] arr = {1,2,3,1,2,3,2,2};
//        int[] arr = {2,2,3,4,8,99,3};
//        int[] arr = {};
        MaxLengthOfSubArray mm = new MaxLengthOfSubArray();
        System.out.println(mm.maxLength(arr));

    }
}
