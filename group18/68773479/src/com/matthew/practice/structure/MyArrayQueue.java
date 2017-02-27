package com.matthew.practice.structure;


public class MyArrayQueue<T> implements MyQueue<T> {
    private T[] data;
    private int size;//元素个数
    private int front;//队列中第一个对象的位置
    private int rear;//队列中当前对象的位置

    public MyArrayQueue() {
        data = (T[]) new Object[10];
        size = 0;
        front = 0;
        rear = 0;
    }

    @Override
    public void add(T t) {
        if (isFull()) {
            resize();
            front = 0;
        }
        rear = (front + size) % data.length;
        data[rear] = t;
        size++;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        T tempData = data[front];
        data[front] = null;
        //思考一下这里有必要进行除法运算吗?

        front = (front + 1) % (data.length);
        size--;
        return tempData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T front() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        return data[front];
    }

    /**
     * 判断当前队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return size == data.length;
    }

    /**
     * 扩容，2倍
     */
    public void resize() {
        /*注意重新扩容的时候并不需要去设置size
         * 队列的大小并不能通过数组的大小直观的显示出来。
         * 但是栈就可以直观的通过数组的大小显示出来*/
        T[] tmp = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0, tmp, 0, data.length);
        data = tmp;
        tmp = null;//引用置为空，便于gc处理
    }
}
