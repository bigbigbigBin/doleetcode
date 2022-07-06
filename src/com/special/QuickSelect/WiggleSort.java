package special.QuickSelect;

import java.util.Arrays;

public class WiggleSort {
    /**
     * 摆动排序II
     * 给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
     *
     * 你可以假设所有输入数组都可以得到满足题目要求的结果。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/wiggle-sort-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    // 摆动排序
    // 快速选择算法 + 3路划分法
    public void wiggleSort(int[] nums) {
        int k = nums.length / 2;
        // 中位数的索引
        int midIndex = quickSelect(nums, 0, nums.length - 1, k);
        // 中位数
        int mid = nums[midIndex];

//        System.out.println("midIndex = " + midIndex + " mid = " + mid);
//        System.out.println("快速选择 k 之后 nums = " + Arrays.toString(nums));

        threeWayPartition(nums, 0, nums.length - 1, mid);

//        System.out.println("3路划分之后 nums = " + Arrays.toString(nums));

        if (nums.length % 2 == 1) {
            midIndex++;
        }

        int[] subnum1 = Arrays.copyOfRange(nums, 0, midIndex);
        int[] subnum2 = Arrays.copyOfRange(nums, midIndex, nums.length);

//        System.out.println(Arrays.toString(subnum1));
//        System.out.println(Arrays.toString(subnum2));

        for (int i = 0; i < subnum1.length; i++) {
            // subnum1 、 subnum2 需要倒叙，但是此处，直接用下标控制，避免再次倒叙排列花费的时间
            nums[2 * i] = subnum1[subnum1.length - 1 - i];
        }

        for (int j = 0; j < subnum2.length; j++) {
            nums[2 * j + 1] = subnum2[subnum2.length - 1 - j];
        }
    }


    /**
     * 快速选择算法，基于快速排序算法的思想，解决Top K问题的算法。
     *
     * @param array  数组
     * @param begin  数组的起始点
     * @param end    数组的结束点
     * @param k      选择多少个
     * @return
     */
    public int quickSelect(int[] array, int begin, int end, int k) {

        int pivot = array[begin];
        int i = begin, j = end;

        while (i < j) {
            while(j > i && array[j] >= pivot) {
                j--;
            }
            if (j > i) {
                array[i] = array[j];
                i++;
            }
            while(i < j && array[i] <= pivot) {
                i++;
            }
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }

        // 基准值归位
        array[i] = pivot;

        if (i > k) { //
            return quickSelect(array, begin, i - 1, k);
        } else if (i < k) {
            return quickSelect(array, i + 1, end, k);
        }
        return i;
    }




    /**
     * 快速排序，当num[left] <= pivot，不会对数组进行任何处理。
     * 这意味着当pivot左侧出现了某个数，值等于pivot时，也不会进行处理。
     *
     * 假设数组中有重复数字，那么可能排序后的数组中，数字只能是严格的2部分：
     * 一部分是小于等于pivot， 一部分是大于povit。
     * 但是小于等于pivot的这部分数据，里面的数字有可能不是严格再次能划分的部分，小于、等于pivot的数字不是聚集在一起的。
     * [5, 5, 3, 2, 6, 7, 8]    ===>    [2, 5, 3, 5, 7, 8, 6]    这个例子可看出 5左边还有2、5、3，5不是聚集在一起
     *
     * 原版partition过程中，实际上遍历过程分为以下三种情况：
     * arr[left] < pivot：不操作(相当于放到pivot左边)
     * arr[left] == pivot：不操作(所以左边会出现和pivot相等的值，且可能与pivot不相邻)
     * arr[left] > pivot：将arr[left]这个较大的值换到右边去
     *
     *
     * 三路划分就是为了解决这个场景
     * 让nums所有和pivot相等的值都在中间部分，这样nums按照大小顺序就可以严格分成三部分，此时就需要使用到3-way-partition算法
     *
     * 为了实现严格将nums划分为三部分，
     * 在arr[left] < pivot时不仅仅是将其放到pivot左边(即不操作)，而是要将其放到最左边，使其尽量远离pivot，
     * 这样一轮划分下来，所有小于pivot的值都会集中在数组最左边，实现严格的划分，具体代码如下：
     *
     * 时间复杂度 O(n)
     *
     * @param array
     * @param left
     * @param right
     */
    private void threeWayPartition(int[] array, int left, int right, int pivot) {
//        int pivot = array[left];
        int i = 0; // 记录左边的小值应该放入的位置。
        while (left < right) {
            if (array[left] < pivot) {
                swap(array, left, i);
                left++;
                i++;
            } else if (array[left] > pivot) {
                swap(array, right, left);
                right--;
            } else { // 即 array[left] == pivot
                left++;
            }
        }
    }


    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,2,3,1};
        WiggleSort ws = new WiggleSort();
        ws.wiggleSort(nums);
        System.out.println(nums);
    }
}
