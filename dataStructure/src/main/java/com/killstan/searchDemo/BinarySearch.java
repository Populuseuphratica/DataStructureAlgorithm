package com.killstan.searchDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/12/17 14:38
 * @Description:
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int arr[] = {1, 1, 8, 10, 89, 1000, 1000, 1000, 1000, 1000, 1000, 1234, 1234};
//        int arr1[] = {};
//        System.out.println(searchDemo.binarySearchPlural(arr, 10001));
        int i = binarySearch.binarySearchByRecursion(arr, 0, arr.length - 1, 1);
        System.out.println(7/9*10);
    }

    /**
     * 二分查找
     * 重复数字只返回其中一个值的下标
     *
     * @param arr    数组
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

    /**
     * 二分查找
     * 允许重复数字
     *
     * @param arr
     * @param target
     * @return 数组中该值所在的下标List
     */
    public List<Integer> binarySearchPlural(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return null;
        }

        int left = 0;
        int right = arr.length - 1;
        List<Integer> list = new ArrayList<>();

        while (left <= right) {
            int middle = (right - left) / 2 + left;
            if (arr[middle] > target) {
                right = middle - 1;
            } else if (arr[middle] < target) {
                left = middle + 1;
            } else {
                // 找到值之后，在该值左右寻找=查找值的值的坐标，加入list
                int temp = middle - 1;
                while (temp >= 0 && arr[temp] == target) {
                    temp--;
                }
                while (temp < middle - 1) {
                    list.add(++temp);
                }

                list.add(middle);

                temp = middle + 1;
                while (temp <= right && arr[temp] == target) {
                    temp++;
                }
                while (temp > middle + 1) {
                    list.add(++middle);
                }
                return list;
            }
        }
        return null;
    }

    /**
     * 二分查找（递归）
     * 重复存在只返回一个下标
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return 存在：数组中目标值的下表； 不存在：-1
     */
    public int binarySearchByRecursion(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int middle = (right - left) / 2 + left;
        if (arr[middle] > target) {
            return binarySearchByRecursion(arr, left, middle - 1, target);
        } else if (arr[middle] < target) {
            return binarySearchByRecursion(arr, middle + 1, right, target);
        } else {
            return middle;
        }
    }

}
