package com.myutil;

/**
 * 列表基本操作
 */
public interface List<T> {
    /**
     * 添加一个元素到列表
     *
     * @param element 元素
     */
    void add(T element);

    /**
     * 添加一个元素至指定位置
     *
     * <pre>
     *     指定位置范围： index >= 0 && index < size
     *     否则回抛出非法参数异常
     * </pre>
     *
     * @param element 元素
     * @param index 指定位置
     */
    void add(T element, int index);

    /**
     * 删除指定位置元素
     *
     * <pre>
     *     指定位置范围： index >= 0 && index < size
     *     否则回抛出非法参数异常
     * </pre>
     *
     * @param index 指定位置
     * @return 删除的元素的引用
     */
    T remove(int index);

    /**
     * 获取指定位置元素
     * @param index 指定位置
     * @return 指定位置的元素
     */
    T get(int index);

    /**
     * 获取当前列表的大小
     * @return 当前列表的大小
     */
    int size();
}
