package com.vvv.base;

public class Stack {
	private ArrayList data = new ArrayList();

	public void push(Object o) {
		data.add(o);
	}

	public Object pop() {
		return data.remove(data.size() - 1);
	}

	public Object peek() {
		if (data.size() > 0) {
			return data.get(data.size() - 1);
		}
		return null;
	}

	public boolean isEmpty() {
		if (data.size() > 0)
			return false;
		return true;
	}

	public int size() {
		return data.size();
	}
}
