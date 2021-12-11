package com.killstan.recursion;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/11/28 11:45
 * @Description:
 */
public class EightQueens {
    public static void main(String[] args) {
        Queens queens = new Queens(8);
        queens.check(0);
        System.out.println(queens.count);
    }

}

class Queens {

    public int count = 0;

    private int max;
    /**
     * 表示一种解法，数组个数为行数，值为当前行皇后所在列数。
     */
    private int[] nums;

    public Queens(int max) {
        this.max = max;
        nums = new int[max];
    }

    /**
     * @param num 第几个皇后
     */
    public void check(int num) {
        if (num == max) {
            printQueens();
            count++;
            return;
        }

        //i为每一列，依次判断当前行的每一列是否能放皇后
        for (int i = 0; i < this.max; i++) {
            nums[num] = i;
            // 如果能放，下一行
            if (judgeQueen(num)) {
                check(num + 1);
            }
        }
    }

    /**
     * @param num 第几个皇后，同时也是第几行的皇后
     * @return true:皇后可以放在当前点上
     */
    public boolean judgeQueen(int num) {
        for (int i = 0; i < num; i++) {
            //num > i 保证了当前皇后在放过皇后的后一行

            //nums[i] == nums[num] 当前皇后和之前放过的皇后在同一列
            //由2点算出直线斜率，为1时，在对角线上
            if (nums[i] == nums[num] || Math.abs(num - i) == Math.abs(nums[num] - nums[i])) {
                return false;
            }

//            int k = (nums[num] - nums[i]) / (num - i);
//            if (Math.abs(k) == 1) {
//                return false;
//            }
        }

        return true;
    }

    private void printQueens() {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
