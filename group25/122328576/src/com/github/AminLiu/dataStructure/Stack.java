package com.github.AminLiu.dataStructure;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList arrayList = new ArrayList();

	public void push(Object o) {
		arrayList.add(o);
	}

	public Object pop() {
		if (arrayList.size() == 0) {
			throw new EmptyStackException();
		}
		return arrayList.remove(arrayList.size() - 1);
	}

	public Object peek() {
		if (arrayList.size() == 0) {
			throw new EmptyStackException();
		}
		return arrayList.get(arrayList.size() - 1);
	}

	public boolean isEmpty() {
		return arrayList.size() == 0;
	}

	public int size() {
		return arrayList.size();
	}

	public String toString() {
		return arrayList.toString();
	}
}
