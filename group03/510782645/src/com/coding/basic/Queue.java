package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * Queue的常用方法：
 * add(Object o):向队尾插入元素，失败则抛出异常
 * offer(Object o):向队尾插入元素，失败则返回false
 * remove():获取并删除队首元素，失败则抛出异常
 * poll():获取并删除队首元素，失败则返回null
 * element():获取但不删除队首元素，失败则抛出异常
 * peek():获取但不删除队首元素，失败则返回null
 */
public class Queue {
    /**
     * Queue中存储的元素
     */
    private Object[] data;
    /**
     * head指向首端第一个有效元素
     */
    private int head;
    /**
     * tail指向尾端第一个可以插入元素的空位。
     */
    private int tail;

    /**
     * 进队列
     */
    public void enQueue(Object o) {
        addLast(o);
    }

    /**
     * 向队列的尾部添加元素
     */
    public void addLast(Object o) {
        if (o == null)
            throw new NullPointerException();
        data[tail] = o;
        //这里可以避免数组是否越界。
        if ((tail = (tail + 1) & (data.length - 1)) == head)
            doubleCapacity();
    }

    /**
     * 检查是否要扩容。
     */
    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = data.length;
        int r = n - p; // head右边元素的个数
        int newCapacity = n << 1;//原空间的2倍
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        System.arraycopy(data, p, a, 0, r);//复制右半部分
        System.arraycopy(data, 0, a, r, p);//复制左半部分
        data = (Object[]) a;
        head = 0;
        tail = n;
    }

    /**
     * 出队列
     */
    public Object deQueue() {
        return removeFirst();
    }

    /**
     * 移除第一个元素
     */
    public Object removeFirst() {
        Object x = pollFirst();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }

    public Object pollFirst() {
        int h = head;
        Object result = data[h]; // Element is null if deque empty
        if (result == null)
            return null;
        data[h] = null;     // Must null out slot
        head = (h + 1) & (data.length - 1);
        return result;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return (tail - head) & (data.length - 1);
    }
}
