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

    /**
     * 本体不考虑算法，就是单纯模拟。
     * 但是难点在于：要坚持循环不变量原则。
     * 抽象题意，就是花四条边，每画一条边都要坚持一致的原则：要么是左闭右开，要么是左开右闭。
     * 要一直坚持这个原则。
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int count = 1;

        int startX = 0, startY = 0; // 每一次循环的起始位置
        int loop = n / 2 ; // 循环几圈
        int mid = n / 2; // 矩阵中间位置
        int offset = 1; // 控制每次循环的时候，每条边的长度。
        int i, j;

        while(loop > 0) {
            i = startX;
            j = startY;

            // 从左到右，左闭右开
            for (j = startY; j < startY + n - offset; j++) {
                result[startX][j] = count++;
            }

            // 从上到下，依然遵循左闭右开
            for (i = startX; i < startY + n - offset; i++) {
                result[i][j] = count++;
            }

            // 从右到左，依然遵循左闭右开
            for (; j > startY; j--) {
                result[i][j] = count++;
            }

            for (; i > startX; i--) {
                result[i][j] = count++;
            }

            // 走完了完整的一圈，更新新的一圈的开始点
            startX++;
            startY++;

            // 更新新的一圈的长度控制器
            offset += 2;

            loop--;
        }
        if (n % 2 != 0) {
            result[mid][mid] = count;
        }
        return result;
    }




    public static void main(String[] args) {
        SpiralMatrix s = new SpiralMatrix();
        s.generateMatrix(5);
    }

}
