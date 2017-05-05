package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {

    private int size = 0;
    private final int stepLength = 16;
    private Object[] elementData = new Object[stepLength];

    public void add(Object o) {
        checkCapacity();
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        checkIndex(index);
        checkCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    public Object get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    public Object remove(int index) {
        checkIndex(index);
        Object tempObj = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return tempObj;
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("数组下标越界");
        }
    }

    private void checkCapacity() {
        if (size + 1 > elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length + stepLength);
        }
    }

    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator {
        int cursor = 0;

        public boolean hasNext() {
            return cursor < size;
        }

        public Object next() {
            return elementData[cursor++];
        }
    }


}
