package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];

    private Iterator iterator = new ArrayListIterator();

    private int length() {
        return elementData.length;
    }

    private static final int ENLARGE_LENGTH = 100;

    private Object[] enlarge(Object[] origin) {
        return Arrays.copyOf(origin, origin.length + ENLARGE_LENGTH);
    }

    private void enLargeElementData() {
        if (size == length()) {
            elementData = enlarge(elementData);
        }
    }

    public void add(Object o) {
        enLargeElementData();
        elementData[size] = o;
        size++;
    }

    public void add(int index, Object o) {
        checkForAdd(index);
        enLargeElementData();
        // 备份 index 处及后面的数据
        Object[] elementsBehindIndex = backBehindElements(elementData, index);
        // 给index处 设值
        elementData[index] = o;
        // 追加 备份的数据
        appendElement(elementData, index, elementsBehindIndex);
        size++;
    }

    private void appendElement(Object[] origin, int pos, Object[] append) {
        System.arraycopy(append, 0, origin, pos, append.length);
    }

    private Object[] backBehindElements(Object[] elementData, int index) {
        int backSize = size - index;
        Object[] back = new Object[backSize];
        System.arraycopy(elementData, index, back, 0, backSize);
        return back;
    }

    public Object get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(String.format("index=%s, size=%s", index, size));
        }
    }

    private void checkForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(String.format("index=%s, size=%s", index, size));
        }
    }

    public Object remove(int index) {
        checkIndex(index);
        Object[] back = backBehindElements(elementData, index + 1);
        System.arraycopy(back, 0, elementData, index, back.length);
        Object ret = elementData[index];
        elementData[index] = null;
        size--;
        return ret;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return iterator;
    }

    private class ArrayListIterator implements Iterator {

        int next = 0;

        @Override
        public boolean hasNext() {
           return next < size;
        }

        @Override
        public Object next() {
            return elementData[next++];
        }
    }

}
