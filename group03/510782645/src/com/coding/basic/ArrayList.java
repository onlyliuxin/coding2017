package com.coding.basic;

import java.util.Arrays;
import java.util.ConcurrentModificationException;

public class ArrayList implements List {
    /**
     * 当数组进行add/remove时， 对modCount进行++
     */
    protected transient int modCount = 0;
    /**
     * 数组的大小
     */
    private int size = 0;

    /**
     * 数组，用来存放ArrayList的内容。
     */
    private Object[] elementData;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int intialSize) {
        elementData = new Object[intialSize];
    }

    public void add(Object o) {
        modCount++;
        // 检测是否要扩容，当添加的元素大于数组的长度后， 扩容
        increment(size + 1);
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        modCount++;
        increment(size + 1);
        /**
         * @param src
         *            源数组
         * @param srcPos
         *            源数组要复制的起始位置
         * @param dest
         *            目的数组
         * @param destPos
         *            目的数组放置的起始位置
         * @param length
         *            复制的长度 从index位置开始copy，
         */
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
    }

    /**
     * 验证是否要扩容。
     *
     * @param capacity
     */
    private void increment(int capacity) {
        if (capacity - elementData.length > 0) {
            grow(capacity);
        }
    }

    /**
     * 扩容，扩容规则为：oldCapacity + oldCapacity/2
     *
     * @param capacity
     */
    private void grow(int capacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public Object get(int index) throws Exception {
        checkSize(index);
        return elementData[index];
    }

    public Object remove(int index) throws Exception {
        modCount++;
        checkSize(index);
        Object oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        //回收多出来的内存。
        elementData[size--] = null;
        return oldValue;
    }

    /**
     * 验证给定的数组下标是否小于数组的长度。
     *
     * @param index
     * @return
     */
    private void checkSize(int index) throws Exception {
        if (index > size) {
            // 数组下标越界异常。
            throw new IndexOutOfBoundsException();
        }
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator {
        int cursor;//记录下一个元素的索引
        int lastReturn = -1;//记录最后一个元素的索引
        int expectCount = modCount;

        @Override
        public boolean hasNext() {
            return (cursor != size);
        }

        @Override
        public Object next() {
            checkForComodification();
            int i = cursor;
            Object[] elementData = ArrayList.this.elementData;
            cursor = i+ 1;
            return elementData[lastReturn = i];
        }

        /**
         * 核心方法， 这里remove可以避免fail-fast快速失败原则。
         * @throws Exception
         */
        public void remove() throws Exception {
            checkForComodification();
            ArrayList.this.remove(lastReturn);
            cursor = lastReturn;
            lastReturn = -1;
            expectCount = modCount;
        }

        /**
         * 验证fail-fast规则。
         */
        final void checkForComodification() {
            if (modCount != expectCount)
                throw new ConcurrentModificationException();
        }
    }
}
