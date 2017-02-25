package com.coding.basic;

/**
 * Created by zhangjiatao on 2017/2/25.
 */
public class Queue {
    private ArrayList elementData = new ArrayList();

    public void enQueue(Object o) {
        elementData.add(0, o);
    }

    public Object deQueue() {
        return elementData.remove(elementData.size() - 1);
    }

    public boolean isEmpty() {
        return elementData.isEmpty();
    }

    public int size() {
        return elementData.size();
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        System.out.println(queue.deQueue());
        System.out.println(queue.toString());
    }
}
