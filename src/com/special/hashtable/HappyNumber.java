package special.hashtable;

import java.util.HashSet;

public class HappyNumber {

    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * 「快乐数」定义为：
         * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
         * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
         * 如果这个过程 结果为 1，那么这个数就是快乐数。
         * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
     * 输入：n = 19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     */



    // 读题：提到无限循环，必定说明在重复上述过程中，会出现某些数组的平方和不断循环出现，
    // 所以可以用set，存放过程中每次出现的平方和，当检测到重复出现，就停止，认为当前已经不可能为快乐数了。
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n != 0) {
                int k = n % 10;
                sum += k * k;
                n = n / 10;
            }

            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            n = sum;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 210;
        HappyNumber hh = new HappyNumber();
        System.out.println(hh.isHappy(n));
    }
}
