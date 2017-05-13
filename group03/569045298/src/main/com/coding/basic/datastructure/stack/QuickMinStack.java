package com.coding.basic.datastructure.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回该数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 */
public class QuickMinStack<E extends Comparable> {

    private Stack<E> stack;
    private Stack<E> minStack;

    public QuickMinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(E data) {
        stack.push(data);
        if (minStack.isEmpty()) {
            minStack.push(data);
        } else {
            if (minStack.peek().compareTo(data) >= 0) {
                minStack.push(data);
            }
        }
    }

    public E pop() {
        E top = stack.pop();
        if (top.compareTo(minStack.peek()) <= 0) {
            minStack.pop();
        }
        return top;
    }

    public E findMin() {
        return minStack.peek();
    }
}
