package com.matthew.practice.structure;


public interface MyList<E> {
    public void add(E e);
    public void add(int index, E o);
    public E get(int index);
    public E remove(int index);
    public int size();
}
