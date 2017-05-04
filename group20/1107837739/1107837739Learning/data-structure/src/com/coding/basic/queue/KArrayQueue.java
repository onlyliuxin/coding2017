package com.coding.basic.queue;

import java.util.NoSuchElementException;

/**
 * Created by Korben on 18/02/2017.
 */
public class KArrayQueue<T> implements KQueue<T> {

    private int size;
    private Object[] dataArray = {};
    private int front = 0;
    private int end = -1;

    public KArrayQueue() {

    }

    @Override
    public boolean add(T t) {
        ensureCapacity(size + 1);
        dataArray[end + 1] = t;
        end++;
        size++;
        return true;
    }

    @Override
    public boolean offer(T t) {
        ensureCapacity(size + 1);
        dataArray[end + 1] = t;
        end++;
        size++;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove() {
        if (end == -1) {
            throw new NoSuchElementException();
        }
        T value = (T) dataArray[front];
        dataArray[front] = null;
        size--;
        front++;
        if (front == dataArray.length) {
            front = 0;
        }
        if (size == 0) {
            end = -1;
        }

        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T poll() {
        if (end == -1) {
            return null;
        }
        T value = (T) dataArray[front];
        dataArray[front] = null;
        size--;
        front++;
        if (front == dataArray.length) {
            front = 0;
        }

        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T element() {
        if (end == -1) {
            throw new NoSuchElementException();
        }
        return (T) dataArray[front];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (end == -1) {
            return null;
        }
        return (T) dataArray[front];
    }

    private void ensureCapacity(int minSize) {
        if (end == -1) {
            dataArray = new Object[8];
        } else if (minSize >= dataArray.length) {
            int newLength = dataArray.length * 2;
            Object[] newDataArray = new Object[newLength];
            if (front != 0) {
                System.arraycopy(dataArray, front,
                        newDataArray, newLength - dataArray.length + front,
                        dataArray.length - 1 - front);

                front += newLength - dataArray.length;
            } else {
                System.arraycopy(dataArray, front, newDataArray, front, size);
            }

            dataArray = newDataArray;
        }
    }
}
