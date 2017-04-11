package com.coding.basic.stack;

import java.util.EmptyStackException;

import com.coding.basic.array.ArrayList;

public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        checkBound();
        return elementData.remove(size() - 1);
    }

    public Object peek() {
        checkBound();
        return elementData.get(size() - 1);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return elementData.size();
    }

    private void checkBound() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
    }
    
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<elementData.size();i++){
    		sb.append(elementData.get(i));
    		if(i < elementData.size() - 1)
    			sb.append(",");
    	}
    	return sb.toString();
    }
}
