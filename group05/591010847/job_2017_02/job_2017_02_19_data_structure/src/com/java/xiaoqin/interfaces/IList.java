package com.java.xiaoqin.interfaces;

/**
 * Created by xiaoqin on 17-2-19.
 */
public interface IList<T> {

    /**
     * 添加元素
     * @param t
     */
    void add(T t);

    /**
     * 添加元素在第几个
     * @param index
     * @param t
     */
    void add(int index,T t);

    /**
     * 获取第index个的元素
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 移除第index个的元素
     * @param index
     * @return
     */
    T remove(int index);

    /**
     * 返回List的大小
     * @return
     */
    int size();

    /**
     * 是否为empty
     * @return
     */
    boolean isEmpty();

    /**
     * 返回迭代器
     * @return
     */
    IIterator<T> iterator();

}
