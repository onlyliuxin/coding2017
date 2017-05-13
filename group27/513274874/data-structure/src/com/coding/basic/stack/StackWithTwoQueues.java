package com.coding.basic.stack;


import com.coding.basic.queue.Queue;

public class StackWithTwoQueues {

    Queue<Integer> q1 = new Queue<>();
    Queue<Integer> q2 = new Queue<>();


    public void push(int data) {
        if (q1.isEmpty()) {
            q2.enQueue(data);
        } else {
            q1.enQueue(data);
        }
    }

    public int pop() {
        if (!q1.isEmpty()) {
            while (!q1.isEmpty()) {
                int val = q1.deQueue();
                if (q1.isEmpty()) {
                    q2.enQueue(val);
                } else {
                    return val;
                }
            }
        } else {
            while (!q2.isEmpty()) {
                int val = q2.deQueue();
                if (!q2.isEmpty()) {
                    q1.enQueue(val);
                } else {
                    return val;
                }
            }

        }
        return 0;
    }


}