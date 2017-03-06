package com.aaront.exercise.generic;

public class GenericStack<T> {
    private GenericArrayList<T> elementData = new GenericArrayList<>();

    public void push(T o) {
        elementData.add(o);
    }

    public T pop() {
        return elementData.remove(elementData.size() - 1);
    }

    public T peek() {
        return elementData.get(elementData.size() - 1);
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public int size() {
        return elementData.size();
    }

    public Object[] toArray() {
        return elementData.toArray();
    }

    public T[] toArray(T[] a) {
        return elementData.toArray(a);
    }
}
