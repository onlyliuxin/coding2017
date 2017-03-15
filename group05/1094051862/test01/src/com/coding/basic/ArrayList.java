package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[10];
	
	private int increaseSize = 3;
	private void increaseArray() {
		Object[] newData = Arrays.copyOf(elementData, elementData.length + increaseSize);
		elementData = newData;
	}
	public void add(Object o){
		if (size == elementData.length) {
			increaseArray();
			elementData[size++] = o;
		} else {
			elementData[size++] = o;
		}
	}
	public void add(int index, Object o){
		if (index < 0 || index > size) {
			System.out.println("错误提示：index > size || index < 0");
			return;
		}
		Object temp;
		for (int i = index; i < size; i++) {
			temp = elementData[i];
			elementData[i] = o;
			o = temp;
		}
		elementData[size ++] = o;
	}
	
	public Object get(int index){
		if (index < 0 || index > size ){
			return null;
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index < 0 || index > size ){
			return null;
		}
		Object result = elementData[index];
		for (int i = index; i < size-1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[size-1] = null;
		size --;
		return result;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator() {
			private int cusor = 0;
			@Override
			public Object next() {
				if (!hasNext()) {
					System.out.println("next: !hasNext");
					return null;
				}
				return elementData[cusor ++];
			}
			@Override
			public boolean hasNext() {
				return cusor < size;
			}
		};
	}
}
