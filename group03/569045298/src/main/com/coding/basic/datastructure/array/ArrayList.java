package com.coding.basic.datastructure.array;


import com.coding.basic.datastructure.Iterator;
import com.coding.basic.datastructure.List;

/**
 * Created by zt on 2017/2/19.
 */
public class ArrayList implements List {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elementData = null;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new RuntimeException("initialCapacity is smaller than zero");
        }
        elementData = new Object[initialCapacity];
    }

    @Override
    public void add(Object o) {
        checkCapacity(size + 1);
        elementData[size] = o;
        size++;
    }

    @Override
    public void add(int index, Object o) {
        checkCapacity(size + 1);
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
        Object removedObject = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, elementData.length - index - 1);
        elementData[--size] = null;
        return removedObject;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkCapacity(int size) {
        if (size > elementData.length) {
            int newLength = elementData.length * 2;
            Object[] newObject = new Object[newLength];
            System.arraycopy(elementData, 0, newObject, 0, elementData.length);
            elementData = newObject;
        }
    }

    public Iterator iterator() {
        return new ArrayListIterator(this);
    }

    private class ArrayListIterator implements Iterator {

        private ArrayList arrayList = null;
        private int position = 0;

        private ArrayListIterator(ArrayList arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public boolean hasNext() {
            return position < size();
        }

        @Override
        public Object next() {
            return get(position++);
        }

        @Override
        public Object remove() {
            // TODO
            return this.arrayList.remove(position--);
        }
    }
}
