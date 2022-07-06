package special.BinarySearch;

public class IntNumBinarySearch {
    /**
     * 题目链接：https://leetcode-cn.com/problems/binary-search/
     *
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     */

    // 非递归版本
    public int searchNotRecursive(int [] nums, int target) {
        int lIndex = 0, rIndex = nums.length-1;
        int midIndex;

        while (lIndex <= rIndex) {
            midIndex = (rIndex - lIndex) / 2 + lIndex;
            if (nums[midIndex] == target) {
                return midIndex;
            } else if (nums[midIndex] < target) {
                lIndex = midIndex +1;
            } else {
                rIndex = midIndex -1;
            }
        }
        return -1;
    }


    public int searchRecursive(int [] nums, int target) {
        // 递归版本
        return binarySearch(nums, target, 0, nums.length-1);
    }

    /**
     * 递归版本的二分查找：
     *  时间复杂度分析：
         *  总共执行了多少次递归了呢？
         *  极端情况，会一直往一个方向不停的递归，每次递归的值，为 1/2 倍。
         *  那么以供会递归多少次呢？
         *  假设 nums.length = n；
         *  那么 n/2/2/……/2 = 1 ?什么时候会成立？
         *  即 n / 2^m = 1 成立时，m多大？
         *     2^m = n  ====> 两边同时取对数 m = logN
         *  所以时间复杂度 = O（logN）
     *
     *  那么空间复杂度呢？
         *  由于是递归，所以递归的最大深度即为所占最大内存。
         *  入参为一个数组，大小为n，每次递归过程中，都会申请一个空间存放midIndex，所以每次递归空间复杂度为O（n + 1）
         *  由于最大递归层数为logN , 所以空间复杂度即为  O(logN * (N+1))
         *  由于 (NlogN + logN) 按照数量级，可以统一用NlogN表示，
         *  所以最后的空间复杂度为 O(NlogN)
     * @return
     */
    public int binarySearch(int[] nums, int target, int lIndex, int rIndex) {
        // 先考虑递归终止条件
        if (rIndex < lIndex) {
            return -1;
        }

        int midIndex = (rIndex - lIndex) / 2 + lIndex;

        if (nums[midIndex] == target) {
            return midIndex;
        } else if (nums[midIndex] > target) {
            // 说明target 在较小部分
            rIndex = midIndex - 1;
            return binarySearch(nums, target, lIndex, rIndex);
        } else {
            // 说明target在较大部分
            lIndex = midIndex +1;
            return binarySearch(nums, target, lIndex, rIndex);
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;

//        int[] nums = {-1,0,3,5,9};
//        int target = 9;

//        int[] nums = {5};
//        int target = 5;

        IntNumBinarySearch binarySearch = new IntNumBinarySearch();
        System.out.println(binarySearch.searchNotRecursive(nums, target));
    }

}
