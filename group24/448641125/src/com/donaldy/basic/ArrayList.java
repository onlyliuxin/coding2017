package com.donaldy.basic;

/**
 * Created by donal on 2017/3/7.
 */


public class ArrayList implements List {

    private int size = 0;

    private final int MAXNSIZE = 100;

    private Object[] elementData = new Object[MAXNSIZE];

    public void add(Object o){
        ensureCupacity(size + 1);
        elementData[size++] = o;
    }

    public void add(int index, Object o){
        ensureCupacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    private void ensureCupacity(int capacitySize){
        if (capacitySize >= MAXNSIZE)
            throw new RuntimeException("capacitySize : " + capacitySize);
    }

    public void clear() {
        for (int i = 0 ; i < size() ; ++ i)
            elementData[i] = null;
    }

    public Object get(int index){
        if (index >= MAXNSIZE || index < 0)
            throw new RuntimeException();
        return elementData[index];
    }

    public Object remove(int index){
        ensureCupacity(index);
        Object oldElem = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[size--] = null;
        return oldElem;
    }

    public int size(){
        return size;
    }

}