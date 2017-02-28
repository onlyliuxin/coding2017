package com.kevin.coding01.basic;

/**
 * Created by YinWenBing on 2017/2/25.
 */
public interface MyList<E> {

    void add(E e);

    void add(int index, E e);

    E get(int index);

    E remove(int index);

    int size();
}
