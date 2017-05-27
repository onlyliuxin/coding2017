package com.coding.week9;


import com.coding.week8.CircleQueue;

public class StackWithTwoQueues {
    private CircleQueue<Integer> queue1 = new CircleQueue<>();
    private CircleQueue<Integer> queue2 = new CircleQueue<>();

    public void push(int data) {
        if (queue1.isEmpty()) {
            queue1.enQueue(data);
        } else {
            queue2.enQueue(data);
        }
    }

    public int pop() {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        if (queue2.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.enQueue(queue1.deQueue());
            }
            return queue1.deQueue();
        } else {
            while (queue2.size() > 1) {
                queue1.enQueue(queue2.deQueue());
            }
            return queue2.deQueue();
        }


    }


}
