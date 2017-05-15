package com.johnChnia.coding2017.basic.stack;

import com.johnChnia.coding2017.basic.queue.Queue;

/**
 * Created by john on 2017/5/7.
 */
public class StackWithTwoQueues {

    Queue<Integer> queue1 = new Queue<>();
    Queue<Integer> queue2 = new Queue<>();

    public void push(int data) {
        while (!queue1.empty()) {
            queue2.add(queue1.remove());
        }
        queue1.add(data);
        while (!queue2.empty()) {
            queue1.add(queue2.remove());
        }
    }

    public int pop() {
        if (!queue1.empty()) {
            return queue1.remove();
        }
        return -1;
    }

    public String toString() {
        return queue1.toString();
    }
}
