package com.killstan.recursion;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/19 14:49
 * @Description:
 */
public class TowersOfHanoi {

    public static void main(String[] args) {
        TowersOfHanoi towersOfHanoi = new TowersOfHanoi();
        towersOfHanoi.hanoi(3,'A','B','C');
    }

    /**
     * 汉诺塔算法
     * @param diskNum 盘子的数量
     * @param from A杆
     * @param temp B杆
     * @param to C杆
     */
    public void hanoi(int diskNum, char from, char temp, char to) {
        // 在递归时，始终将问题分为最底层的圆盘n和其上的圆盘(1~n-1)，将(1~n-1)移动到B，n移动到C。中间递归的过程中ABC的角色会发生变化，但最终的结果是全部移动到C杆上
        // 圆盘只有一个时，只需将其从A塔移到C塔
        if (diskNum == 1) {
            System.out.println("第1个盘从" + from + "移动到" + to);
        } else {
            // 递归，把A塔上编号1~n-1的圆盘移到B上，以C为辅助塔
            hanoi(diskNum - 1, from, to, temp);
            // 把A塔上编号为n的圆盘移到C上
            System.out.println("第" + diskNum + "个盘从" + from + "移动到" + to);
            // 递归，把B塔上编号1~n-1的圆盘移到C上，以A为辅助塔
            hanoi(diskNum - 1, temp, from, to);
        }
    }

}
