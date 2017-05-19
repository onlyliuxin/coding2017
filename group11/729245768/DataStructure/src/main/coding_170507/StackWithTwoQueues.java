package main.coding_170507;

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Created by peterchen on 2017/5/5.
 */
public class StackWithTwoQueues {
    private Queue queue1;
    private Queue queue2;

    public StackWithTwoQueues() {
        queue1 = new Queue();
        queue2 = new Queue();
    }

    public void push(int data) {
        queue1.enQueue(data);
    }

    public int pop() {
        if (queue1.isEmpty()) {
            throw new EmptyStackException();
        }
        int currentLength = queue1.size();
        for (int i = 0; i < currentLength - 1; i++) {
            queue2.enQueue(queue1.deQueue());
        }
        int data = queue1.deQueue();
        while (!queue2.isEmpty()) {
            queue1.enQueue(queue2.deQueue());
        }
        return data;
    }

    private class Queue {
        private LinkedList<Integer> linkedList = new LinkedList<>();

        public boolean isEmpty() {
            return linkedList.size() == 0;
        }

        public int size() {
            return linkedList.size();
        }

        public void enQueue(Integer data) {
            linkedList.add(data);
        }

        public Integer deQueue() {
            int data = linkedList.removeFirst();
            return data;
        }
    }

    public static void main(String[] args) {
        StackWithTwoQueues stackWithTwoQueues = new StackWithTwoQueues();
        stackWithTwoQueues.push(10);
        stackWithTwoQueues.push(20);
        stackWithTwoQueues.push(30);
        System.out.println(stackWithTwoQueues.pop());
    }
}
