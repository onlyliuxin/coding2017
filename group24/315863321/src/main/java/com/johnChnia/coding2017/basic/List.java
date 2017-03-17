package com.johnChnia.coding2017.basic;

/**
 * Created by john on 2017/3/12.
 */
public interface List {
    public void add(Object o);

    public void add(int index, Object o);

    public Object get(int index);

    public Object remove(int index);

    public int size();
}
