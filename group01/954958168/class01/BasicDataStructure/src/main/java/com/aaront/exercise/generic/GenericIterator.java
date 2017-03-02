package com.aaront.exercise.generic;

public interface GenericIterator<T> {
    boolean hasNext();

    T next();

    void remove();
}
