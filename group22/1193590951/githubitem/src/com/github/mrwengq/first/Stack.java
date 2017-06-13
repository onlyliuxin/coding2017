package com.github.mrwengq.first;

public class Stack {

	
	private ArrayList elementData;
	private int index = 0;
	public Stack() {
		elementData = new ArrayList();
	}

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		if (elementData.size() == 0) {
			return null;
		} else {
			Object ob = null;
			ob = elementData.get(elementData.size() - 1);
			elementData.remove(elementData.size() - 1);
			return ob;
		}
	}

	public Object peek() {
		if (elementData.size() == 0){	
			return null;
		}else{
			index ++;
			Object ob = index>elementData.size()? null:elementData.get(elementData.size() - index);
			if(ob==null){
				index = 0;
			}
			return ob;
		}
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}
}
