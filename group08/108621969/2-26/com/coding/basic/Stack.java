package com.coding.basic;

import java.util.ArrayList;


/**
 * Created by zhangjiatao on 2017/2/25.
 */
public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(elementData.size(), o);
    }

    public Object pop() {
        return elementData.remove(elementData.size() - 1);
    }

    public Object peek() {
        return elementData.get(elementData.size() - 1);
    }

    public boolean isEmpty() {
        return elementData.isEmpty();
    }

    public int size() {
        return elementData.size();
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.toString());
    }
}
