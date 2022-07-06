package array;

public class KthLargestElementInAnArray {
    // 第K大的元素
    // 理解快速排序的思想

    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length -1, k);
    }

    public int quickSort(int[] nums, int leftIndex, int rightIndex, int k) {
        int left = leftIndex;
        int right = rightIndex;
        int baseValue = nums[left];

        while(left < right) {
            while(right > left && nums[right] >= baseValue) {
                right--;
            }
            if (right > left) {
                nums[left] = nums[right];
                left++;
            }
            while(left < right && nums[left] < baseValue) {
                left++;
            }
            if (right > left) {
                nums[right] = nums[left];
                right--;
            }
        }
        nums[left] = baseValue;
        if (left < nums.length - k) {
            return quickSort(nums, left + 1, rightIndex, k);
        } else if (left > nums.length - k) {
            return quickSort(nums, leftIndex, left - 1, k);
        } else {
            return nums[left];
        }
    }

    public static void main(String[] args) {
        int[] nums = {7,6,5,4,3,2,1};
        int k = 2;
        KthLargestElementInAnArray kk = new KthLargestElementInAnArray();
        System.out.println(kk.findKthLargest(nums, k));
    }

}
