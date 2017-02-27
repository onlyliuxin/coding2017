package net.iyouqu.bruceretrofit.util.java;

import java.util.Arrays;

/**
 * Created by liq on 2017/2/25.
 */

public class CustomArrayList implements List {

    private int size = 0;

    private final static int DEFAULT_CAPACITY = 10;
    private final static int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private String desc = "index超过界限";

    @Override
    public void add(Object o) {
        isCapacityEnough(size + 1);
        elementData[size++] = o;
    }

    @Override
    public void add(int index, Object o) {
        checkRangeForAdd(index);
        isCapacityEnough(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    @Override
    public Object get(int index) {
        checkRange(index);
        return elementData[index];
    }

    @Override
    public Object remove(int index) {
        Object value = get(index);
        int moveSize = size - index - 1;
        if (moveSize > 0){
            System.arraycopy(elementData,index + 1, elementData,index,size - index - 1);
        }
        elementData[--size] = null;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(desc);
        }
    }

    private void checkRangeForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(desc);
        }
    }

    private void explicitCapacity(int capacity) {
        int newLength = elementData.length * 2;
        if (newLength - capacity < 0) {
            newLength = capacity;
        }
        if (newLength > (MAX_ARRAY_LENGTH)) {
            newLength = (capacity > MAX_ARRAY_LENGTH ? Integer.MAX_VALUE : MAX_ARRAY_LENGTH);
        }
        elementData = Arrays.copyOf(elementData, newLength);
    }

    private void isCapacityEnough(int size) {
        if (size > DEFAULT_CAPACITY) {
            explicitCapacity(size);
        }
        if (size < 0) {
            throw new OutOfMemoryError();
        }
    }

    public Iterator iterator() {
        return null;
    }

}
