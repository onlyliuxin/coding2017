package com.coding.basic.container;

public class ArrayList<T> {

    private Object[] elements;
    private int size;
    private int capacity;

    public ArrayList() {
        elements = new Object[8];
        capacity = 8;
        size = 0;
    }

    public void add(T element) {
        if (size == capacity) {
            Object[] tempArray = new Object[capacity];
            for (int i = 0; i < size; i++) {
                tempArray[i] = elements[i];
            }
            elements = new Object[capacity * 2];
            for (int i = 0; i < size; i++) {
                elements[i] = tempArray[i];
            }
            elements[capacity] = element;
            capacity = capacity * 2;
        }
        elements[size] = element;
        size = size + 1;
    }

    public void remove(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size] = null;
        size = size - 1;
    }

    public void set(int index, Object element) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException();
        }

        elements[index] = element;
    }

    @SuppressWarnings("unchecked") public T get(int index) {
        if (index >= elements.length) {
            throw new IndexOutOfBoundsException();
        }

        return (T) elements[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                sb.append(elements[i] + ", ");
            } else {
                sb.append(elements[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int size() {
        return size;
    }
}
