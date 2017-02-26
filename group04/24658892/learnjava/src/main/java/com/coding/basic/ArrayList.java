package com.coding.basic;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.Arrays;
import java.util.Collections;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];

    public void add(Object o) {
        int len = size + 1;
        if (len == elementData.length) {
            Object[] temp = new Object[len * 2];
            System.arraycopy(elementData, 0, temp, 0, size);
            elementData = temp;
        }
        elementData[size++] = o;
    }

    public void add(int index, Object o) {

    }

    public Object get(int index) {
        return elementData[index];
    }

    public Object remove(int index) {
        return null;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }
}
