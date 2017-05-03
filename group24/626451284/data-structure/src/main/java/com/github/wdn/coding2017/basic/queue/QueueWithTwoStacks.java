package com.github.wdn.coding2017.basic.queue;

import java.util.Stack;

/**
 * Created by Administrator on 2017/5/2 0002.
 * 用两个栈来实现一个队列
 */
public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;
    private int size;

    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    public boolean isEmpty() {
        return size==0;
    }

    public int size() {
        return size;
    }

    public void enQueue(E item) {
        stack1.push(item);
        size++;
    }

    public E deQueue() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        size--;
        return stack2.pop();
    }

    public static void main(String[] args) {
        QueueWithTwoStacks queue = new QueueWithTwoStacks();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(queue.deQueue());
        }
        for (int i = 10; i < 20; i++) {
            queue.enQueue(i);
        }
        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}
