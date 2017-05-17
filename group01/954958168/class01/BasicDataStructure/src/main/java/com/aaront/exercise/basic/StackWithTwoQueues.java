package com.aaront.exercise.basic;

import com.aaront.exercise.generic.GenericQueue;

public class StackWithTwoQueues {

    private GenericQueue<Integer> queue1 = new GenericQueue<>();
    private GenericQueue<Integer> queue2 = new GenericQueue<>();

    public void push(int data) {
        queue1.enQueue(data);
    }

    public int pop() {
        while (queue1.size() != 1) {
            queue2.enQueue(queue1.deQueue());
        }
        int element  = queue1.deQueue();
        while (!queue2.isEmpty()) {
            queue1.enQueue(queue2.deQueue());
        }
        return element;
    }
}
