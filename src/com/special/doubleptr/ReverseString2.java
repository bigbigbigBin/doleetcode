package special.doubleptr;

public class ReverseString2 {

    /**
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     *
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     *
     */


    /**
     * 其实在遍历字符串的过程中，只要让 i += (2 * k)，i 每次移动 2 * k 就可以了
     * 因为要找的也就是每2 * k 区间的起点，这样写，程序会高效很多。
     * 所以当需要固定规律一段一段去处理字符串的时候，要想想在在for循环的表达式上做做文章。
     *
     * 双指针提现在swap函数上
     */
    public String reverseStr(String s, int k) {
        char[] array = s.toCharArray();
        int i = 0;
        for ( ; i < array.length; i = i + 2*k) {
            if (i + k <= array.length) {
                swap(array, i, i+k - 1);
            } else {
                swap(array, i, array.length - 1);
            }
        }
        return String.valueOf(array);
    }

    /**
     * 双指针交换
     */
    private void swap(char[] array, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
//        String s = "abcdefghijk";
//        int k = 3;
//        String s = "a";
//        int k = 2;
//        String s = "abcdefg";
//        int k = 2;
        String s = "abcd";
        int k = 4;
        ReverseString2 rr = new ReverseString2();
        System.out.println("s1 = " + rr.reverseStr(s, k));
        System.out.println("s2 = " + rr.reverseStr2(s, k));
    }


    // 全程模拟题目的规律，方法不简练，
    public String reverseStr2(String s, int k) {
        int count = 0;
        char[] array = s.toCharArray();

        /**
         * 其实在遍历字符串的过程中，只要让 i += (2 * k)，i 每次移动 2 * k 就可以了，然后判断是否需要有反转的区间。
         * 因为要找的也就是每2 * k 区间的起点，这样写，程序会高效很多。
         * 所以当需要固定规律一段一段去处理字符串的时候，要想想在在for循环的表达式上做做文章。
         */
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (count % (2 * k) == 0) {
                int reverseStartIndex = count - 2 * k;
                for (int j = reverseStartIndex; j < reverseStartIndex + k/2; j++) {
                    char temp = array[j];
                    array[j] = array[reverseStartIndex + k - (j-reverseStartIndex) - 1];
                    array[reverseStartIndex + k - (j-reverseStartIndex) - 1] = temp;
                }
            }
        }
        if (s.length() % (2*k) != 0) {
            int startIndex = s.length() - s.length() % (2*k);
            int newK = Math.min(s.length() % (2*k), k);
            int endIndex = startIndex + newK;
            for (int i = startIndex; i < startIndex + newK / 2 ; i++) {
                char temp = array[i];
                array[i] = array[endIndex - (i-startIndex) - 1];
                array[endIndex - (i-startIndex) - 1] = temp;
            }
        }
        return String.valueOf(array);
    }
}
