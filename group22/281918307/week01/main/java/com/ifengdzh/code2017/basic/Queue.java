package com.ifengdzh.code2017.basic;

/**
 * 队列<br>
 * 1. 先进先出
 * 2. 大小可扩展
 * <p>
 * Created by ajaxfeng on 2017/3/12.
 */
public interface Queue {

    public Object enQueue(Object o);

    public Object deQueue();

    public int size();

    public boolean isEmpty();

}
