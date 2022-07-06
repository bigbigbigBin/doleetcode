package array;

import java.util.Arrays;

public class RemoveElement {
    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3]
     * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
     *
     */

    /**
     * 暴力求解
     * 时间复杂度为O(n^2)
     * 空间复杂度为O(1)
     */
    public int removeElement(int[] nums, int val) {
        int totalValNum = 0;
        int i = 0;
        while(i < nums.length - totalValNum) {
            if (nums[i] == val) {
                totalValNum++;
                for (int j = i+1; j < nums.length; j++) {
                    nums[j-1] = nums[j];
                }
            } else {
                i++;
            }
        }
        return nums.length - totalValNum;
    }


    /**
     *
     *  没想出来的办法，需要再次记忆
     * 双指针；
     * 慢指针递增，每个位置存的必定是 !=val 的值。
     * 快指针，每次都要走一次，每次用快指针来判断值是否为val，不为val才将其复制给慢指针的为孩子
     *
     */
    public int removeElement2(int[] nums, int val) {

        int slowIndex = 0;

        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }



    public static void main(String[] args) {
        int[] nums = {0,1,2,2,2,3,0,4,2};
        int val = 2;

//        int[] nums = {0,0,0};
//        int val = 0;

//        int[] nums = {0};
//        int val = 0;

        RemoveElement removeElement = new RemoveElement();

        System.out.println(removeElement.removeElement2(nums, val));
        System.out.println(Arrays.toString(nums));

    }
}
