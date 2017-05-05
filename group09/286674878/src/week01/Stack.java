package week01;

import java.util.Arrays;

public class Stack {
private ArrayList elementData = new ArrayList();

	/**
	 * Pushes an item onto the top of this stack
	 * @param o
	 */
	public void push(Object o){	
		elementData.add(o);
	}
	/**
	 * Removes the object at the top of this stack
	 * @return
	 */
	public Object pop(){
		if(isEmpty()){
			throw new UnsupportedOperationException();
		}
		elementData.remove(elementData.size());
		return null;
	}
	/**
	 * Looks at the object at the top of this stack without removing it from the stack
	 * @return
	 */
	public Object peek(){
		if(isEmpty()){
			throw new UnsupportedOperationException();
		}else{
			return elementData.get(elementData.size());
		}
	}
	/**
	 * Tests if this stack is empty
	 * @return
	 */
	public boolean isEmpty(){
		if(elementData.size()>0){
			return false;
		}else return true;
		
	}
	public int size(){
		return elementData.size();
	}

}
