package com.coding.basic;

public interface List<T> {

    void add(T t);

    void add(int index, T t);

    T get(int index);

    T remove(int index);

    int size();

    Iterator<T> iterator();
}
