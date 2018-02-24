package com.jazz.lintcode.algorithms;

import java.util.Stack;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team:
 * @date 2018/2/23 0:18
 */
public class LintCode12 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public LintCode12() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int number) {
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            minStack.push(Math.min(number, minStack.peek()));
        }
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int min() {
        return minStack.peek();
    }
}
