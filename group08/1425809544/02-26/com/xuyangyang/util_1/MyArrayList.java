package com.util_1;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by 14258 on 2017/2/27.
 */
public class MyArrayList implements MyList {

    private int size;
    private Object[] elementData;

    private static final int DEFAULTCAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};


    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int initCapacity) {
        if (initCapacity == 0) {
            elementData = EMPTY_ELEMENTDATA;
        } else if (initCapacity > 0) {
            this.elementData = new Object[initCapacity];

        } else {
            throw new IllegalArgumentException("非法参数" + initCapacity);
        }
    }


    @Override
    public boolean add(Object o) {
        ensureInternalCapacity(size + 1);
        elementData[size++] = o;
        return true;
    }

    /**
     * @param index
     * @param o
     */
    @Override
    public void add(int index, Object o) {
        rangeAddCheck(index);
        ensureInternalCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    /**
     * 判断是否第一次插入，如果是，设置为默认长度为10的数组，
     *
     * @param miniCapacity
     */
    private void ensureInternalCapacity(int miniCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            miniCapacity = Math.max(DEFAULTCAPACITY, miniCapacity);
        }
        ensureExplicitCapacity(miniCapacity);
    }

    /**
     * 判断是否扩容，如果待插入的大于现在数组的长度，则扩容；
     *
     * @param miniCapacity
     */
    private void ensureExplicitCapacity(int miniCapacity) {
        if (miniCapacity - elementData.length > 0) {
            grow(miniCapacity);
        }
    }

    /**
     * 扩容
     *
     * @param miniCapacity
     */
    private void grow(int miniCapacity) {
        int oldLength = elementData.length;
        int newLength = oldLength + (oldLength >> 1);
        if (newLength - miniCapacity < 0) {
            newLength = miniCapacity;
        }
        elementData = Arrays.copyOf(elementData, newLength);
    }

    /**
     * 检查add下标范围
     *
     * @param index
     */
    private void rangeAddCheck(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * 根据下标移除
     *
     * @param index
     * @return
     */
    @Override
    public Object remove(int index) {
        rangeCheck(index);
        Object oldValue = elementData[index];
        int movNum = size - index - 1;
        if (movNum > 0) {//如果只有一个元素，就不走
            System.arraycopy(elementData, index + 1, elementData, index, movNum);
        }
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 获取和删除下标检查
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * 根据下标获取，
     *
     * @param index
     * @return
     */
    @Override
    public Object get(int index) {
        rangeCheck(index);

        return elementData[index];
    }

    @Override
    public int size() {
        return size;
    }

    public MyIterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements MyIterator {

        private int cursor;//下一个指针
        private MyArrayList myArrayList;
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {

            int i = cursor;
            if (i>size()){
                throw new NoSuchElementException();
            }
            Object[] elementData = MyArrayList.this.elementData;
            if (i>=elementData.length){
                throw new NoSuchElementException();
            }
            cursor = i +1;

            return  elementData[i];
        }
    }
}
