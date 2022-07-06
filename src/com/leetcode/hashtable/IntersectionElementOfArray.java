package hashtable;

import java.util.*;
import java.util.stream.IntStream;

public class IntersectionElementOfArray {
    /**
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。
     * 我们可以 不考虑输出结果的顺序 。
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     *
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     * 解释：[4,9] 也是可通过的
     */
    // 为什么可以用set（或者map）来存储i
    //
    //  首先看Integer 的hashCode方法、equals方法；
    //  public static int hashCode(int value) {
    //        return value;
    //    }

    //    public boolean equals(Object obj) {
    //        if (obj instanceof Integer) {
    //            return value == ((Integer)obj).intValue();
    //        }
    //        return false;
    //    }

    // 在看set（或者map）的contains方法，发现如下：除了key ==之外，还会判断equals是否相等
    // set(或者map)的contains方法，源码如下：
    //  if (first.hash == hash &&
    //        ((k = first.key) == key || (key != null && key.equals(k))))
    //
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        HashSet<Integer> resSet = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                resSet.add(i);
            }
        }
        // 用lambda表达式，来简单的转换
        return resSet.stream().mapToInt(Integer::intValue).toArray();
    }


    public static void main(String[] args) {
        int[] nums1 = {4,9,5}, nums2 = {9,4,9,8,4};
//        int[] nums1 = {1,2,2,1}, nums2 = {2,2};
        IntersectionElementOfArray intersectionElementOfArray = new IntersectionElementOfArray();
        System.out.println(Arrays.toString(intersectionElementOfArray.intersection(nums1, nums2)));
    }

}
