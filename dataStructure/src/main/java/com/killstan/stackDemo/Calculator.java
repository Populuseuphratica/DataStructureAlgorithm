package com.killstan.stackDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 栈实现综合计算器(中缀表达式)
 * @Auther: Kill_Stan
 * @Date: 2021/11/24 17:16
 * @Description:
 */
public class Calculator {

    private Map<Character, Integer> map = new HashMap<>() {
        {
            put('+', 0);
            put('-', 0);
            put('*', 1);
            put('/', 1);
            put('(', 2);
            put(')', 2);
        }
    };

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String s = "302/ 2/5/6+7*7/7";
        System.out.println(calculator.calculate(s));

    }

    public int calculate(String s) {
        if (s.length() < 1) {
            System.out.println("请输入正确的字符");
            return 0;
        }

        char[] chars = s.toCharArray();
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> charStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            }

            Integer level = map.get(chars[i]);
            int j = 0;
            while (level == null) {
                sb.append(chars[i + j]);
                j++;
                if (i + j < chars.length) {
                    level = map.get(chars[i + j]);
                } else {
                    level = 4;
                }
            }

            if (j != 0) {
                numStack.push(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
                i = i + j - 1;
                continue;
            }

            if (charStack.empty()) {
                charStack.push(chars[i]);
            } else {
                if (map.get(charStack.peek()) < level) {
                    charStack.push(chars[i]);
                } else {
                    int num1 = numStack.pop();
                    char operator = charStack.pop();
                    int num2 = numStack.pop();
                    numStack.push(calOne(num1, operator, num2));
                    charStack.push(chars[i]);
                }
            }

        }

        while (!charStack.empty()) {
            int num1 = numStack.pop();
            char operator = charStack.pop();
            int num2 = numStack.pop();
            numStack.push(calOne(num1, operator, num2));
        }

        return numStack.pop();
    }

    private int calOne(int num1, char operator, int num2) {
        switch (operator) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                break;
        }

        return 0;
    }
}
