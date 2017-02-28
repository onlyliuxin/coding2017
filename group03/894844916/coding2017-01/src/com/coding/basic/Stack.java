/**
 * 
 */
package com.coding.basic;

import java.util.EmptyStackException;

/**
 * @author patchouli
 *
 */
public class Stack {
	private ArrayList elementData = new ArrayList();

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() throws ListIndexException {
		if (size()==0) {
			throw new EmptyStackException();
		}
		return elementData.remove(size()-1);
	}

	public Object peek() throws ListIndexException {
		if (size()==0) {
			throw new EmptyStackException();
		}
		return elementData.get(size()-1);
	}

	public boolean isEmpty() {
		if (size()==0) {
			return true;
		}
		return false;
	}

	public int size() {
		return elementData.size();
	}
}
