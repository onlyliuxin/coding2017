package com.coding.basic;

public interface Iterator<E> {
    public boolean hasNext();
    public E next();
    public E remove();
}