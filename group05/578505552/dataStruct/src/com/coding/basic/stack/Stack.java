package com.coding.basic.stack;


import java.util.EmptyStackException;

/**
 * Created by songbao.yang on 2017/2/22.
 *
 */
public class Stack {

    private Object[] elementData;
    private static final int MIN_INITIAL_CAPACITY = 10;
    private int cursor;

    public Stack() {
        elementData = new Object[MIN_INITIAL_CAPACITY];
        cursor = -1;
    }

    public void push(Object o) {
        ensureCapacity(size() + 1);
        cursor = cursor + 1;
        System.out.println(cursor);
        elementData[cursor] = o;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= elementData.length) {
            return;
        }

        int newSize = elementData.length << 1;
        if (newSize < elementData.length) {
            newSize = Integer.MAX_VALUE;
        }

        Object[] newDataArray = new Object[newSize];
        System.arraycopy(elementData, 0, newDataArray, 0, size());
        elementData = newDataArray;
    }


    public Object pop() {
        Object ele = peek();
        cursor--;
        return ele;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elementData[this.cursor];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.cursor + 1;
    }

    @Override
    public String toString() {

        StringBuffer stringBuffer = new StringBuffer();
        while (!isEmpty()){
            stringBuffer.append(pop());
            if (!isEmpty()){
                stringBuffer.append(',');
            }
        }
        return stringBuffer.toString();
    }
}