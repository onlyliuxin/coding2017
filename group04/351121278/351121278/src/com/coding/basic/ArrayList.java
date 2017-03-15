package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];

    public void add(Object o){
        //容量检查
        checkCapacity(size + 1);
        elementData[size++] = o;

    }

    //对elementData数组进行容量检查
    private void checkCapacity(int minSize) {
        //取得当前数组的长度
        int elementDataLength = elementData.length;
        //如果最小长度大于当前数组的长度，则进行扩容
        if (minSize > elementDataLength) {
            //ArrayList类扩容的长度是原来数组长度的1.5倍+1,此处参考ArrayList的长度进行扩容
            int newSize = (elementDataLength * 3) / 2 + 1;
            //如果扩张后的长度还是比最小需要的长度小，则取需要的长度
            if (newSize < minSize) {
                newSize = minSize;
            }
            //进行数据的拷贝
            elementData = Arrays.copyOf(elementData, newSize);
        }
    }

    public void add(int index, Object o){
        //对容量进行检查
        checkCapacity(size + 1);
        //对数组进行复制，将指定索引位置空出
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    public Object get(int index){
        return elementData[index];
    }

    public Object remove(int index){
        if (index > size) {
            throw new IllegalArgumentException("参数不对");
        }
        //size - index
        Object o = elementData[index];
        int moverNum = size - index - 1;
        if (moverNum > 0) {
            System.arraycopy(elementData, index+1, elementData, index, moverNum);
        }
        //将数组最后一个元素置为null
        elementData[--size] = null;
        return o;
    }

    public int size(){
        return size;
    }

    public Iterator iterator(){
        return null;
    }
    

}

