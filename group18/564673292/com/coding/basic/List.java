package com.coding.basic;

public interface List<E> {
    public void add(E o);
    public void insert(int index, E o);
    public E get(int index);
    public E remove(int index);
    public int size();
}