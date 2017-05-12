package com.coding.basic.datastructure.stack;


import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues<E> {

    private Queue<E> firstQueue;
    private Queue<E> secondQueue;

    public StackWithTwoQueues() {
        firstQueue = new ArrayDeque<>();
        secondQueue = new ArrayDeque<>();
    }

    public void push(E data) {
        if (firstQueue.isEmpty() && secondQueue.isEmpty()) {
            firstQueue.add(data);
            return;
        }
        if (!firstQueue.isEmpty()) {
            firstQueue.add(data);
            return;
        }
        if (!secondQueue.isEmpty()) {
            secondQueue.add(data);
            return;
        }
    }

    public E pop() {
        E data = null;
        if (!firstQueue.isEmpty()) {
            data = this.pop(firstQueue, secondQueue);
        } else if (!secondQueue.isEmpty()) {
            data = this.pop(secondQueue, firstQueue);
        }
        return data;
    }

    private E pop(Queue<E> fromQueue, Queue<E> toQueue) {
        E data = null;
        while (fromQueue.size() > 0) {
            if (fromQueue.size() == 1) {
                data = fromQueue.poll();
            } else {
                toQueue.add(fromQueue.poll());
            }
        }
        return data;
    }

}
