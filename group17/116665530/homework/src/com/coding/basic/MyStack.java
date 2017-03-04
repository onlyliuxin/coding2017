package com.coding.basic;

public class MyStack {
    Object[] elementData;
    private int size;

    public void push(Object o){
        elementData[size++]=o;
    }

    public Object pop(){
        if(size>0)
        {
            elementData[--size]=null;
        }
        return null;
    }

    public Object peek(){
        if(elementData.length == 0){
            return null;
        }
        return elementData[size - 1];
    }
    public boolean isEmpty(){
        if(elementData.length == 0){
            return true;
        }
        return false;
    }
    public int size(){
        return elementData.length;
    }
}
