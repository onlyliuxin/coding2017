package com.ralf.stack;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 实现基本数据结构栈
 * 
 * @author Ralf
 * 
 */
public class MyStack<T> {

	private LinkedList<T> linkedList = new LinkedList<>();

	public MyStack() {

	}

	public void push(T t) {
		linkedList.addFirst(t);
	}

	public T pop() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return linkedList.removeFirst();
	}

	public T peek() {
		return (size() == 0) ? null : linkedList.getFirst();
	}

	public int size() {
		return linkedList.size();
	}

	public boolean isEmpty(){
		return linkedList.isEmpty();
	}
}
