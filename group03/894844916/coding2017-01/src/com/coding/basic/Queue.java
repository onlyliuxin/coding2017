/**
 * 
 */
package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * @author patchouli
 *
 */
public class Queue {
	private LinkedList elementData = new LinkedList();
	
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue() throws ListIndexException{
		if (size()==0) {
			throw new NoSuchElementException();
		}
		return elementData.remove(0);
	}
	
	public boolean isEmpty(){
		if (elementData.size()==0) {
			return true;
		}
		return false;
	}
	
	public int size(){
		return elementData.size();
	}
}
