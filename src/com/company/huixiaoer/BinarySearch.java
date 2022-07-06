package huixiaoer;

public class BinarySearch {

    public int find(double[] nums, double target, int n) {
        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] - target == 0.0) {
                return mid;
            } else if (nums[mid] > target) { // 说明target在左边
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bb = new BinarySearch();
        int index = bb.find(new double[] { 1, 2, 11 ,20 ,25}, 20, 4);
        System.out.println(index);
    }
}
