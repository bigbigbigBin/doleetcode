package special.doubleptr;

public class ReverseLeftWords {

    /**
     * 1、反转区间为前n的子串
     * 2、反转区间为n到末尾的子串
     * 3、反转整个字符串
     */
    // 部分反转 + 整体反转
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        // 反转 0--n
        for (int i = 0, j = n -1; i < j; i++, j--) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
        // 反转 n--末尾
        for (int i = n, j = s.length() -1; i < j; i++, j--) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
        // 反转整体
        return sb.reverse().toString();
    }


    /**
     *  需要额外申请空间
     *  O(n)的空间复杂度
     */
    public String reverseLeftWords2(String s, int n) {
        StringBuffer sb = new StringBuffer();
        sb.append(s, n, s.length());
        sb.append(s, 0, n);
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "lrloseumgh";
        int k = 6;
//        String s = "abcdefg";
//        int k = 2;
        ReverseLeftWords rw = new ReverseLeftWords();
        System.out.println(rw.reverseLeftWords(s, k));
    }

}
