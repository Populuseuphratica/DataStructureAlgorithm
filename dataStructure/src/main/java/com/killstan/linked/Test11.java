package com.killstan.linked;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/11/18 22:36
 * @Description:
 */
public class Test11 {

    private Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};


    public static void main(String[] args) {
//        ListNode listNode5 = new ListNode(5);
//        ListNode listNode4 = new ListNode(4,listNode5);
//        ListNode listNode3 = new ListNode(3,listNode4);
//        ListNode listNode = reverseList(listNode3);
//
//        System.out.println(listNode);
//
        Stack stack = new Stack<Integer>();
        stack.add("asd");
//        fib(45);
        fib2(45);
//        System.out.println((int) Math.pow(0, 10));
////
////        String.valueOf(123213);
//        2147483647
//        1134903170
//        102334155

//        Test11 test11 = new Test11();
//        ListNode node1 = new ListNode(9);
//        ListNode node2 = new ListNode(1,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9))))))))));
//        ListNode listNode = test11.addTwoNumbers(node1, node2);
//        System.out.println(listNode.val);


    }
    public static int fib2(int n) {

        int f1 = 0;
        int f2 = 1;
        if(n == 0){
            return f1;
        }

        if(n==1){
            return f2;
        }

        int num = fib2(n-1) + fib2(n-2);
        System.out.println(num);

         return num;
    }

    public static int fib(int n) {

        int f1 = 0;
        int f2 = 1;
        if(n == 0){
            return f1;
        }

        if(n==1){
            return f2;
        }

        int temp = 0;
        for(int i = 2; i<= n;i++){
            temp = f2;
            f2 = f1+f2;
            f1= temp;
            System.out.println("第"+i+"个数的值为"+f2);
        }

        return f2;

        // return fib(n-1) + fib(n-2);

    }
}

