package special.BinarySearch;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoubleNumBinarySearch {

    // 求sqrt2， 不可以使用自带的函数
    public static double sqrt(double target) {
        double left = 0;
        double right = Math.max(1, target);

        while (right - left > 1e-8) {
            double mid = (left + right) / 2;
            if (mid * mid > target) {
                right = mid;// 浮点数，不需要处理边界问题，因为每次进入下一层循环，区间都是变为原来的1/2;
            } else {
                left = mid;
            }
        }
        // todo  "%.6f" 控制打印精度多少位
        System.out.println(String.format("%.6f", left));
        return left;
    }

    public static void main(String[] args) {
        DoubleNumBinarySearch.sqrt(2);
    }


}
