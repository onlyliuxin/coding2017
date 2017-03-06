package com.coding.basic;

import java.util.Arrays;

/**
 * A Simple  ArrayList
 */
public class ArrayList<E> implements List<E> {

    /**
     * 当前list的元素个数
     */
    private int size;

    /**
     * 默认数组大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 存储元素的数组
     */
    private Object[] elementData;

    /**
     * 追加一个元素
     *
     * @param e
     */
    public void add(E e) {
        grow();
        elementData[size++] = e;
    }

    private void grow() {
        if (elementData.length == size) {
            elementData = Arrays.copyOf(elementData, size + 10);
        }
    }

    /**
     * 插入一个元素到指定位置
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        grow();
        int movedNum = size - index;
        if (movedNum > 0) {
            System.arraycopy(elementData, index, elementData, index + 1, movedNum);
        }
        elementData[index] = e;
        size++;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        checkIndex(index);
        return getElement(index);
    }


    /**
     * 删除指定位置的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        checkIndex(index);
        E delEle = getElement(index);
        int movedNum = size - index - 1;//是不是最后一个元素
        if (movedNum > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, movedNum);
        }
        elementData[--size] = null;
        return delEle;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
    }

    private E getElement(int index) {
        return (E) elementData[index];
    }

    /**
     * list中元素的个数
     *
     * @return
     */
    public int size() {
        return this.size;
    }


    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator<E> implements Iterator<E> {

        private int cursor;//游标

        private int lastRet = -1;//可被删除元素下标


        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            cursor++;
            return (E) elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            cursor = lastRet;//游标等于当前删除元素的下标 或者 cursor--;
            ArrayList.this.remove(lastRet);
            lastRet = -1;//重置可删元素下标

        }
    }

}