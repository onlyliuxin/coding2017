package com.ifengdzh.code2017.basic;

import com.ifengdzh.code2017.basic.util.ArrayIndexBoundUtil;

/**
 * 数组构成的list
 * Created by ajaxfeng on 2017/3/12.
 */
public class ArrayList implements List {
    // 数组长度值
    private int enLargeLength = 10;
    // 数组已用大小
    private int size = 0;

    private Object[] dataArray = new Object[enLargeLength];


    @Override
    public Object add(Object o) {
        if (size < dataArray.length) {
            dataArray[size] = o;
            size++;
        } else {
            enlarge(enLargeLength);
            return add(o);
        }
        return o;
    }

    @Override
    public Object add(int index, Object o) {
        ArrayIndexBoundUtil.checkIndex(index, size);
        // 插入数组内
        if (size == dataArray.length) {
            enlarge(enLargeLength);
        }
        // 后面值依次后移一位,2.然后再插入
        moveBack1Step(index);
        dataArray[index] = o;
        size++;
        return o;
    }

    @Override
    public Object remove(int index) {
        ArrayIndexBoundUtil.checkIndex(index, size);
        Object o = null;
        //最后一个元素
        if (index == size - 1) {
            o = dataArray[index];
            dataArray[index] = null;
        } else {
            //所有元素像前移动一位
            o = moveFront1Step(index);
        }
        size--;
        return o;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {
        ArrayIndexBoundUtil.checkIndex(index, size);
        return dataArray[index];
    }

    /**
     * 往前移动一位
     *
     * @param index
     */
    private Object moveFront1Step(int index) {
        Object o = get(index);
        for (int i = index; i < size; i++) {
            if (i > 0) {
                dataArray[i - 1] = dataArray[i];
            }
        }
        //设置成空
        dataArray[size - 1] = null;
        return o;
    }

    /**
     * @param index 往后移动一位
     */
    private void moveBack1Step(int index) {
        for (int i = size; i > index; i--) {
            dataArray[i] = dataArray[i - 1];
        }
    }

    /**
     * 扩大数组
     *
     * @return 新的扩大空间的数组
     */
    private void enlarge(int enLargeLength) {
        int arrayLength = dataArray.length + enLargeLength;
        Object[] targetArray = new Object[arrayLength];
        copyArray(dataArray, targetArray);
        dataArray = targetArray;
    }

    /**
     * 数组对拷贝
     *
     * @param sourceArray
     * @param targetArray
     */
    private void copyArray(Object[] sourceArray, Object[] targetArray) {
        for (int i = 0; i < sourceArray.length; i++) {
            targetArray[i] = sourceArray[i];
        }
    }

    @Override
    public Iterator iterator() {
        return new ListIterator(this);
    }
}
