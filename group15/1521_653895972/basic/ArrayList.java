package com.oneces.tool.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {
    //实例化空数组 不用每次都new
    private static final Object[] Empty_elementData = {};
    private int size = 0;

    private Object[] elementData = new Object[100];

    public ArrayList() {
        this.elementData = Empty_elementData;
    }
    //检查是否越界
    private void checkLenght(int index){
        if (index - size > 0)
            throw new IndexOutOfBoundsException();
    }
    //增加数组容量
    private void kuorong(){
        elementData = Arrays.copyOf(elementData, size + 1);
    }
    public void add(Object o) {
        kuorong();
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        kuorong();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    public Object get(int index) {
        checkLenght(index);
        return elementData[index];
    }

    public Object remove(int index) {
        checkLenght(index);
        Object element =elementData[index];
        int movesize=size-index-1;
        System.arraycopy(elementData,index+1,elementData,index,movesize);
        elementData[--size]=null;
        return element;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new ArrayItr();
    }
    private class ArrayItr implements Iterator{
        int cursor;//游标
        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public Object next() {
            int i=cursor;
            if (i>size)throw new NoSuchElementException();
            Object [] newElementData = ArrayList.this.elementData;
            if (i>newElementData.length)throw new IndexOutOfBoundsException();
            cursor=i+1;
            return newElementData[i];
        }
    }

    @Override
    public String toString() {
        Object[] s = Arrays.copyOf(elementData,size);
        return Arrays.toString(s);
    }
}
