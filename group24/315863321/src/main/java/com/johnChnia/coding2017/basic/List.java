package com.johnChnia.coding2017.basic;

/**
 * Created by john on 2017/3/12.
 */
public interface List<E> {
    public void add(E o);

    public void add(int index, E o);

    public E get(int index);

    public E remove(int index);

    public int size();
}
