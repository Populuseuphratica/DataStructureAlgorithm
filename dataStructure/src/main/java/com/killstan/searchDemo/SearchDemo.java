package com.killstan.searchDemo;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/12/17 14:38
 * @Description:
 */
public class SearchDemo {

    public static void main(String[] args) {
        SearchDemo searchDemo = new SearchDemo();
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
        System.out.println(searchDemo.binarySearch(arr,-1000));
    }

    /**
     * 二分查找
     * 重复数字只返回其中一个值的下标
     * @param arr 数组
     * @param target 查找目标
     * @return
     */
    public int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = (right - left) / 2 + left;
            if (arr[middle] > target) {
                right = middle - 1;
            } else if (arr[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

}
