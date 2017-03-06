package com.kevin.coding01.basic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by YinWenBing on 2017/2/25.
 */
public class MyArrayList<E> implements MyList<E> {
    private int size = 0;
    private Object[] elementData = new Object[10];

    /**
     * 添加
     * 判断数组空间是否足够，不够则扩容，将元素放在数组末尾
     *
     * @param e
     */
    public void add(E e) {
        isGrow(size + 1);//判断是否需要扩容
        elementData[size++] = e;
    }

    /**
     * 是否需要扩容
     *
     * @param size
     */
    private void isGrow(int size) {
        if (size > elementData.length) {
            grow(size);
        }
    }

    /**
     * 扩容
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);//>>将oldCapacity向右移一位，右移一位代表除2，右移n位代表除以2的n次方。左移则是乘
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        } else if (newCapacity - (Integer.MAX_VALUE - 8) > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > (Integer.MAX_VALUE)) ? Integer.MAX_VALUE : Integer.MAX_VALUE - 8;
    }

    /**
     * 添加指定索引的元素
     * 判断索引是否小于0或大于size
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        isGrow(index);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
    }

    /**
     * 根据索引获取数组中的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        return (E) elementData[index];
    }

    /**
     * 根据索引移除数组中的元素，如果移除中间的元素，后面的元素要往前移
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        E oldValue = (E) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
            elementData[--size] = null;
            size--;
        }
        return oldValue;
    }

    /**
     * 获取数组中存放值得数量
     *
     * @return
     */
    public int size() {
        return size;
    }
}
