package com.coding.basic;

import java.util.EmptyStackException;

/**
 * @author Hipple
 * @Time：2017年2月23日 下午10:59:39
 * @version 1.0
 */
public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public Stack(){
		
	}
	
	//入栈  
	public void push(Object o){
		elementData.add(o);
	}
	
	//出栈
	public Object pop(){
		if (elementData.isEmpty()) {
			throw new EmptyStackException();
		}
		final Object o = peek();
		elementData.remove(o);//重新写根据对象remove
		return o;
	}
	
	public Object peek(){
		if (elementData.isEmpty()) {
			throw new EmptyStackException();
		}
		final Object o = elementData.get(elementData.size()-1);
		return o;
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
class TestStack {  
    public static void main(String[] args){  
    	Stack myStack=new Stack();  
        myStack.push("a");  
        myStack.push(2);  
        myStack.push("123");  
        myStack.push("ahu");  
        while(!myStack.isEmpty()){  
            System.out.println(myStack.pop());
        }  
    }  
} 
