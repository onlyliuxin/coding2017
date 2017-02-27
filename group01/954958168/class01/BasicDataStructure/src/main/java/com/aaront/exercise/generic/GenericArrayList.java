package com.aaront.exercise.generic;

import java.util.Arrays;

public class GenericArrayList<T> implements GenericList<T> {

    private int size = 0;

    private static final double factor = 0.75;

    private Object[] elementData = new Object[100];

    public void add(T o) {
        _ensureCapacityEnough();
        elementData[size++] = o;
    }

    public void add(int index, T o) {
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

    public T get(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("index超出边界");
        return (T) elementData[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index超出边界");
        Object element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return (T) element;

    }

    public int size() {
        return size;
    }

    public GenericIterator<T> iterator() {
        return new ArrayListGenericIterator(this);
    }

    public Object[] toArray() {
        Object[] objects = new Object[size];
        System.arraycopy(elementData, 0, objects, 0, size);
        return objects;
    }

    public T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        return a;
    }

    private static class ArrayListGenericIterator<T> implements GenericIterator<T> {

        private GenericArrayList<T> genericArrayList;
        private int pos = 0;

        private ArrayListGenericIterator(GenericArrayList<T> genericArrayList) {
            this.genericArrayList = genericArrayList;
        }

        public boolean hasNext() {
            return pos < genericArrayList.size();
        }

        public T next() {
            return (T) genericArrayList.elementData[pos++];
        }

        public void remove() {
            genericArrayList.remove(pos - 1);
            pos--;
        }
    }
}