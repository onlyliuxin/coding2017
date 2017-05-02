package com.github.wdn.coding2017.basic.queue;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class CircleQueue <E> {

    private final static int DEFAULT_SIZE = 10;

    //用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE] ;

    //队头
    private int front = 0;
    //队尾
    private int rear = 0;

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return -1;
    }

    public void enQueue(E data) {

    }

    public E deQueue() {
        return null;
    }
}
