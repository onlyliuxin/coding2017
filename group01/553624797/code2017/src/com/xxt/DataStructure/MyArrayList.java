package com.xxt.DataStructure;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by star on 2017/2/26.
 */
public class MyArrayList implements List {



    private Object[] elementData;
    private int size = elementData.length;


    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MyArrayList() {
        this(10);
    }


    @Override
    public void add(Object o) {
        ensureCapacity(size + 1);
        elementData[size + 1] = o;
    }

    @Override
    public void add(int index, Object o) {
        //判断数组下标是否越界
        if(index < 0 || index > elementData.length){
            throw new IndexOutOfBoundsException("index : "+index+"size : "+size);
        }
        ensureCapacity(index);
        System.arraycopy(elementData, index,elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public Object remove(int index) {
        //判断数组下标是否越界
        if(index < 0 || index > elementData.length){
            throw new IndexOutOfBoundsException("index : "+index+"size : "+size);
        }


        //取出删除的值
        Object oldValue = elementData[index];

        //做删除操作同样是复制数组
        //计算要删除的数量
        int numMoved = size - index - 1;
        if ( numMoved > 0){
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        //将数组最后一个元素置为空，让GC回收
        elementData[size - 1] = null;
        return oldValue;

    }

    @Override
    public int size() {
        return elementData.length;
    }


    //判断是否扩容
    public void ensureCapacity(int minCapacity) {
        //取得当前数组容量
        int currentCapacity = elementData.length;
        //如果最小需要的容量小于当前数组容量则需要扩容
        if (minCapacity < currentCapacity) {
            int newCapacity = currentCapacity + (currentCapacity >> 1);
            //如果扩容后的长度还是小于最小需要的长度，则直接以最小需要的长度作为当前数组的长度
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
                elementData = Arrays.copyOf(elementData, newCapacity);
            }
        }

    }

}
