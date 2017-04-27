package com.johnChnia.coding2017.basic;

import java.util.Iterator;

/**
 * Created by john on 2017/3/12.
 */
public interface List<E> extends Iterable<E>{
    void add(E o);

    void add(int index, E o);

    E get(int index);

    E remove(int index);

    int size();

    Iterator<E> iterator();

    boolean contains(Object o);

}
