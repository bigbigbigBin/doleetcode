package special.hashtable;

import java.util.HashMap;

public class FourSumCount {

    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     *
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
     *
     * 例如:
     *
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     * 输出:
     * 2
     * 解释:
     * 两个元组如下:
     *
     * (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */

    // 不能使用暴力法O(n^4)时间复杂度太高了，必须想办法降低时间复杂度
    // 思路：
    // O(n^2) 把前两个数据的和保存起来
    // O(n^2) 判断后两个数组，找到 0 - 自身的俩数组的和 在数组中存在
    // 最终的时间复杂度为 O(n^2)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        int N = nums1.length;
        HashMap<Integer, Integer> dataMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp = nums1[i] + nums2[j];
                dataMap.put(temp, dataMap.getOrDefault(temp, 0) + 1);
            }
        }

        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                int temp = 0 - nums3[k] - nums4[l];
                if (dataMap.containsKey(temp)) {
                    count = count + dataMap.get(temp);
                }
            }
        }
        return count;
    }


     // 暴力法，时间复杂度 O(n^4) 超出时间限制
//    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
//        int N = nums1.length;
//        HashMap<Integer, Integer> dataMap1 = new HashMap<>();
//        int count = 0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                for (int k = 0; k < N; k++) {
//                    for (int l = 0; l < N; l++) {
//                        if (nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0)
//                            count++;
//                    }
//                }
//            }
//        }
//        return count;
//    }

    public static void main(String[] args) {
        int[] nums1 = {1,2}, nums2 = {-2,-1}, nums3 = {-1,2}, nums4 = {0,2};
//        int[] nums1 = {0}, nums2 = {0}, nums3 = {0}, nums4 = {0};
        FourSumCount ff = new FourSumCount();
        System.out.println(ff.fourSumCount(nums1, nums2, nums3, nums4));
    }
}
