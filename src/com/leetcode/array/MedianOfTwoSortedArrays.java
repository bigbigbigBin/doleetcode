package array;

public class MedianOfTwoSortedArrays {

    // 求两个有序数组的中位数

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int totalLength = l1 + l2;
        if (totalLength % 2 == 0) {
            return (getKthNum(nums1, nums2, totalLength / 2) + getKthNum(nums1, nums2, totalLength / 2 + 1)) / 2.0;
        } else {
            return getKthNum(nums1, nums2, totalLength / 2 + 1);
        }
    }

    // K 从1开始
    private int getKthNum(int nums1[], int nums2[], int k) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        //  前k个数，有可能nums1中有 k/2个数据，nums2中有k/2个数
        int nums1Left = 0;
        int nums2Left = 0;
        while(k > 1) {
            if (nums1Left >= l1) {
                return nums2[nums2Left + k - 1];  // k是在一直在缩小的
            }
            if (nums2Left >= l2) {
                return nums1[nums1Left + k - 1];
            }
            int nums1Mid = Math.min(nums1Left + k / 2, l1) - 1;   // 为了防止越界
            int nums2Mid = Math.min(nums2Left + k / 2, l2) - 1;

            if (nums1[nums1Mid] <= nums2[nums2Mid]) {
                // 排除nums1Left--mid位置的数据，
                k = k - (nums1Mid - nums1Left + 1); // 不能直接减去k/2，因为有时候长度不到k/2，数据就已经到尾巴了
                nums1Left = nums1Mid + 1;
            } else {
                k = k - (nums2Mid - nums2Left + 1);
                nums2Left = nums2Mid + 1;
            }
        }
        if (nums1Left >= l1) {
            return nums2[nums2Left + k - 1];  // k是在一直在缩小的
        } else if (nums2Left >= l2) {
            return nums1[nums1Left + k - 1];
        }
        return Math.min(nums1[nums1Left], nums2[nums2Left]);
    }

    public static void main(String[] args) {
        int[] array1 = {1,2};
        int[] array2 = {3,4};

        MedianOfTwoSortedArrays mm = new MedianOfTwoSortedArrays();

        System.out.println(mm.findMedianSortedArrays(array1, array2));
    }
}
