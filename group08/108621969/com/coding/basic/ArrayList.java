package com.coding.basic;

import java.util.Arrays;

/**
 * Created by zhangjiatao on 2017/2/25.
 */
public class ArrayList implements List {
    private int size = 0;
    private Object[] elementData = new Object[100];

    @Override
    public void add(Object o) {
        if(isFull()) {
            elementData = expandArray(elementData);
        }
        elementData[size++] = o;
    }

    @Override
    public void add(int index, Object o) {
        ifOutOfBounds(index);
        if(isFull()) {
            elementData = expandArray(elementData);
        }
        System.arraycopy(elementData,index,elementData,index+1,size++);
        elementData[index] = o;
    }

    @Override
    public Object get(int index) {
        ifOutOfBounds(index);
        return elementData[index];
    }

    @Override
    public Object remove(int index) {
        ifOutOfBounds(index);
        Object delData = elementData[index];
        System.arraycopy(elementData,index+1,elementData,index,size-index);
        size--;
        return index;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i=0; i<=size-1; i++){
            str += elementData[i] + " ";
        }
        return str;
    }

    private boolean isFull() {
        if(size >= elementData.length-1){
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    private void ifOutOfBounds(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Object[] expandArray(Object[] arr) {
        return Arrays.copyOf(arr, arr.length+10);
    }

    public Iterator iterator = new ArrayListIterator(this);

    private class ArrayListIterator implements Iterator{

        private ArrayList arrList;
        int position = 0,length;

        private ArrayListIterator(ArrayList arrList){
            this.arrList = arrList;
            this.length = arrList.size();
        }

        @Override
        public boolean hasNext() {
            return position < length;
        }

        @Override
        public Object next() {
            return arrList.get(position++);
        }

    }

    public static void main(String[] args) {
        ArrayList arr = new ArrayList();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(1,4);
        arr.remove(2);
        System.out.println(arr.toString());
        System.out.println(arr.size());
        System.out.println(arr.iterator.next());
        System.out.println(arr.iterator.next());
    }
}
