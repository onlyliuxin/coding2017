package com.coding2017.basic;

import java.util.Arrays;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[4];

    public void add(Object o) {
        if (noSpace()) {
            extendSpace();
        }

        elementData[size++] = o;
    }

    private void extendSpace() {
        elementData = Arrays.copyOf(elementData, elementData.length * 2);
    }

    private boolean noSpace() {
        return size == elementData.length;
    }

    public void add(int index, Object o) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (noSpace()) {
            extendSpace();
        }

        if (index == size) {
            add(o);
            return;
        }

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size - 1) {
            return elementData[--size];
        }

        Object removed = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removed;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (size > 0) {
            builder.append(get(0));
        }
        for (int i = 1; i < size; i++) {
            builder.append(", ").append(get(i));
        }
        builder.append("]");
        return builder.toString();
    }

    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator {
        private int pos;

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public Object next() {
            return ArrayList.this.get(pos++);
        }
    }
}
