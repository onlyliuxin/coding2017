package com.coding;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

	private final static int INIT_CAPACITY = 3;

	private final static int INCREASE_CAPACITY = 1;

	private int size = 0;        //列表使用长度
	
	private int modCount = 0;    //列表结构修改次数

	private int mCurrentCapacity = INIT_CAPACITY;     //列表容量

	private Object[] elementData = new Object[INIT_CAPACITY];
	
	private void expansion() {
		Object[] newElementData = new Object[mCurrentCapacity + INCREASE_CAPACITY];
		System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
		elementData = newElementData;
		mCurrentCapacity += INCREASE_CAPACITY;
	}

	@Override
	public void add(T o) {
		if (size >= mCurrentCapacity) {
			expansion();
		}
		elementData[size] = o;
		size++;
		modCount++;
	}

	@Override
	public void add(int index, T o){
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		} 
		if (size >= mCurrentCapacity) {
			expansion();
		}
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i-1];
		}
		elementData[index] = o;
		size++;
		modCount++;
	}

	@Override
	public T get(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return (T)elementData[index];
	}

	@Override
	public T remove(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Object item = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i+1];
		}
		size--;
		modCount++;
		return (T)item;
	}

	@Override
	public int size(){
		return size;
	}
    
	public Iterator<T> iterator(){
		return new Iter();
	}
	
	private class Iter implements Iterator<T> {
		int cursor;        
        int expectedModCount = modCount;
        
        @Override
        public boolean hasNext() {
        	return cursor != size;
        }
        
        @Override
    	public T next() {
        	checkForComodification();
        	if (cursor >= size) {
        		throw new NoSuchElementException();
        	}
        	Object item = elementData[cursor];
        	cursor++;
        	return (T)item;
        }
        
        final void checkForComodification() 
        {
             if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
	}

}
