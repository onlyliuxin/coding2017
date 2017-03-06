package com.coding.basic;

public class MyArrayList implements MyList {
    private int size = 0;
    private Object[] elementData = new Object[100];

    public void add(Object o){
        elementData[size++] = o;
    }
    public void add(int index, Object o){
        for(int i = size; i > index; i--)
        {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = o;
        size++;
    }

    public Object get(int index){
            return elementData[index];
    }

    public Object remove(int index){
        Object obj = elementData[index];
        for(int i = index; i < size(); i++)
        {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return elementData;
    }

    public int size(){
        return size;
    }

    public MyIterator myIterator(){
        return null;
    }

}
