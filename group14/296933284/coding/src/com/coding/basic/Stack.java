package com.coding.basic;

/**
 * Stack 实现 第14小组 296933284
 *
 * @author Tonnyson
 *
 */
public class Stack<T> {
	
	private ArrayList<T> elementData = new ArrayList<>();
	private int top = 0;

	public void push(T element) {
		elementData.add(element);
		top++;
	}

	public T pop() {
		return elementData.remove(--top);
	}

	public T peek() {
		return elementData.get(top - 1);
	}
	
	public boolean isEmpty() {
		return top == 0;
	}

	public int size() {
		return top;
	}
}
