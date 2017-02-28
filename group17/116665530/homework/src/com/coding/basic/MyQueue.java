package com.coding.basic;

public class MyQueue {
    private Object[] elementData;
    private int elementCount;
    private int head;
    private int next;
    public void enQueue(Object o){
        elementData[next] = o;
        elementCount++;
        next++;
    }

    public Object deQueue(){
        Object obj = elementData[head];
        elementData[head] = null;
        elementCount--;
        head++;
        return obj;
    }

    public boolean isEmpty(){
        if(elementData.length==0){
            return true;
        }
        return false;
    }

    public int size(){
        return elementData.length;
    }
}
