public class Sqrt2 {

    /**
     *
     *
     * @param n         所求根号数
     * @param scale     保存的小数点后几位
     * @return
     */
    public double sqrt(int n, int scale) {
        double ss = 1.0;
        while (scale > 0) {
            ss = ss / 10;
            scale--;
        }
        System.out.println("保存的位数 = " + ss);
        double left = 0;
        double right = 2;

        while (right - left > ss) {
            double mid = (left + right) / 2;
            if (mid * mid <= n) {
                left = mid;
            } else if (mid * mid > n) {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int n = 2;
        Sqrt2 ss = new Sqrt2();
        System.out.println(ss.sqrt(2, 4));
    }
}
