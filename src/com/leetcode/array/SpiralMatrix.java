package array;

import java.util.Arrays;

public class SpiralMatrix {

    /**
     * 题目地址：https://leetcode-cn.com/problems/spiral-matrix-ii/
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     *
     * 示例:
     *
     * 输入: 3
     * 输出:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     */

    public int[][] generateMatrix(int n) {
        int k = 1;
        int maxNum = n*n;
        int left = 0;
        int right = n;
        int top = 0;
        int bottom = n;

        int[][] result = new int[n][n];

        while(k <= maxNum) {
            int j = left;
            for (; j < right; j++) {
                result[top][j] = k++;
            }
            if (j == right) {
                right--;
            }

            int i = top + 1;
            for (; i < bottom; i++) {
                result[i][right] = k++;
            }
            if (i == bottom) {
                bottom--;
            }

            int m = right-1;
            for (; m >=left; m--) {
                result[bottom][m] = k++;
            }

            int z = bottom-1;
            for (; z > top; z--) {
                result[z][left] = k++;
            }
            if (z == top) {
                top++;
            }
            if (m == left-1) {
                left++;
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(result[i]));
//        }
        return result;
    }

    public static void main(String[] args) {
        SpiralMatrix s = new SpiralMatrix();
        s.generateMatrix(5);
    }

}
