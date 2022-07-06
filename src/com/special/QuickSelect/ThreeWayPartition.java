package special.QuickSelect;

public class ThreeWayPartition {

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
    private void threeWayPartition(int[] array, int left, int right) {
        int pivot = array[left];
        int i = 0; // 记录左边的小值应该放入的位置。
        while (left < right) {
            if (array[left] < pivot) {
                swap(array, left, i);
                left++;
                i++;
            } else if (array[left] > pivot) {
                swap(array, right, i);
                right--;
            } else { // 即 array[left] == pivot
                i++;
            }
        }
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
