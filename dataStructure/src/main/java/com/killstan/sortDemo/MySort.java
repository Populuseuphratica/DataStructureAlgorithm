package com.killstan.sortDemo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/11/29 19:17
 * @Description:
 */
public class MySort {

    public static void main(String[] args) {
        MySort mySort = new MySort();
        int arr[] = {3, 9, -1, 10, 20, -1};
//        int arr[] = {4965556, 4346772, 2518358, 2405369, 5862936, 6514337, 2226242, 2175017};
        //创建要给 80000 个的随机的数组
        arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 800000); //生成一个[0, 8000000) 数
        }
//        System.out.println(Arrays.toString(arr));
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
//测试冒泡排序
//        mySort.bubbleSort(arr);
//        mySort.selectSort(arr);
//        mySort.insertSort(arr);
//        mySort.shellSort(arr);
//        mySort.quickSort(arr, 0, arr.length - 1);
        mySort.mergeSort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                System.out.println("算法出错");
            }
        }
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);


//        System.out.println(Arrays.toString(arr));

    }

    /**
     * 冒泡排序
     *
     * @param array 需要排序的数组
     * @return 排序完成的数组
     */
    public int[] bubbleSort(int[] array) {

        // 如果不想动原数组,clone
//        int[] tempArray = array.clone();

        for (int i = 0; i < array.length - 1; i++) {
            boolean falg = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    falg = false;
                }

            }

            // 当循环一次没有进行交换的时候，说明排序已经完成，终端循环。但是对算法的优化并没有很大提升，时间复杂度不变。
            if (falg) {
                break;
            }
        }

        return array;
    }

    /**
     * 选择排序
     *
     * @param array 需要排序的数组
     * @return 排序完成的数组
     */
    public int[] selectSort(int[] array) {

        // 如果不想动原数组,clone
//        int[] tempArray = array.clone();

        int minIndex = 0;
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }

            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }

        return array;
    }

    /**
     * 插入排序
     *
     * @param array
     * @return
     */
    public int[] insertSort(int[] array) {

        // 如果不想动原数组,clone
//        int[] tempArray = array.clone();
        int temp = 0;
        int tempIndex = 0;
        // 从数组的第二个元素开始循环（默认第一个元素为有序数组）
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            for (int j = i - 1; j > -1; j--) {
                // 当有序数组中的数比当前比较数大的时候，数组中的数后移
                if (temp < array[j]) {
                    array[j + 1] = array[j];
                    tempIndex = j;
                } else {
                    // 找到适合位置后，结束内循环
                    break;
                }
            }
            // 如果发生了数组的后移，就在判断的最后位置插入比较值
            if (temp != array[i]) {
                array[tempIndex] = temp;
            }
        }

        //while解法
//        int insertVal = 0;
//        int insertIndex = 0;
//        //使用 for 循环来把代码简化
//        for(int i = 1; i < array.length; i++) {
//        //定义待插入的数
//            insertVal = array[i];
//            insertIndex = i - 1; // 即 array[1]的前面这个数的下标
//            // 给 insertVal 找到插入的位置
//            // 说明
//            // 1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
//            // 2. insertVal < array[insertIndex] 待插入的数，还没有找到插入位置
//            // 3. 就需要将 array[insertIndex] 后移
//            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
//                array[insertIndex + 1] = array[insertIndex];// array[insertIndex]
//                insertIndex--;
//            }
//            // 当退出 while 循环时，说明插入的位置找到, insertIndex + 1
//            // 举例：理解不了，我们一会 debug
//            //这里我们判断是否需要赋值
//            if(insertIndex + 1 != i) {
//                array[insertIndex + 1] = insertVal;
//            }
//        }

        return array;
    }

    /**
     * 希尔排序
     *
     * @param array
     * @return
     */
    public int[] shellSort(int[] array) {
        int temp = 0;
        int tempIndex = 0;
//        // 将数组以固定间隔分组，间隔从数组长度的一半到1
//        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {
//            // 对每一组遍历
//            for (int i = 0; i < gap; i++) {
//                // 每一组的第二个元素为无序数组，采用插入排序法
//                for (int j = gap + i; j < array.length; j += gap) {
//                    temp = array[j];
//                    tempIndex = j - gap;
//                    while (tempIndex >= 0 && array[tempIndex] > temp) {
//                        array[tempIndex + gap] = array[tempIndex];
//                        tempIndex -= gap;
//                    }
//                    array[tempIndex + gap] = temp;
//                }
//            }
//        }

        // 上面的看着复杂，简化版
        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {
            // 直接从gap开始往后，等于从每个无序组的第一个元素开始遍历，不用纠结一次把一个组的所有元素遍历玩，每次遍历每个无序组的下一个元素
            for (int i = gap; i < array.length; i++) {
                // for写法
//                temp = array[i];
//                tempIndex = i;
//                for (int j = i - gap; j >= 0; j = j - gap) {
//                    if (array[j] > temp) {
//                        array[j + gap] = array[j];
//                        tempIndex = tempIndex -gap;
//                    }else{
//                        break;
//                    }
//                }
//                array[tempIndex] = temp;

                //while写法，感觉看着简单点
                temp = array[i];
                int j = i;
                while (j - gap >= 0 && temp < array[j - gap]) {
                    //移动
                    array[j] = array[j - gap];
                    j -= gap;
                }
//              当退出 while 后，就给 temp 找到插入的位置
                array[j] = temp;

            }
        }
        return array;
    }

    /**
     * 快速排序
     *
     * @param array 待排序数组
     * @param left  左下标
     * @param right 右下标
     */
    public void quickSort(int[] array, int left, int right) {

        // 初版：先找到右边比基准值小的就往左边放。在从左找比基准值大的往右边放。在right==left的点将基准值放入
//        if (left >= right) {
//            return;
//        }
//        int leftOriginal = left;
//        int rightOriginal = right;
//
//        int temp = array[left];
//        while (right >= left) {
//            while (array[right] > temp && right > left) {
//                right--;
//            }
//            if (right == left) {
//                array[right] = temp;
//                break;
//            }
//
//            array[left] = array[right];
//            left++;
//
//            while (array[left] < temp && right > left) {
//                left++;
//            }
//            if (right == left) {
//                array[left] = temp;
//                break;
//            }
//
//            array[right] = array[left];
//            right--;
//        }
//
//        quickSort(array, leftOriginal, left - 1);
//        quickSort(array, right + 1, rightOriginal);


        // 这里只是为了第一次传入判断，后面在递归之前判断，减少栈的开销
        if (left >= right) {
            return;
        }

        int leftOriginal = left;
        int rightOriginal = right;

        // 基准数
        int temp = array[left];

        while (right > left) {
            // 因为是去最左边的为基准值，所以先从右边开始往左找，直到找到比基准值小的数
            while (array[right] >= temp && right > left) {
                right--;
            }

            // 再从左往右边找，直到找到比基准值大的数
            while (array[left] <= temp && left < right) {
                left++;
            }

            // 找到了该交换的位置，交换
            if (right > left) {
                int tempOnce = array[left];
                array[left] = array[right];
                array[right] = tempOnce;
            }
            //这时只会是right==left的情况
            else {
                // 将基准值放在中间位
                array[leftOriginal] = array[left];
                array[left] = temp;
                break;
            }
        }

        if (left > leftOriginal) {
            quickSort(array, leftOriginal, left - 1);
        }

        if (rightOriginal > left) {
            quickSort(array, left + 1, rightOriginal);
        }
    }

    /**
     * 归并排序
     *
     * @param array 待排序数组
     * @return 排序完成数组
     */
    public int[] mergeSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("空数组不能排序");
        }
        // 临时数组，用来存放合并后的数组。
        int[] tempArr = new int[array.length];
        mergeSortDivide(array, 0, array.length - 1, tempArr);
        return array;
    }

    /**
     * 归并数组的分
     *
     * @param array   待分的数组
     * @param left    数组左下标
     * @param right   数组右下标
     * @param tempArr
     */
    private void mergeSortDivide(int[] array, int left, int right, int[] tempArr) {
        // 如果有2个以上元素，就分
        if (right > left) {
            int middle = (right - left) / 2 + left;
            // 左边的数组递归
            mergeSortDivide(array, left, middle, tempArr);
            // 右边的数组递归
            mergeSortDivide(array, middle + 1, right, tempArr);

            // 因为是递归调用，栈的先入后出，这里调用合，将这次分组完成的数组合起来。执行的时候是逆向的。
            // 数组的合
            mergeSortConquer(array, left, right, tempArr);
        }
    }

    /**
     * 数组的合
     *
     * @param array   待合并的数组（也是带排序的数组）
     * @param left    待合并的数组的左下标
     * @param right   待合并的数组的右下标
     * @param tempArr
     */
    private void mergeSortConquer(int[] array, int left, int right, int[] tempArr) {

        int middle = (right - left) / 2 + left;
        // 待合并左边有序序列的开始下标
        int leftPointer = left;

        // 待合并右边有序序列的开始下标
        int rightPointer = middle + 1;
        int tempArrPointer = 0;

        // 直到左右两边的有序序列，有一边处理完毕为止，将其按顺序填入临时数组
        while (leftPointer <= middle && rightPointer <= right) {
            // 对当前左右序列的元素，如果左边小于等于右边
            if (array[leftPointer] <= array[rightPointer]) {
                // 即将左边的元素，填充到 temp 数组
                tempArr[tempArrPointer] = array[leftPointer];
                tempArrPointer++;
                leftPointer++;
            } else {
                //反之,将右边的元素，填充到 temp 数组
                tempArr[tempArrPointer] = array[rightPointer];
                tempArrPointer++;
                rightPointer++;
            }
            // TODO 下面会数组越界，找原因。
//            while (array[leftPointer] <= array[rightPointer] && middle >= leftPointer) {
//                tempArr[tempArrPointer] = array[leftPointer];
//                tempArrPointer++;
//                leftPointer++;
//            }
//
//            while (array[leftPointer] > array[rightPointer] && right >= rightPointer) {
//                tempArr[tempArrPointer] = array[rightPointer];
//                tempArrPointer++;
//                rightPointer++;
//            }
        }

        // 把有剩余数据的一边的数据依次全部填充到 temp 数组
        while (leftPointer <= middle) {
            tempArr[tempArrPointer] = array[leftPointer];
            tempArrPointer++;
            leftPointer++;
        }
        while (rightPointer <= right) {
            tempArr[tempArrPointer] = array[rightPointer];
            tempArrPointer++;
            rightPointer++;
        }

        // 将 temp 数组的元素拷贝到待排序数组
        tempArrPointer--;
        while (tempArrPointer > -1) {
            array[right--] = tempArr[tempArrPointer--];
        }
    }
}


