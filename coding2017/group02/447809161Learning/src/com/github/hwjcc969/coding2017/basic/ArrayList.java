package com.github.hwjcc969.coding2017.basic;

import javax.lang.model.element.Element;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if (o == null){
			throw new NullPointerException("空对象");
		}
		if (size >= 100){
			throw new RuntimeException("越界");
		}
		elementData[size] = o;
		size++;
	}
	public void add(int index, Object o){
		if (o == null){
			throw new NullPointerException("空对象");
		}
		if (index >= 99){
			throw new RuntimeException("越界");
		}
		for (int i = size; i >= index; i--) {
			elementData[i + 1] = elementData[i];
		}
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if (index >= 100 || index < 0){
			throw new RuntimeException("越界");
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index >= 100 || index < 0){
			throw new RuntimeException("越界");
		}
		Object o = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		size--;
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
