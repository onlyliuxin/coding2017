package com.coding.basic;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];

    public void add(Object o) {
        int len = size + 1;
        if (len == elementData.length) {
            expandArray();
        }
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int remain = elementData.length - size;
        if (remain == 0 || index == elementData.length) {
            expandArray();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    private void expandArray() {
        int len = elementData.length;
        Object[] temp = new Object[len * 2];
        System.arraycopy(elementData, 0, temp, 0, len);
        elementData = temp;
    }

    public Object get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object removed = elementData[index];
        int len = size - index - 1;
        if (len > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, len);
        }
        elementData[--size] = null;
        return removed;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator {

        private int cursor;

        public boolean hasNext() {
            return cursor + 1 < size;
        }

        public Object next() {
            return elementData[++cursor];
        }
    }
}
