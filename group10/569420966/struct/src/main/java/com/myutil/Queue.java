package com.myutil;

/**
 * 队列
 */
public class Queue<T> {
    private LinkedList<T> elementList = new LinkedList<>();

    /**
     * 进入队列
     *
     * @param element 进入队列的元素
     */
    public void enQueue(T element) {
        elementList.add(element);
    }

    /**
     * 出队列
     *
     * @return 出队列的元素
     */
    public T deQueue() {
        return elementList.removeFirst();
    }

    /**
     * 队列是否为空
     *
     * @return true-是 false-否
     */
    public boolean isEmpty() {
        return elementList.size() == 0;
    }

    /**
     * 获取队列的大小
     *
     * @return 队列的大小
     */
    public int size() {
        return elementList.size();
    }
}
