package algorithm.stack;


import datastructure.basic.Queue;

public class StackWithTwoQueues {

    private Queue queue1 = new Queue();
    private Queue queue2 = new Queue();

    public void push(int data) {
        queue1.enQueue(data);
    }

    public int pop() {
        for (int i = 0; i < queue1.size() - 1; ++i) {
            queue1.enQueue(queue1.deQueue());
        }
        return (int) queue1.deQueue();
    }
}
