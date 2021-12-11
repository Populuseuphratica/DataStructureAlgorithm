package com.killstan.recursion;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/11/27 16:08
 * @Description:
 */
public class Maze {

    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 当 map[i][j] 为 0 表示该点没有走过 当为 1 表示墙 ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
        int[][] map = new int[8][7];

        for (int i = 0; i < 7; i++) {
            // 上下全部置为 1
            map[0][i] = 1;
            map[7][i] = 1;
            // 左右全部置为 1
            map[i + 1][0] = 1;
            map[i + 1][6] = 1;
        }

        //设置挡板, 1 表示
        map[3][1] = 1;
        map[3][2] = 1;
// map[1][2] = 1;
// map[2][2] = 1;
        // 输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归回溯给小球找路
        setWay(map, 1, 1);
        //        setWay2(map, 1, 1);
        //输出新的地图, 小球走过，并标识过的递归
        System.out.println("小球走过，并标识过的 地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回 true, 否则返回 false
     */
    public static boolean setWay(int[][] map, int i, int j){
        System.out.println("---------------开始走-----------");
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 7; y++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------");

        // 如果终点是通路，则找到终点返回
        if(map[6][5]==2){
            return true;
        }

        if(map[i][j]==0){
            map[i][j] = 2;
            if(setWay(map,i+1,j)){
                return true;
            }
            if(setWay(map,i,j+1)){
                return true;
            }
            if(setWay(map,i-1,j)){
                return true;
            }
            if(setWay(map,i,j-1)){
                return true;
            }else{
                map[i][j] = 3;
                return false;
            }

        }else{
            return false;
        }

    }

}
