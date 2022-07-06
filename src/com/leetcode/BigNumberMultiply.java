import java.math.BigInteger;

public class BigNumberMultiply {

    /**
     * 缺点：n会很大，用int/long存储结果，会出现超过int/long最大所能保存的范围
     * 比方，30！ long就已经存不下了
     * @param n
     * @return
     */
    public static long solution(int n) {
        long result = 1;
        while (n > 0) {
            result *= n;
            n--;
        }
        return result;
    }


    public static String solution2(int n) {
        String result = "1";
        while (n > 0) {
            result = bigNumberMultiply(result, String.valueOf(n));
            n--;
        }
        return result;
    }

    /**
     * 丢失精度
     * @param n
     * @return
     */
    public static String solution3(int n) {
        double result = 1;
        while (n > 0) {
            result *= n;
            n--;
        }
        return String.format("%.0f", result);
    }

    public static String solution4(int n) {
        BigInteger result = new BigInteger("1");
        while (n > 0) {
            result = result.multiply(new BigInteger(String.valueOf(n)));
            n--;
        }
        return result.toString();
    }

    /**
     * 大数相乘，还是需要转换成字符串，然后模拟乘法
     * 缺点：速度太慢
     */
    public static String bigNumberMultiply(String number1, String number2) {
        char[] char1 = number1.toCharArray();
        char[] char2 = number2.toCharArray();

        int[] num1 = new int[char1.length];
        int[] num2 = new int[char2.length];

        for (int i = 0; i < num1.length; i++) {
            num1[i] = char1[i] - '0';
        }
        for (int i = 0; i < num2.length; i++) {
            num2[i] = char2[i] - '0';
        }

        int []result = new int[num1.length + num2.length];
        int lastIndex = 0;
        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num2.length; j++) {
                result[j + i] = result[j + i] + num1[i] * num2[j];
                lastIndex = j+i;
            }
        }

        // 处理进位, 让原位置只留下余数
        for (int i = lastIndex; i>0; i--) {
            result[i-1] = result[i-1] + result[i] / 10 ;
            result[i] = result[i] % 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= lastIndex; i++) {
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 30;
        System.out.println(BigNumberMultiply.solution(n));
        System.out.println(BigNumberMultiply.solution2(n));
        System.out.println(BigNumberMultiply.solution3(n));
        System.out.println(BigNumberMultiply.solution4(n));
    }


}
