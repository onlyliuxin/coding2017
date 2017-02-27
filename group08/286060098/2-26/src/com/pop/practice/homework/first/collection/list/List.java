package com.pop.practice.homework.first.collection.list;

import com.pop.practice.homework.first.collection.Collection;

/**
 * @author haipop Date: 17-2-19 Time: 下午4:03
 */
public interface List<T> extends Collection<T> {

    /**
     * 获取指定位置元素
     */
    T get(int index) throws IndexOutOfBoundsException;
}
