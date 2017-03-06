package com.coding.basic;

/**
 * Iterator
 */
public interface Iterator<E> {

    /**
     * 可迭代对象是否还有值
     *
     * @return
     */
    boolean hasNext();

    /**
     * 返回迭代对象的下一个元素
     *
     * @return
     */
    E next();

    /**
     * 移除当前元素
     */
    void remove();
}