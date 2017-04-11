package com.ifengdzh.code2017.basic;

/**
 * List
 * 1. 有序
 * 2. 随机访问
 * 3. 长度不固定
 * Created by ajaxfeng on 2017/3/12.
 */
public interface List {
    /**
     * 添加一个对象
     *
     * @param o
     * @return
     */
    public Object add(Object o);

    /**
     * 添加对象到指定位置
     *
     * @param index
     * @param o
     * @return
     */
    public Object add(int index, Object o);


    /**
     * 移除某个元素
     *
     * @param index
     * @return
     */
    public Object remove(int index);

    /**
     * 判断List是否为空
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * 当前List 大小
     *
     * @return
     */
    public int size();

    public Object get(int index);

    public Iterator iterator();

}
