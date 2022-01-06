package com.killstan.sortDemo;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/6 13:56
 * @Description:
 */
public class HeapSort {
    public static void main(String[] args) {
        // 初始化一个序列
        int[] array = {
                1, 3, 4, 5, 2, 6, 9, 7, 8, 0
        };

        // 调用快速排序方法
        HeapSort heap = new HeapSort();

        heap.heapSort(array);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 堆排序
     * 升序（使用大顶堆）
     *
     * @param arr
     */
    public void heapSort(int[] arr) {
        int length = arr.length;
        if (arr == null || length < 2) {
            return;
        }
        // 不能简单的从最后一个非叶子节点起每次将最大的节点放上来，因为该节点的父节点可能比它的子节点还小，需要和该节点的子节点都比较
        // for (int i = length / 2 - 1; i > -1; i--) {
//            int child = 2 * i + 1;
//            if (child + 1 < length && arr[child] < arr[child + 1]) {
//                child++;
//            }
//
//            if (arr[i] < arr[child]) {
//                int temp = arr[i];
//                arr[i] = arr[child];
//                arr[child] = temp;
//            }
//        }

        // 将数组改成大顶堆
        // 最后一个非叶子节点（最后一个叶子节点的索引值是n-1，数组中下标为 n 个元素的父节点为 (n-1) / 2，所以它的父节点索引值是[(n-1)-1]/2 = n/2 -1）
        for (int i = length / 2 - 1; i > -1; i--) {
            heapAdjust(arr, i, length);
        }

        // 每次将堆顶数（最大的数）移到数组末尾，然后从堆顶开始调整二叉树为大顶堆
        for (int i = length - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapAdjust(arr, 0, i);
        }


    }

    /**
     * 堆排序调整
     *
     * @param arr    数组
     * @param parent 头节点（这里为调换顺序后的堆顶）
     * @param length 待调整数的个数
     */
    public void heapAdjust(int[] arr, int parent, int length) {
        // 保存第一个父节点
        int temp = arr[parent];
        // 左子节点
        int child = parent * 2 + 1;
        // 这里不用 child + 1 < length 是因为会有只有左子节点的情况
        while (child < length) {
            // 如果有右子结点，取其中最大的的子节点来交换
            if (child + 1 < length && arr[child] < arr[child + 1]) {
                child++;
            }

            // 如果比要交换的节点小，则退出循环
            if (temp >= arr[child]) {
                break;
            } else {
                // 将子节点的值赋予当前父节点
                arr[parent] = arr[child];
                // 从该子节点开始进行下一轮对换
                parent = child;
                child = child * 2 + 1;
            }
        }
        // 最后将最小值换到最小子节点
        arr[parent] = temp;
    }

}
