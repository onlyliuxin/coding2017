package me.lzb.basic.queue;

/**
 * 用数组实现循环队列
 * <p>
 * Created by LZB on 2017/4/27.
 */
public class CircleQueue<E> {


    public CircleQueue(){
        DEFAULT_SIZE = 10;
    }

    public CircleQueue(int size){
        if(size <= 0){
            size = 10;
        }
        DEFAULT_SIZE = size;
    }


    private int DEFAULT_SIZE;

    //用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE];

    //队头
    private int front = 0;
    //队尾
    private int rear = 0;

    private int size;

    public boolean isEmpty() {
        return size() <= 0;

    }

    public int size() {
        return size;
    }


    public void enQueue(E data) {
        nextRear();
        elementData[rear] = data;
        if (isFull()) {
            nextFront();
        } else {
            size++;
        }
    }

    public E deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("empty");
        }
        Object result = elementData[front];
        elementData[front] = null;
        nextFront();
        size--;
        return (E) result;
    }

    private void nextRear() {
        if (rear == (elementData.length - 1)) {
            rear = 0;
        } else {
            rear = rear + 1;
        }

    }

    private void nextFront() {
        if (front == (elementData.length - 1)) {
            front = 0;
        } else {
            front = front + 1;
        }
    }


    private boolean isFull() {
        if (size() == elementData.length) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");


        for (int i = 0; i < elementData.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(elementData[i].toString());
        }


        sb.append("]");
        return sb.toString();
    }
}
