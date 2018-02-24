package com.jazz.lintcode.algorithms;

import java.util.Stack;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team:
 * @date 2018/2/23 0:16
 */
public class MinStack {
    Stack<Integer> s1;
    Stack<Integer> s2;
    int min = Integer.MAX_VALUE;

    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int number) {
        // write your code here

        if (number < min) {
            min = number;
            s2.push(min);
        }
        s1.push(number);
    }

    public int pop() {

        int temp = s1.pop();
        if (temp == min) {
            min = s2.pop();
        }
        return temp;
    }

    public int min() {
        // write your code here
        if (!s1.isEmpty()) {
            return s2.peek();
        }
        return 0;
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(1);
        stack.push(9);
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());


    }
}
