package datastructure.stack;

import datastructure.queue.Queue;

public class StackWithTwoQueues {

    private Queue queue1 = new Queue();
    private Queue queue2 = new Queue();

    public void push(int data) {
        queue1.enQueue(data);
    }

    public int pop() {
        int size = queue1.size();
        for (int i = 0; i < size - 1; i++) {
            queue2.enQueue(queue1.deQueue());
        }
        int dest = (int) queue1.deQueue();
        queue1 = queue2;
        queue2 = queue1;
        return dest;
    }


}
