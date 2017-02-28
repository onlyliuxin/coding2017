package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[2];
	
	public void add(Object o){
		int len = elementData.length;
		if(size >= len){
			Object[] new_elmentData = new Object[len*2];
			System.arraycopy(elementData, 0, new_elmentData, 0, size);
			elementData = new_elmentData;
		}
		elementData[size] = o;
		size ++;
	}
	public void add(int index, Object o){
		if(index >= size){
			throw new RuntimeException("下标越界");
		}
		Object[] new_elementData = new Object[size+1];
		System.arraycopy(elementData, 0, new_elementData, 0, index);
		System.arraycopy(elementData, index, new_elementData, index+1, size-index);
		new_elementData[index] = o;
		elementData = new_elementData;
		size++;
	}
	
	public Object get(int index){
		if(index >= size){
			throw new RuntimeException("下标越界");
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index >= size){
			throw new RuntimeException("下标越界");
		}
		Object oldElement = elementData[index];
		if((index+1) != size){
			System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		}
		elementData[size-1] = null;
		size --;
		return oldElement;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	@Override
	public String toString() {
		String s = "{";
		for (int i = 0; i < size; i++) {
			if(i == (size -1)){
				s += elementData[i] + "}";
			}else{
				s += elementData[i]+",";
			}
		}
		return s;
	}
}
