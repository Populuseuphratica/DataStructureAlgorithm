package com.killstan.searchDemo;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/12/19 13:01
 * @Description:
 */
public class InsertSearch {

    /**
     * 插值查找
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找次数~~");
        //注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 求出 mid, 自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) { // 说明应该向右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 说明向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

}
