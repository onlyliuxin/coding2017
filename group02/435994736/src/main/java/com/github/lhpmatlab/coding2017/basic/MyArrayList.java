package com.github.lhpmatlab.coding2017.basic;

/**
 * Created by andy on 2017/2/18.
 */
public class MyArrayList<T> {

    private Object[] initialArray = {};
    private Object[] dataArray;
    private int initSize = 10;
    private int arraySize;
    public MyArrayList() {
        dataArray = initialArray;
    }

    public MyArrayList(int init) {
        dataArray = new Object[init];
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < arraySize)
            return;

        Object[] old = dataArray;
        dataArray = new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            dataArray[i] = old[i];
        }
    }

    public void add(T element) {
        add(size(), element);
    }

    public void add(int index, T element) {
        if (size() == dataArray.length) {
            ensureCapacity(size()*2 + 1);
        }
        for(int i=arraySize;i>index;i--) {
            dataArray[i] = dataArray[i - 1];
        }
        dataArray[index] = element;
        arraySize++;
    }

    public T delete(int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removeElement = (T)dataArray[index];
        for (int i = index; i < size() -1; i++) {
            dataArray[i] = dataArray[i + 1];
        }
        arraySize--;
        return removeElement;
    }

    public T get(int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T)dataArray[index];
    }

    public T set(int index, T newElement) {
        if (index < 0 || index > arraySize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldElement = (T) dataArray[index];
        dataArray[index] = newElement;

        return oldElement;
    }

    public int size() {
        return arraySize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}
