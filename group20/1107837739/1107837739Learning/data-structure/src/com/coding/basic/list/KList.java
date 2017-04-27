package com.coding.basic.list;

/**
 * Korben's List
 *
 * Created by Korben on 18/02/2017.
 */
public interface KList<T> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Object[] toArray();

    boolean add(T o);

    boolean remove(T o);

    void clear();

    T get(int index);

    T set(int index, T element);

    void add(int index, T element);

    T remove(int index);

    int indexOf(T o);

    KIterator<T> iterator();
}
