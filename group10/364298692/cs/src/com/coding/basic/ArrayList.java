package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List{
	
	private int size = 0;
	
	private Object[] elementData;
	
	public ArrayList(int initialCapacity){
		elementData = new Object[initialCapacity];
	}
	
	public ArrayList(){
		elementData = new Object[10];
	}
	
	public void ensureCapacity(int minCapacity){
		int oldCapacity = elementData.length;
		if(minCapacity > oldCapacity){
			Object[] oldData = elementData;
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if(minCapacity > newCapacity){
				newCapacity = minCapacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
	
	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size] = o;
		size++;
	}
	public void add(int index, Object o){
		ensureCapacity(size + 1);
		for(int i = size-1; i >= index; i--){
			elementData[i+1] = elementData[i];
		}
		elementData[index] = o;	
		size++;
	} 
	
	public Object get(int index){
		if(index > size-1){
			return null;
		}else{
			return elementData[index];
		}
	}
	
	public Object remove(int index){
		if(index > size-1){
			return null;
		}else{
			Object obj = elementData[index];
			for(int i=index; i<size-1; i++){
				elementData[index] = elementData[index+1];
			}
			size--;
			return obj;
		}
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		
		return null;
	}
	
}
