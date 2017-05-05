package com.circle.collection;

/**
 * Created by keweiyang on 2017/2/25.
 */
public interface List {
    void add(Object o);

    void add(int index, Object o);

    Object get(int index);

    Object remove(int index);

    int size();

}
