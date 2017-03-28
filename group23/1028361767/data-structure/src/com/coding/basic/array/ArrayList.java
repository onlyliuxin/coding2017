package com.coding.basic.array;

import java.util.Arrays;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList implements List {

    private int size = 0;

    private int HALF_MAX_VALUE = Integer.MAX_VALUE;

    private Object[] elementData = new Object[100];

    public void add(Object o) {
        if (noSpace()) {
            elementData = grow();
        }
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive integer");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("size is" + size);
        }
        if (noSpace()) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[size++] = o;
    }

    public Object get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive integer");
        }
        if (index > (size - 1)) {
            throw new IndexOutOfBoundsException("size is" + size);
        }
        return elementData[index];
    }

    public Object remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive integer");
        }
        if (index > (size - 1)) {
            throw new IndexOutOfBoundsException("size is" + size);
        }
        Object obj = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[size-1] = null;
        size--;
        return obj;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }

    private boolean noSpace() {
        return size == elementData.length;
    }

    private Object[] grow() {
        int newSize;
        if (size < HALF_MAX_VALUE) {
            newSize = size * 2;
        } else {
            newSize = Integer.MAX_VALUE;
        }
        return Arrays.copyOf(elementData, newSize);
    }

}
