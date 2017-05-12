package com.ifengdzh.code2017.basic;

/**
 * 栈
 * 1. 先进后出
 * 2. 大小可自动扩展
 * Created by ajaxfeng on 2017/3/12.
 */
public interface Stack {

    public Object push(Object o);

    public Object pull();

    public Object peek();

    public int size();

    public boolean isEmpty();

}
