package string;

public class PalindromeStr {

    // 判断字符串是否为回文串
    // 双指针办法

    public boolean isPalindrome(String str) {
        int preIndex = 0;
        int endIndex = str.length() - 1;

        while (preIndex < endIndex) {
            if (str.charAt(preIndex) != str.charAt(endIndex))
                return false;
            preIndex++;
            endIndex--;
        }
        return true;
    }

}
