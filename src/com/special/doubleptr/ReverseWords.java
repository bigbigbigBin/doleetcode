package special.doubleptr;

public class ReverseWords {
    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     *
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     *
     * 示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     *
     * 示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */

    /**
     * 1、先删除 首、尾、中间多余的空格
     * 2、双指针（头、尾两个指针） 反转整个字符串
     * 3、双指针 反转 单个字符串
     */

    public String reverseWords(String s) {
        //
        StringBuilder sb = removeSpace(s);
        System.out.println(sb);
        //
        reverseString(sb, 0, sb.length() - 1);

        //
        reverseEachWords(sb);
        return sb.toString();
    }

    /**
     * 删除多余空格
     */
    private StringBuilder removeSpace(String s) {
        int left = 0;
        int right = s.length() - 1;
        // 首尾的空格跨过
        while(s.charAt(left) == ' ' && left <= right) {
            left++;
        }
        while(s.charAt(right) == ' ' && right >= left) {
            right--;
        }

        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ' || ( c == ' ' && sb.charAt(sb.length() - 1) != ' ')) {
                sb.append(c);
            }
            left++;
        }
        return sb;
    }

    /**
     * 首、尾双指针，逆转字符串
     * @param s
     */
    public StringBuilder reverseString(StringBuilder s, int left, int right) {
        while (left < right) {
            char temp = s.charAt(left);
            s.setCharAt(left, s.charAt(right));
            s.setCharAt(right, temp);
            left++;
            right--;
        }
        return s;
    }


    public void reverseEachWords(StringBuilder s) {
        int start = 0;
        int end = s.length();
        int i = 0;

        while(i < end) {
            while (i < end && s.charAt(i) != ' ')
                i++;
            reverseString(s, start, i - 1);
            i++;
            start = i;
        }
    }

//    /**
//     * 从后往前模拟，每遇到一个单词就将其放在新的字符串中
//     */
//    public String reverseWords(String s) {
//        StringBuilder sb = new StringBuilder();
//        int last = s.length() - 1;
//        int pre = s.length() - 1;
//        while (pre >= 0) {
//            while (pre >=0 && s.charAt(pre) != ' ')
//                pre--;
//            if (sb.length() == 0) {
//                sb.append(s, pre + 1, last+1);
//            } else {
//                sb.append(" ");
//                sb.append(s, pre + 1, last+1);
//            }
//
//            last = pre;
//            while (pre >=0 && s.charAt(pre) == ' ') {
//                pre--;
//                last--;
//            }
//        }
//        return sb.toString();
//    }

    public static void main(String[] args) {
//        String s = "the sky is blue";
//        String s = "hello world";
        String s = "a good   example  ";
        ReverseWords rw = new ReverseWords();
        System.out.println(rw.reverseWords(s));
    }
}
