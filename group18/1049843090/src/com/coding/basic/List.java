package com.coding.basic;

/**
 * A Simple List Interface
 */
public interface List<E> {

    /**
     * 追加一个元素
     *
     * @param e
     */
    void add(E e);

    /**
     * 插入一个元素到指定位置
     *
     * @param index
     * @param e
     */
    void add(int index, E e);

    /**
     * 获取指定位置元素
     *
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 移除指定位置元素
     *
     * @param index
     * @return
     */
    E remove(int index);


    /**
     * 当前List中元素的个数
     *
     * @return
     */
    int size();
}