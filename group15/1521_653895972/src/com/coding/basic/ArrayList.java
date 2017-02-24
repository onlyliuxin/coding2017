package com.coding.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by wanc on 2017/2/21.
 * 实现ArrayList
 */
public class ArrayList implements List {
    /**
     * 实例化空数组 不用每次都new
     */
    private static final Object[] Empty_elementData = {};
    /**
     * 计数
     */
    private int size = 0;
    /**
     * 数据存放
     */
    private Object[] elementData = new Object[100];

    public ArrayList() {
        this.elementData = Empty_elementData;
    }

    /**
     * 检查是否越界
     */
    private void checkLenght(int index) {
        if (index - size > 0)
            throw new IndexOutOfBoundsException();
    }

    /**
     * 增加数组容量
     */
    private void kuorong() {
        elementData = Arrays.copyOf(elementData, size + 1);
    }

    /**
     * 添加数据
     *
     * @param o
     */
    public void add(Object o) {
        //扩容
        kuorong();
        //添加数据
        elementData[size++] = o;
    }

    /**
     * 在指定索引添加数据
     *
     * @param index
     * @param o
     */
    public void add(int index, Object o) {
        //扩容
        kuorong();
        //移动数据
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        //添加数据
        elementData[index] = o;
        size++;
    }

    /**
     * 获取指定索引数据
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        //检查是否越界
        checkLenght(index);
        return elementData[index];
    }

    /**
     * 移除指定索引数据
     *
     * @param index
     * @return
     */
    public Object remove(int index) {
        //检查是否越界
        checkLenght(index);
        Object element = elementData[index];
        //计算移除该元素后，要前移的个数
        int movesize = size - index - 1;
        //移动数据
        System.arraycopy(elementData, index + 1, elementData, index, movesize);
        //删除末尾元素
        elementData[--size] = null;
        return element;
    }

    /**
     * 返回数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 获取迭代器
     *
     * @return
     */
    public Iterator iterator() {
        return new ArrayItr();
    }

    //迭代器实现类部类
    private class ArrayItr implements Iterator {
        int cursor;//游标

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i > size) throw new NoSuchElementException();
            Object[] newElementData = ArrayList.this.elementData;
            if (i > newElementData.length) throw new IndexOutOfBoundsException();
            cursor = i + 1;
            return newElementData[i];
        }
    }

    /**
     * 重写toString 方便打印
     *
     * @return
     */
    @Override
    public String toString() {
        Object[] s = Arrays.copyOf(elementData, size);
        return Arrays.toString(s);
    }
}
