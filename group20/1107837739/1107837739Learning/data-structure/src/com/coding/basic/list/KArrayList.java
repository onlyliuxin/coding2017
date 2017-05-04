package com.coding.basic.list;

import java.util.Objects;

/**
 * Korben's ArrayList
 *
 * Created by Korben on 18/02/2017.
 */
public class KArrayList<T> implements KList<T> {

    private int size;
    private Object[] dataArray = new Object[0];

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object obj : dataArray) {
            if (Objects.equals(obj, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(dataArray, 0, array, 0, size);
        return array;
    }

    @Override
    public boolean add(T o) {
        ensureCapacity(size + 1);
        dataArray[size] = o;
        size++;
        return true;
    }

    @Override
    public boolean remove(T o) {
        int index = indexOf(o);
        if (index < 0) {
            return false;
        }

        System.arraycopy(dataArray, index + 1, dataArray, index, size - 1 - index);
        dataArray[size - 1] = null;

        size--;

        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            dataArray[i] = null;
        }
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (T) dataArray[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        dataArray[index] = element;

        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity(size + 1);

        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);

        dataArray[index] = element;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T removeData = (T) dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, size - 1 - index);
        dataArray[size - 1] = null;
        size--;
        return removeData;
    }

    @Override
    public int indexOf(T o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, dataArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public KIterator<T> iterator() {
        return new ArrayListIterator();
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > dataArray.length) {
            int newCapacity = Math.max(minCapacity, dataArray.length * 2);
            Object[] newDataArray = new Object[newCapacity];
            System.arraycopy(dataArray, 0, newDataArray, 0, dataArray.length);

            dataArray = newDataArray;
        }
    }

    private class ArrayListIterator implements KIterator<T> {
        private int position;

        ArrayListIterator() {
        }

        @Override
        public boolean hasNext() {
            return position < size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                return get(position++);
            }
            return null;
        }
    }
}
