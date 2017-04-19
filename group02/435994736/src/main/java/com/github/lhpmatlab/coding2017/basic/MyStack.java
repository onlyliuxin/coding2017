package com.github.lhpmatlab.coding2017.basic;

/**
 * Created by andy on 2017/2/22.
 */
public class MyStack<T> {
    private MyArrayList<T> list = new MyArrayList<>();

    public void push(T t) {
        list.add(t);
    }

    public T pop() {
        if (size() <= 0) {
            throw new IndexOutOfBoundsException();
        }
        return list.delete(size() - 1);
    }

    public T peek() {
        return list.get(size() - 1);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
    public int size() {
        return list.size();
    }
}
