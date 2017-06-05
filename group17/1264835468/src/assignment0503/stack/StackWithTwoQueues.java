package assignment0503.stack;


import assignment.Queue;

import java.util.EmptyStackException;

public class StackWithTwoQueues {
    Queue<Integer> queue1 = new Queue<>();
    Queue<Integer> queue2 = new Queue<>();

    public void push(int data) {
        queue1.enQueue(data);
    }

    public int pop() {
        if (queue1.size() == 0) {
            throw new EmptyStackException();
        }
        int size = queue1.size();
        for (int i = 0; i < size - 1; i++) {
            queue2.enQueue(queue1.deQueue());
        }
        Queue t = queue1;
        queue1 = queue2;
        queue2 = t;
        return queue2.deQueue();
    }


}
