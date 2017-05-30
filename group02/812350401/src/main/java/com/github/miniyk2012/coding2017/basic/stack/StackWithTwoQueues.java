package com.github.miniyk2012.coding2017.basic.stack;


import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues<E> {
    private Queue<E> queue1 = new LinkedList<>();
    private Queue<E> queue2 = new LinkedList<>();

    public boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public void push(E data) {
        if (queue1.isEmpty()) {
            queue1.add(data);
            move(queue2, queue1);
        } else {
            queue2.add(data);
            move(queue1, queue2);
        }
    }

    public E pop() {
        if (!queue1.isEmpty()) {
            return queue1.remove();
        } else {
            return queue2.remove();
        }
    }

    public E peek() {
        if (!queue1.isEmpty()) {
            return queue1.peek();
        } else {
            return queue2.peek();
        }
    }

    public int size() {
        return queue1.size() + queue2.size();
    }

    private void move(Queue q1, Queue q2) {
        while(!q1.isEmpty()) {
            q2.add(q1.remove());
        }
    }
}
