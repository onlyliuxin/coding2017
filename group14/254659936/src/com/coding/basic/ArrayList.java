package com.coding.basic;

import java.util.Objects;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[5];

    public void add(Object o) {
        if (size == elementData.length) {
            Object[] newArr = new Object[elementData.length * 2];
            System.arraycopy(newArr, 0, elementData, 0, elementData.length);
            elementData = newArr;
        }
        elementData[size] = o;
        size++;
    }

    public void add(int index, Object o) {
        if (index >= size) {
            throw new RuntimeException("the ArrayList size is short than index");
        }
        elementData[index] = o;
    }

    public Object get(int index) {
        if (index >= size) {
            throw new RuntimeException("the ArrayList size is short than index");
        }
        return elementData[index];
    }

    public Object remove(int index) {
        if (index >= size) {
            throw new RuntimeException("the ArrayList size is short than index");
        }
        Object resultObj = elementData[index];
        size--;
        for (int i = index; i < size; i++) {
            elementData[index] = elementData[index + 1];
        }
        return resultObj;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }

    private class ArrayIterator implements Iterator {

        private int iteratorIndex = 0;

        @Override
        public boolean hasNext() {
            return iteratorIndex < size;
        }

        @Override
        public Object next() {
            if (iteratorIndex >= size) {
                throw new RuntimeException("the index is out of the list");
            }
            return elementData[iteratorIndex++];
        }
    }

}
