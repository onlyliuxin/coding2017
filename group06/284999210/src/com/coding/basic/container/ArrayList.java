package com.coding.basic.container;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<T> implements java.util.List<T>{

    private Object[] elements;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 8;

    public ArrayList() {
        elements = new Object[8];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    public boolean add(T element) {
        if (size == capacity) {
            Object[] tempArray = new Object[capacity];
            for (int i = 0; i < size; i++) {
                tempArray[i] = elements[i];
            }
            elements = new Object[capacity * 2];
            for (int i = 0; i < size; i++) {
                elements[i] = tempArray[i];
            }
            elements[capacity] = element;
            capacity = capacity * 2;
        }
        elements[size] = element;
        size = size + 1;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);

        Object o = elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size] = null;
        size = size - 1;
        return (T)o;
    }

    private void checkIndex(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException();
        }
    }

    public T set(int index, Object element) {
        checkIndex(index);

        Object o = elements[index];
        elements[index] = element;
        return (T)o;
    }

    @SuppressWarnings("unchecked") public T get(int index) {
        checkIndex(index);

        return (T) elements[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                sb.append(elements[i] + ", ");
            } else {
                sb.append(elements[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[])elements;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        int findIndex = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                findIndex = i;
                break;
            }
        }

        for (int i = findIndex; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        elements = new Object[8];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        return null;
    }
}
