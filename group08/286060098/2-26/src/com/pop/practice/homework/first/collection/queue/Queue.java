package com.pop.practice.homework.first.collection.queue;

import com.pop.practice.homework.first.collection.Collection;

/**
 * 更符合Queue语义的方法
 * 
 * @author haipop Date: 17-2-19 Time: 下午3:23
 */
public interface Queue<T> extends Collection<T> {

    /**
     * 添加元素
     */
    void push(T element) throws IllegalAccessException;

    /**
     * 添加元素
     */
    void push(Collection<T> collection) throws IllegalAccessException;

    /**
     * 取元素,删除最后一个并返回
     */
    T pull();
}