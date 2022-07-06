public class AssignCandy {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/candy
     *
     * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     *
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     *
     * 每个孩子至少分配到 1 个糖果。
     * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     *
     * 示例 1:
     *
     * 输入: [1,0,2]
     * 输出: 5
     * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
     * 示例 2:
     *
     * 输入: [1,2,2]
     * 输出: 4
     * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
     */

    // 不能排序，因为原题目对相邻位置的俩元素有分配要求
    // 不能一起考虑，分开考虑
    public int candy(int[] ratings) {
        int[] candyNum = new int[ratings.length];
        candyNum[0] = 1;
        // 从左到右，找出右边比左边大的
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                candyNum[i] = candyNum[i-1] + 1;
            } else {
                candyNum[i] = 1;
            }
        }

        // 从右到左，找出左边比右边大的
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candyNum[i] = Math.max(candyNum[i], candyNum[i+1] + 1);
            }
        }

        int sum = 0;
        for (int n : candyNum) {
            sum += n;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] rating  = {1,0,2};
        AssignCandy aa = new AssignCandy();
        System.out.println(aa.candy(rating));
    }
}
