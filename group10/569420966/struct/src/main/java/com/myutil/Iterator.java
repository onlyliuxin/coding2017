package com.myutil;

/**
 * 迭代器
 */
public interface Iterator<T> {

    /**
     * 是否有下一个元素
     *
     * @return true-有 false-无
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     *
     * @return 下一个元素
     */
    T next();

    /**
     * 删除当前迭代的元素
     *
     * @return 被删除的元素
     */
    T remove();
}
