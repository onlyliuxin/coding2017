package com.java.xiaoqin.interfaces;

/**
 * Created by xiaoqin on 17-2-26.
 */
public interface IQueue<T> {

    void enQueue(T t);

    T deQueue();

    boolean isEmpty();

    int size();
}
