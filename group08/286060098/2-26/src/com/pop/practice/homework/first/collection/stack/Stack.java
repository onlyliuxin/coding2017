package com.pop.practice.homework.first.collection.stack;

import com.pop.practice.homework.first.collection.Collection;

/**
 * 
 * 更符合Stack语义的方法
 * 
 * @author haipop Date: 17-2-19 Time: 下午3:50
 */
public interface Stack<T> extends Collection<T> {

    /**
     * 添加元素
     */
    void push(T element) throws IllegalAccessException;

    /**
     * 取元素
     */
    T pull();
}
