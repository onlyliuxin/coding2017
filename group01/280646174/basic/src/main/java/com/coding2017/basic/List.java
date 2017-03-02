package com.coding2017.basic;

public interface List {
    void add(Object o);

    void add(int index, Object o);

    Object get(int index);

    Object remove(int index);

    int size();
}
