package com.github.lqingchenl.coding2017.basic;

import java.util.Arrays;


public class ArrayList implements List {
    private int size = 0;

    private Object[] elementData = new Object[3];

    /**
     * 添加一个元素
     *
     * @param o
     */
    public void add(Object o) {
        elementData[size] = o;
        size = size + 1;
        if (size >= elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
    }

    /**
     * 往固定位置添加一个元素
     *
     * @param index
     * @param o
     */
    public void add(int index, Object o) {
        if (get(index - 1) == null) { //原来为空，添加到指定位置
            add(o);
            return;
        }
        size++;
        if (size >= elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
    }

    /**
     * 获取元素
     *
     * @param index
     * @return
     */
    public Object get(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        return elementData[index];
    }

    /**
     * 移除元素
     *
     * @param index
     * @return
     */
    public Object remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Object deleteData = elementData[index];
        if (index == size - 1) {
            elementData[index] = null;
        } else {
            int movedCount = size - index;
            System.arraycopy(elementData, index + 1, elementData, index, movedCount);
        }
        size--;
        return deleteData;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }

}
