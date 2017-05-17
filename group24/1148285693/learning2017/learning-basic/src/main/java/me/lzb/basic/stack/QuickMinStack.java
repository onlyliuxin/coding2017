package me.lzb.basic.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 */
public class QuickMinStack {
    private int mini;
    private java.util.Stack<Integer> stack = new Stack();


    public void push(int data) {
        if (stack.size() <= 0) {
            mini = data;
        } else if (mini > data) {
            mini = data;
        }
        stack.push(data);
    }

    public int pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }


        int a = stack.pop();
        if (a <= mini) {
            newMin();
        }

        return a;
    }

    public int findMin() {
        if (stack.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        return mini;
    }


    private void newMin() {
        if (stack.isEmpty()) {
            return;
        }

        int a = stack.peek();
        for (int i : stack) {
            if (i < a) {
                a = i;
            }
        }

        this.mini = a;
    }

}
