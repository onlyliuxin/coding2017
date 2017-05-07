package com.aaront.exercise.generic;

import java.util.Stack;

public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;


    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.empty();
    }


    public int size() {
        return stack1.size() + stack2.size();
    }


    public void enQueue(E item) {
        stack1.push(item);
    }

    public E deQueue() {
        if (isEmpty()) return null;
        if (stack2.isEmpty()) _copy();
        return stack2.pop();
    }

    private void _copy() {
        while (!stack1.isEmpty()) {
            E element = stack1.pop();
            stack2.push(element);
        }
    }

}