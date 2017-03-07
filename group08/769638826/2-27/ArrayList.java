package com.coding.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by huitailang on 17/2/25.
 *
 * @author zhangkun
 * @date 2017年02月25日13:23:30
 */
public class ArrayList implements List {
    private int size = 0;
    private static final int DEFAULT_SIZE = 16;
    private Object[] elementData = null;
    private int index;

    public ArrayList() {
        elementData = new Object[DEFAULT_SIZE];
    }

    public ArrayList(final int size) {
        elementData = new Object[size];
    }

    public void add(Object o) {
        //如果当前元素个数大于数组长度的2/3
        if (size() > elementData.length * 2 / 3) {
            raiseArray();
        }

        elementData[index++] = o;
        size++;
    }

    public void add(int index, Object o) {
        checkParam(index);

        //如果当前元素个数大于数组长度的2/3
        if (size() > elementData.length * 2 / 3) {
            raiseArray();
        }

        elementData[index] = o;
        size++;
    }

    public Object get(int index) {
        checkParam(index);

        return elementData[index];
    }

    public Object remove(int index) {
        checkParam(index);

        Object o = elementData[index];
        elementData[index] = null;
        size--;
        return o;
    }

    private void raiseArray() {
        Object[] newElementData = Arrays.copyOf(elementData, size() * 2);
        elementData = newElementData;
    }

    private void checkParam(int index) {
        if (index < 0 || index > elementData.length - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator{
        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        @Override
        public Object next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();

            Object[] elementData = ArrayList.this.elementData;

            cursor = i + 1;

            return elementData[i];
        }
    }
}
