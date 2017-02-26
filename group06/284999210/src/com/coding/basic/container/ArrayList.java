package com.coding.basic.container;

import java.util.NoSuchElementException;

public class ArrayList implements List {

    private Object[] elementData;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 8;

    public ArrayList() {
        elementData = new Object[8];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    public boolean add(Object element) {
        if (size == capacity) {
            Object[] tempArray = new Object[capacity];
            for (int i = 0; i < size; i++) {
                tempArray[i] = elementData[i];
            }
            elementData = new Object[capacity * 2];
            for (int i = 0; i < size; i++) {
                elementData[i] = tempArray[i];
            }
            elementData[capacity] = element;
            capacity = capacity * 2;
        }
        elementData[size++] = element;
        return true;
    }

    @Override
    public void add(int index, Object element) {
        checkIndex(index);
        Object[] tempArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            tempArray[i] = elementData[i];
        }
        elementData = new Object[capacity * 2];
        for (int i = 0; i < size; i++) {
            if (i < index) {
                elementData[i] = tempArray[i];
            } else {
                elementData[i + 1] = tempArray[i];
            }
        }
        elementData[index] = element;
        capacity = capacity * 2;
        size++;
    }

    @SuppressWarnings("unchecked")
    public Object remove(int index) {
        checkIndex(index);

        Object o = elementData[index];
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size] = null;
        size = size - 1;
        return o;
    }

    private void checkIndex(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException();
        }
    }

    public Object set(int index, Object element) {
        checkIndex(index);

        Object o = elementData[index];
        elementData[index] = element;
        return o;
    }

    @SuppressWarnings("unchecked")
    public Object get(int index) {
        checkIndex(index);

        return (Object) elementData[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                sb.append(elementData[i] + ", ");
            } else {
                sb.append(elementData[i]);
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
    public Iterator iterator() {
        return new IteratorArrayList();
    }

    @Override
    public boolean remove(Object element) {
        if (element == null)
            return false;
        int findIndex = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                findIndex = i;
                break;
            }
        }

        for (int i = findIndex; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return false;
    }

    private class IteratorArrayList implements Iterator {

        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Object next() {
            if (cursor < size)
                return elementData[cursor++];
            else
                throw new NoSuchElementException();
        }

    }
}
