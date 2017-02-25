package com.pop.practice.homework.first.collection;

/**
 * @author haipop Date: 17-2-16 Time: 下午6:34
 */
public interface Iterator<T> {

    /**
     * 实例化
     */
    Iterator iterator();

    /**
     * 是否存在下一个
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     */
    T next();

}
