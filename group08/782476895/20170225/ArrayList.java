package com.sl.test20170221;

import java.util.Arrays;

public class ArrayList implements List{
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	@Override
	public void add(Object o) {	
		grow(elementData);
		elementData[size] = o;
	}

	@Override
	public void add(int index, Object o) {
		grow(elementData);
		for(int i = size - 1;i >= index;i--){
			elementData[i+1] = elementData[i];
		}
		elementData[index] = o;
	}

	@Override
	public Object get(int index) {
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		size--;
		for(int i = index;i < size;i++){
			elementData[i] = elementData[i+1];
		}
		elementData[size] = null;
		return elementData;
	}

	@Override
	public int size() {
		
		return size;
	}
	
	private void grow(Object[] elementData){
		size++;
		if(size >= elementData.length){
			int newSize = elementData.length + 100;
			elementData = Arrays.copyOf(elementData, newSize);
		}
	}

}
