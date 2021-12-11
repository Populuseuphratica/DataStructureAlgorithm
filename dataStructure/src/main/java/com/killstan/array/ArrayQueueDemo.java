package com.killstan.array;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CircularQueue queue = new CircularQueue(5);

        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");

            switch (scanner.next()) {
                case "a":
                    System.out.println("输入一个数");
                    int num = scanner.nextInt();
                    queue.add(num);
                    break;
                case "g":
                    int pop = queue.pop();
                    System.out.printf("取出的数据是%d\n", pop);
                    break;
                case "h":
                    int read = queue.read();
                    System.out.printf("队列头的数据是%d\n", read);
                    break;
                case "s":
                    queue.show();
                    break;
                case "e":
                    loop = false;
                    break;
                default:
                    break;
            }

        }
    }

}

/*
 数组实现环形队列
 */
class CircularQueue {

    /**
     * 队列最大个数（数组长度）
     */
    private int maxSize;

    /**
     * 队列前下标
     */
    private int front;

    /**
     * 队列后下标 + 1(队列的后一个空元素)，空出一个空间做为约定来判断是空还是装满了
     */
    private int rear;

    /**
     * 数组
     * 约定数组最后一个为空，并用rear指向这个空。为了使front==rear能够判断出队列是空的
     */
    private int[] array;

    /**
     * 创建队列
     */
    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean add(int num) {
        if (isFull()) {
            System.out.println("队列已满，无法添加");
            return false;
        }

        array[rear] = num;
        rear = (rear+1)%maxSize;
        return true;

    }

    public int read() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("空队列error");
        }

        return array[front % maxSize];
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("空队列error");
        }
        int num = array[front];
        front = (front+1)%maxSize;
        return num;
    }

    public void show() {

        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }

        for (int i = 0; i < size(); i++) {
            System.out.println(array[(front + i) % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    private boolean isFull() {
        return (rear + 1) % maxSize == front;
    }
}


/*
 数组实现一次性队列
 */
class ArrayQueue {

    /**
     * 队列最大个数（数组长度）
     */
    private int maxSize;

    /**
     * 队列前下标-1,指向队列头的前一个位置
     */
    private int front;

    /**
     * 队列后下标
     */
    private int rear;

    /**
     * 数组
     */
    private int[] array;

    /**
     * 创建队列
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        this.array = new int[maxSize];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean add(int num) {
        if (isFull()) {
            System.out.println("队列已满，无法添加");
            return false;
        }

        rear++;
        array[rear] = num;
        return true;

    }

    public int read() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("空队列error");
        }

        return array[front + 1];
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("空队列error");
        }

        front++;
        return array[front];
    }

    public void show() {

        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }

        for (int i = front + 1; i <= rear; i++) {
            System.out.println(array[i]);
        }
    }

    private boolean isFull() {
        return rear == maxSize - 1;
    }

}
