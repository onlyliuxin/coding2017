package com.coding.basic;

public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        elementData.remove(elementData.size()-1);
        return null;
    }

    public Object peek() {
        return elementData.get(elementData.size()-1);
    }

    public boolean isEmpty() {
        return elementData.size()==0?true:false;
    }

    public int size() {
        return elementData.size();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "elementData=" + elementData +
                '}';
    }
}
