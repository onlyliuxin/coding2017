package com.coding.basic;

/**
 * A Simple Queue
 */
public class Queue<E> {

    private LinkedList<E> elementData;

    /**
     * 入列
     *
     * @param e
     */
    public void enQueue(E e) {
        elementData.addLast(e);
    }

    /**
     * 出列
     *
     * @return
     */
    public E deQueue() {
        return elementData.removeFirst();
    }

    /**
     * 查看第一个元素
     *
     * @return
     */
    public E peek() {
        return elementData.size()==0?null:elementData.get(0);
    }

    /**
     * 队列是否有元素
     *
     * @return
     */
    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    /**
     * 队列中元素个数
     *
     * @return
     */
    public int size() {
        return elementData.size();
    }

    public Queue() {
        elementData = new LinkedList<E>();
    }
}