package special.prefixAndLineSegmentTree;

public class RangeSumQuery2ndImmutable {

    /**
     * https://leetcode.cn/problems/range-sum-query-2d-immutable/
     *
     * 给定一个二维矩阵 matrix，以下类型的多个请求：
     *
     * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1,col1) ，右下角 为 (row2,col2) 。
     * 实现 NumMatrix 类：
     *
     * NumMatrix(int[][] matrix)给定整数矩阵 matrix 进行初始化
     * int sumRegion(int row1, int col1, int row2, int col2)返回 左上角 (row1,col1)、右下角(row2,col2) 所描述的子矩阵的元素 总和 。
     */


    /**
     * 注意到 f(i-1,j) 和 f(i,j-1) 这两项涉及到的矩阵 matrix 的元素有重合，
     * 矩阵 matrix 的以 (i-1,j-1) 为右下角的子矩阵都在这两项中出现。
     *
     * 因此如果计算 f(i-1,j) + f(i,j-1)+ matrix[i][j] 则该结果值比 f(i,j) 多了 f(i-1,j-1)，
     * 因此 f(i,j)f(i,j) 的计算如下：
     * f(i,j)=f(i−1,j)+f(i,j−1)−f(i−1,j−1)+matrix[i][j]
     *
     */

    int[][] preSum;

    public RangeSumQuery2ndImmutable(int[][] matrix) {
        preSum = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                preSum[i+1][j+1] = preSum[i+1][j] + preSum[i][j+1] + matrix[i][j] - preSum[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row1][col2+1] - preSum[row2+1][col1] + preSum[row1][col1];
    }
}
