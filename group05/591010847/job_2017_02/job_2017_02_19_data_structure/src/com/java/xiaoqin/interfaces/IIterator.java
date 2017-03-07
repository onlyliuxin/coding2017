package com.java.xiaoqin.interfaces;

/**
 * Created by xiaoqin on 17-2-25.
 */
public interface IIterator<T> {

    /**
     * 是否有下一个
     * @return
     */
    boolean hasNext();

    /**
     * 取出下一个的元素
     * @return
     */
    T next();
}
