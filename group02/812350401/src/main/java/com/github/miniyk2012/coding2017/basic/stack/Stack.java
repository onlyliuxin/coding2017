package com.github.miniyk2012.coding2017.basic.stack;

import com.github.miniyk2012.coding2017.basic.ArrayList;

public class Stack {
	
	// 栈顶  《-》 1，2，3，4 栈底
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(0, o);
	}

	// 如果队列已经没有值了，则抛出ArrayIndexOutOfBoundsException的异常
	public Object pop(){
	    try {
            return elementData.remove(0);
        } catch (Exception e) {
	        throw new NullStackException();
        }
	}
	
	public Object peek(){
		return elementData.get(0);
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}

	public static void main(String[] args) {
		Stack s = new Stack();
		s.pop();
	}

	public static class NullStackException extends RuntimeException {
	    NullStackException() {
	        super("Null Stack!");
        }
    }
}
