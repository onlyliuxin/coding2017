package com.coding;

public class ArrayList implements List {
	
	private final static int INIT_CAPACITY = 100;
	
	private final static int INCREASE_CAPACITY = 50;
	
	private int size = 0;
	
	private int mCurrentCapacity = INIT_CAPACITY;
	
	private Object[] elementData = new Object[INIT_CAPACITY];
	
	public void add(Object o) {		
		if (size < mCurrentCapacity) {
			elementData[size] = o;
			size++;
		} else {
			Object[] newElementData = new Object[mCurrentCapacity + INCREASE_CAPACITY];
			System.arraycopy(elementData, 0, newElementData, 0, size);
			newElementData[size] = o;
			elementData = newElementData;
			size++;
			mCurrentCapacity += INCREASE_CAPACITY;
		}			
	}
	public void add(int index, Object o){
		if (index > size) {
			return; ///////////////////////////
		} else {
			if (size >= mCurrentCapacity) {
				Object[] newElementData = new Object[mCurrentCapacity + INCREASE_CAPACITY];
				System.arraycopy(elementData, 0, newElementData, 0, size);				
				elementData = newElementData;				
				mCurrentCapacity += INCREASE_CAPACITY;
			}
			for (int i = size; i > index; i--) {
				elementData[i] = elementData[i-1];
			}
			elementData[index] = o;
			size++;
		}
	}
	
	public Object get(int index){
		if (index >= size) {
			return null; /////////////////////////////////
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index >= size) {
			return null;/////////////////////////////////////
		}
		Object item = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i+1];
		}
		size--;
		return item;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
