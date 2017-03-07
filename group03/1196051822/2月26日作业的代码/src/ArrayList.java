package com.byhieg.coding2017;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];


    public void add(Object o) {
        isCapacityEnough(size + 1);
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        checkForAdd(index);
        isCapacityEnough(size + 1);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        elementData[index] = o;
        size++;
    }

    private void checkForAdd(int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index不在指定范围内");
        }

    }
    private void isCapacityEnough(int size) {
        if (size > 100) {
            explicitCapacity(size);
        }
        if (size < 0) {
            throw new OutOfMemoryError();
        }
    }

    private final static int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    public void explicitCapacity(int size) {
        int newLength = elementData.length * 2;
        if (newLength > (MAX_ARRAY_LENGTH)){
            newLength = (size > MAX_ARRAY_LENGTH ? Integer.MAX_VALUE : MAX_ARRAY_LENGTH);
        }
        elementData = Arrays.copyOf(elementData, newLength);

    }


    public Object get(int index) {
        checkRange(index);
        return elementData[index];
    }

    private void checkRange(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index不在范围内");
        }
    }

    public Object remove(int index) {
        Object o = get(index);
        //要保证后面的 index + 1是有效的
        int moveSize = size - index - 1;
        if (moveSize > 0) {
            System.arraycopy(elementData,index + 1,elementData,index, size - index);
        }
        elementData[--size] = null;
        return o;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            return elementData[cursor++];
        }
    }


}
