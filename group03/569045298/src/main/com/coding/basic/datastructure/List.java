package com.coding.basic.datastructure;

/**
 * Created by zt on 2017/2/19.
 */
public interface List {

    void add(Object o);

    void add(int index, Object o);

    Object get(int index);

    Object remove(int index);

    int size();
}
