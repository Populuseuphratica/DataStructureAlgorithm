package com.killstan.dynamicProgramming;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/20 16:21
 * @Description:
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        //物品的重量
        int[] w = {1, 4, 3};

        //物品的价值 这里 val[i] 就是前面讲的 v[i]
        int[] val = {1500, 3000, 2000};

        //背包的容量
        int m = 4;
        //物品的个数
        int n = val.length;

        KnapsackProblem knapsackProblem = new KnapsackProblem();
        knapsackProblem.zeroOnePack(m, n, w, val);

    }

    /**
     * 0-1背包问题
     *
     * @param V      背包容量
     * @param N      物品种类
     * @param weight 物品重量
     * @param value  物品价值
     * @return 最大价值
     */
    public int zeroOnePack(int V, int N, int[] weight, int[] value) {
        // 构建二维数组，多出一行一列，让首行和首列为0
        int[][] dp = new int[N + 1][V + 1];
        // 数组默认初始值为0，不用赋值
//        for (int i = 0; i < V + 1; i++) {
//            dp[0][i] = 0;
//        }
//        for (int i = 1; i < N + 1; i++) {
//            dp[i][0] = 0;
//        }
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                // 第i件物品的重量 > 当前允许的重量时，不放入，延用前一个物品计算后的价值
                // 因为我们程序 i 是从 1 开始的，因此原来对应重量为 w[i-1]
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 能放入当前物品的情况
                else {
                    // 装入该物品之前，腾出存放该物品重量时，背包中物品拥有的最大价值。
                    int beforePutWeight = dp[i - 1][j - weight[i - 1]];
                    // 取放入该物品前后的最大价值作为当前背包的价值
                    dp[i][j] = Math.max(dp[i - 1][j], beforePutWeight + value[i - 1]);
                }
            }
        }
        //则容量为 V 的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
//        System.out.println("最大价值为：" + maxValue);

        // 打印数组矩阵
        printMatrix(dp);

        //逆推找出装入背包的所有商品的编号
        int j = V;
        String numStr = "";
        for (int i = N; i > 0; i--) {
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if (dp[i][j] > dp[i - 1][j]) {
                numStr = i + " " + numStr;
                j = j - weight[i - 1];
            }
            if (j == 0) {
                break;
            }
        }

//        System.out.println("放入物品的下标为" + numStr);
        return maxValue;
    }

    /**
     * 打印矩阵
     * @param matrix
     */
    private void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
