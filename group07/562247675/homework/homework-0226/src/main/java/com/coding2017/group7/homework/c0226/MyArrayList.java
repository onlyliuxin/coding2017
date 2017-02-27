package com.coding2017.group7.homework.c0226;

import java.util.Arrays;

public class MyArrayList implements MyList {

    private int size = 0;

    private Object[] elementData = new Object[10];

    @Override
    public void add(Object o) {
        if (isFull()) {
            increase();
        }
        elementData[size++] = o;
    }

    @Override
    public void add(int index, Object o) {
        checkRangeAdd(index);
        if (isFull()) {
            increase();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    @Override
    public Object get(int index) {
        checkRangeGet(index);
        return elementData[index];
    }

    @Override
    public Object remove(int index) {
        checkRangeGet(index);
        Object element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - (index + 1));
        elementData[--size] = null;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    public MyIterator iterator() {
        return new MyArrayListIterator();
    }

    private boolean isFull() {
        return size >= elementData.length;
    }

    private void checkRangeGet(int index) {
        boolean outOfRange = index < 0 || index >= size;
        if (outOfRange) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkRangeAdd(int index) {
        boolean outOfRange = index < 0 || index > size;
        if (outOfRange) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void increase() {
        Object[] target = new Object[elementData.length * 2];
        System.arraycopy(elementData, 0, target, 0, elementData.length);
        elementData = target;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    private class MyArrayListIterator implements MyIterator {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Object next() {
            checkRangeGet(index);
            return elementData[index++];
        }
    }
}
