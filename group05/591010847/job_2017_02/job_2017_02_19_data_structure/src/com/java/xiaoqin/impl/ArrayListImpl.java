package com.java.xiaoqin.impl;

import com.java.xiaoqin.interfaces.IIterator;
import com.java.xiaoqin.interfaces.IList;

/**
 * Created by xiaoqin on 17-2-25.
 */
public class ArrayListImpl<T> implements IList<T> {

    private static final int DEFAULT_INIT_SIZE = 20;

    private T[] data;

    private int size = 0;

    public ArrayListImpl() {
        this(DEFAULT_INIT_SIZE);
    }

    public ArrayListImpl(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("无效的capacity：" + capacity);
        }
        if (0 == capacity) {
            capacity = DEFAULT_INIT_SIZE;
        }
        data = (T[]) new Object[capacity];
    }

    @Override
    public void add(T t) {
        group(size);
        data[size++] = t;
    }

    @Override
    public void add(int index, T t) {
        if (index < 0) {
            throw new IllegalArgumentException("index < 0，index:" + index);
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("index >= size。index:" + index + "\tsize:" + size);
        }
        group(size);
        T[] temp = (T[]) new Object[size - index];
        System.arraycopy(data, index, temp, 0, temp.length);
        data[index] = t;
        size++;
        System.arraycopy(temp, 0, data, index + 1, temp.length);
    }

    @Override
    public T get(int index) {
        if (index < data.length) {
            return data[index];
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index invalid!!!index:" + index);
        }
        T[] temp = (T[]) new Object[size - index - 1];
        System.arraycopy(data, index + 1, temp, 0, temp.length);
        T result = data[index];
        System.arraycopy(temp, 0, data, index, temp.length);
        data[--size] = null;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public IIterator<T> iterator() {
        return new ArrayIteratorImpl<>();
    }

    @Override
    public String toString() {
        StringBuilder sbToString = new StringBuilder();
        for (T t :
                data) {
            sbToString.append(t).append("\t");
        }
        return sbToString.toString();
    }

    private void group(int minSize) {
        if (minSize >= data.length) {
            T[] temp = (T[]) new Object[size];
            System.arraycopy(data, 0, temp, 0, size);
            int groupSize = (size >> 1) + size;
            data = (T[]) new Object[Math.max(groupSize, minSize)];
            System.arraycopy(temp, 0, data, 0, size);
        }
    }

    private class ArrayIteratorImpl<T> implements IIterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (index >= size) {
                throw new ArrayIndexOutOfBoundsException("index out");
            }
            return (T) data[index++];
        }
    }
}
