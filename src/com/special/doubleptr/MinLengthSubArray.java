package special.doubleptr;

public class MinLengthSubArray {

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
     *
     * 示例：
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */


    /**
     *
     * 滑动窗口，是双指针的一种
     * 重点是：窗口内是什么？窗口何时移动初始位置、窗口合适移动结束为止
     * 对应本题：窗口内是满足其和 ≥ s 的长度最小的 连续 子数组
     *         窗口左移：窗口内子数组的和大于S，要缩小窗口，所以左移
     *         窗口右移：窗口内子数组的和小于S，要扩大窗口，所以右移
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int tempSum = 0;

        int lIndex = 0, rIndex = 0;
        while (rIndex < nums.length) {
            tempSum += nums[rIndex];
            /**
             * 一开始我写不对，因为把窗口左移放入操作，放入了外层的大while，导致while循环中，控制跳出的条件迟迟无法写出
             * todo 事实再次证明，【while循环只控制一件事情的跳出】，【多件事情，可以分开去控制】
             */
            while(tempSum >= target) {
                minLength = Math.min(minLength, rIndex - lIndex + 1);
                tempSum -= nums[lIndex]; // 减去左指针指向的值，便于右指针下一步右移
                lIndex++;
            }
            rIndex++;
        }
        return minLength == Integer.MAX_VALUE ? 0: minLength;
    }
    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2,3,1,2,4,3};

//        int target = 4;
//        int[] nums = {1,4,4};
//
//        int target = 11;
//        int[] nums = {1,1,1,1,1,1,1,1};

//        int target = 11;
//        int[] nums = {1,2,3,4,5};

        MinLengthSubArray m = new MinLengthSubArray();
        System.out.println(m.minSubArrayLen(target, nums));
    }



    // 暴力法，
    // 时间复杂度为O(n^2)
    // 空间复杂度O(1)
    public int minSubArrayLen1(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        boolean canSum = false;

        for (int i =0; i < nums.length; i++) {
            int subArraySum = 0;
            for (int j = i; j < nums.length; j++) {
                subArraySum += nums[j];
                if (subArraySum >= target) {
                    canSum = true;
                    minLength = Math.min(minLength, j - i + 1);
                    break;
                }
            }
        }
        return canSum ? minLength : 0;
    }
}
