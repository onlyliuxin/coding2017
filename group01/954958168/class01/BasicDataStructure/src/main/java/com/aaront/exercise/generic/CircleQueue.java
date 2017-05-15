package com.aaront.exercise.generic;

public class CircleQueue<E> {

    private final static int DEFAULT_SIZE = 10;

    //用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE];

    //队头
    private int front = 0;
    //队尾
    private int rear = 0;

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        if(rear == elementData.length && front == 0) return true;
        return front == rear + 1;
    }

    public int size() {
        if(rear < front) return elementData.length - front + rear;
        return rear - front;
    }

    public void enQueue(E data) {
        if (isFull()) return;
        if(rear == elementData.length) rear = 0;
        elementData[rear++] = data;
    }

    public E deQueue() {
        if (isEmpty()) return null;
        if(front == elementData.length) front = 0;
        Object element = elementData[front++];
        return (E) element;
    }
}