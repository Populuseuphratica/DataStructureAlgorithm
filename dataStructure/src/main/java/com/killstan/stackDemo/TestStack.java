package com.killstan.stackDemo;

import java.util.Scanner;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/11/23 17:22
 * @Description:
 */
public class TestStack {
    public static void main(String[] args) {
//        MyStack stack = new MyStack(6);
        MyLinkedListStack stack = new MyLinkedListStack();
        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        String input;
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");

            input = scanner.next();
            switch (input) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
//                    stack.push(value);
                    stack.push(new Node(value));
                    break;
                case "pop":
                    try {
                        int res = stack.pop().number;
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}

/**
 * 链表实现栈
 */
class MyLinkedListStack{

    private Node head = new Node(-1);

    public boolean isEmpty(){
        if (head.next == null){
            return true;
        }
        return false;
    }


    public void push(Node node){
        Node temp = head.next;
        head.next = node;
        node.next = temp;
    }

    public Node pop(){

        if(isEmpty()){
            throw new RuntimeException("栈为空，无法弹出");
        }
        Node temp = head.next;
        head.next = head.next.next;
        return temp;
    }

    public void show(){
        Node temp = head.next;
        while (temp != null){
            System.out.println(temp.number);
            temp = temp.next;
        }
    }

}

class Node{

    public int number;
    public Node next;

    public Node(int number) {
        this.number = number;
    }

    public Node() {
    }
}

/*
根据数组实现栈
 */
class MyStack {
    private int maxSize;
    private int[] array;

    /**
     * 栈顶指针
     */
    private int top = -1;

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    public boolean isEmpty() {
        if (top < 0) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (top == maxSize - 1) {
            return true;
        }
        return false;
    }

    public void push(int num) {
        if (!isFull()) {
            array[++top] = num;
        } else {
            System.out.println("栈已满，无法添加");
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法弹出");
        }

        return array[top--];
    }


    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空");
        } else {
            for (int i = top; i > -1; i--) {
                System.out.println(array[i]);
            }
        }
    }

}
