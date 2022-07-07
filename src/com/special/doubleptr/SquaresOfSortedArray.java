package special.doubleptr;

import java.util.Arrays;

public class SquaresOfSortedArray {

    /**
     *
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     *
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     *
     */

    /**
     *  最优解，使用双指针
     *
     *  新排好序的数组，最大值必然是原数组最左端元素或者最右端元素，不可能是中间元素。
     *  此时考虑双指针，i指向原数组起始位置，j指向原数组终止位置。
     *  newNumMaxIndex 始终指向新数组中最大位置
     */
    public int[] sortedSquares3(int[] nums) {
        int[] newNum = new int[nums.length];
        int newNumMaxIndex = newNum.length - 1;
        int i =0, j = nums.length -1;

        while (newNumMaxIndex >= 0) {
            int leftValue = nums[i] * nums[i];
            int rightValue = nums[j] * nums[j];

            if (leftValue > rightValue) {
                newNum[newNumMaxIndex--] = leftValue;
                i++;
            } else {
                newNum[newNumMaxIndex--] = rightValue;
                j--;
            }
        }
        return newNum;
    }

    public static void main(String[] args) {
        int[] nums = {-7,-3,2,3,11};
//        int[] nums = {1};
//        int [] nums = {-4,-1,0,3,10};
//        int [] nums = {-5,-3,-2,-1};
//        int [] nums = {-5,-3,-2,1};
        SquaresOfSortedArray s = new SquaresOfSortedArray();
        System.out.println(Arrays.toString(s.sortedSquares3(nums)));
    }




    /**
     * 暴力法：每个数平方，然后排序。
     *
     * 下面这个写法，本质上还是上述路子，我自己设想的归并排序，并没有跳出这个框架。
     * 所以我自己的这个方法，本质还是暴力排序。
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        // 第一步，先找出第一个>=0的数的位置；
        int positiveIndex = -1;
        for (int i =0 ;i < nums.length; i++) {
            if (nums[i] >=0) {
                positiveIndex = i;
                break;
            }
        }
//        [0, positiveIndex) // 为负数；
//        [positiveIndex, nums.length-1] // 为正数；
        // 第二步，两个子数组分别求平方；
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        // 第三步，把数组切分为两个子数组；
        int[] negativeArray = null;
        int[] positiveArray = null;
        if (positiveIndex > 0) {
            negativeArray = Arrays.copyOfRange(nums, 0 , positiveIndex);
            positiveArray = Arrays.copyOfRange(nums, positiveIndex, nums.length);
        } else if (positiveIndex == 0) {
            return nums;
        } else {
            negativeArray = nums;
        }

        if (negativeArray != null) {
            int i = 0;
            int negativeLastIndex = positiveIndex < 0 ? nums.length - 1 : positiveIndex -1;
            while(i <= negativeLastIndex / 2) {
                int temp = negativeArray[i];
                negativeArray[i] = negativeArray[negativeLastIndex - i];
                negativeArray[negativeLastIndex - i] = temp;
                i++;
            }
        }
        if (positiveIndex < 0) {
            return negativeArray;
        }

        // 第五步， 归并排序；
        return mergeSort(negativeArray, positiveArray);
    }

    public int[] mergeSort(int[] num1, int[] num2) {
        int i = 0, j = 0;
        int[] newNum = new int[num1.length + num2.length];
        int newIndex = 0;
        while(i < num1.length && j < num2.length) {
            if (num1[i] <= num2[j]) {
                newNum[newIndex++] = num1[i++];
            } else {
                newNum[newIndex++] = num2[j++];
            }
        }

        for (; i < num1.length; i++) {
            newNum[newIndex++] = num1[i];
        }

        for (; j < num2.length; j++) {
            newNum[newIndex++] = num2[j];
        }
        return newNum;
    }

    /**
     *  同样是暴力排序，采用API排序
     *
     */
    public int[] sortedSquares2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        Arrays.sort(nums);
        return nums;
    }
}
