package com.pop.practice.homework.first.collection;

/**
 * @author haipop Date: 17-2-16 Time: 下午6:35
 */
public interface Collection<T> extends Iterator<T> {

    /**
     * 是否为空
     */
    boolean isEmpty();

    /**
     * 获取大小
     */
    int size();

    /**
     * 添加元素
     */
    void add(T element) throws IllegalAccessException;

    /**
     * 批量添加元素
     */
    void addAll(Collection<T> collection) throws IllegalAccessException;

    /**
     * 删除元素
     */
    void remove(T element);

    /**
     * 批量删除元素
     */
    void removeAll(Collection<T> collection);

    /**
     * 元素查找,返回索引,找不到返回-1
     */
    int contain(T element);
}