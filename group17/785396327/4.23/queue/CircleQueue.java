package queue;

import java.util.Arrays;

/**
 * Created by gongxun on 2017/4/24.
 * 用数组实现循环队列
 */
public class CircleQueue<E> {
    private final static int DEFAULT_SIZE = 10;

    //用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE];

    //队头
    private int front = 0;
    //队尾
    private int rear = 0;

    public boolean isEmpty() {
        return front == rear && elementData[front] == null;

    }

    public int size() {
        if (isEmpty())
            return 0;
        return front - rear > 0 ? DEFAULT_SIZE - front + rear : front - rear < 0 ? rear - front : DEFAULT_SIZE;
    }


    public void enQueue(E data) {
        if (size() != DEFAULT_SIZE) {
            elementData[rear] = data;
            if (rear == DEFAULT_SIZE - 1)
                rear = 0;
            else
                rear++;
        }
    }

    public E deQueue() {
        E removeEle = null;
        if (!isEmpty()) {
            removeEle = (E) elementData[front];
            elementData[front] = null;
            if (front == DEFAULT_SIZE - 1)
                front = 0;
            else
                front++;
        }
        return removeEle;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
