package com.johnChnia.coding2017.basic.queue;

/**
 * Created by john on 2017/5/6.
 *
 * NOTE:循环队列下一个为(i+1)%N，上一个为(i+N-1)%N
 */
public class CircleQueue {
    private int rear = -1;
    private int front = -1;
    private Object[] array;
    private static final int DEFAULTCAPACITY = 10;


    /**
     * 构造一个空的循环队列
     */
    public CircleQueue() {
        array = new Object[DEFAULTCAPACITY];
    }

    /**
     * 根据传进来的容量构造循环队列
     *
     * @param capacity 队列容量
     */
    public CircleQueue(int capacity) {
        array = new Object[capacity];
    }

    /**
     * 循环队列长度
     *
     * @return 队列长度
     */
    public int size() {
        return array.length;
    }

    /**
     * 判断队列是否已经满了
     *
     * @return 如果队列满的话返回true，否则返回false
     */
    public boolean isFull() {
        if ((rear + 1) % size() == front) {
            return true;
        }
        return false;
    }

    /**
     * 判断队列是否为空
     *
     * @return 如果队列为空返回true，否则返回false
     */
    public boolean isEmpty() {
        if (rear == -1 && front == -1) {
            return true;
        }
        return false;
    }

    /**
     * 入队操作
     */
    public void enQueue(int item) throws Exception {
        if (isFull()) {
            throw new Exception("队列已满");
        } else if (isEmpty()) {
            rear++;
            front = rear;
            array[rear] = item;
        } else {
            rear = (rear + 1) % size();
            array[rear] = item;
        }
    }

    /**
     * 出队操作
     */
    public void deQueue() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空");
        } else if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % size();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i <= size() - 1; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
}
