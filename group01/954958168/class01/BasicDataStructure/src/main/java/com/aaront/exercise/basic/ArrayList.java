package com.aaront.exercise.basic;

import java.util.Arrays;

public class ArrayList implements List {

    private int size = 0;

    private static final double factor = 0.75;

    private Object[] elementData = new Object[100];

    public void add(Object o) {
        _ensureCapacityEnough();
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("index超出边界");
        _ensureCapacityEnough();
        int i = size;
        for (; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[i] = o;
        size++;
    }

    private void _ensureCapacityEnough() {
        if (size >= elementData.length) {
            dilatancy();
        }
    }

    private void dilatancy() {
        int newLength = elementData.length + (int) (elementData.length * factor);
        elementData = Arrays.copyOf(elementData, newLength);
    }

    public Object get(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("index超出边界");
        return elementData[index];
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index超出边界");
        Object element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return element;

    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new ArrayListIterator(this);
    }

    public Object[] toArray() {
        Object[] objects = new Object[size];
        System.arraycopy(elementData, 0, objects, 0, size);
        return objects;
    }

    private static class ArrayListIterator implements Iterator {

        private ArrayList arrayList;
        private int pos = 0;

        private ArrayListIterator(ArrayList arrayList) {
            this.arrayList = arrayList;
        }

        public boolean hasNext() {
            return pos < arrayList.size();
        }

        public Object next() {
            return arrayList.elementData[pos++];
        }

        public void remove() {
            arrayList.remove(pos - 1);
            pos--;
        }
    }
}
