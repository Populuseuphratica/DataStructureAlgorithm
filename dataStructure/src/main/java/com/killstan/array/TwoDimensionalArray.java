package com.killstan.array;

public class TwoDimensionalArray {

    public static void main(String[] args) {
        // 棋盘二维数组赋值，1：黑棋；2：白棋
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;

        // 二维数组总数据数
        int sum = 0;
        for (int[] ints : chessArray) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sum++;
                }
//                System.out.print(anInt + " ");
            }
//            System.out.println();
        }

        int[][] sparseArray = new int[sum + 1][3];

        // 稀疏数组第一行
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = sum;

        // 将二维数组数据存档稀疏数组中
        int count = 1;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[0].length; j++) {
                if (chessArray[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                    count++;
                }
            }
        }
        // 打印稀疏数组
//        for (int[] ints : sparseArray) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }

        //*****************************
        // 将稀疏数组恢复成二维数组
        int[][] chessArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //打印恢复后的二维数组
//        for (int[] ints : chessArray2) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }
    }
}
