package com.coding.basic.array;

import java.util.Arrays;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList<T> implements List<T>{
	
	private int size = 0;
	
	private Object[] elementData;
	
	private static final Object[] EMPTY_ELEMENTDATA = {};
	
	public ArrayList(int initialCapacity){
		if(initialCapacity>0){
			this.elementData = new Object[initialCapacity];
		}else if(initialCapacity==0){
			this.elementData = EMPTY_ELEMENTDATA;
		}else{
			throw new IllegalArgumentException("Illeagal Capacity: "+initialCapacity);
		}		
	}
	
	public ArrayList(){
		elementData = new Object[10];
	}
	
	public void ensureCapacity(int minCapacity){
		int oldCapacity = elementData.length;
		if(minCapacity > oldCapacity){
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if(minCapacity > newCapacity){
				newCapacity = minCapacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
	
	public boolean add(T o){
		ensureCapacity(size + 1);
		elementData[size] = o;
		size++;
		return true;
	}
	
	public boolean add(int index, Object o){
		if(index<0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		ensureCapacity(size + 1);
		
		System.arraycopy(elementData, index, elementData, index+1, size - index);

		elementData[index] = o;	
		size++;
		return true;
	} 
	
	@SuppressWarnings("unchecked")
	public T get(int index){
		checkIndex(index);
		return (T)elementData[index];
	}
	
	@SuppressWarnings("unchecked")
	public T remove(int index){
		checkIndex(index);
		T obj = (T)elementData[index];
		if(index != size-1){
			System.arraycopy(elementData,index+1,elementData,index,size-1-index);
		}
		elementData[size-1] = null;
		size--;
		return obj;
	}

	private void checkIndex(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int size(){
		return size;
	}
	
	public ArrayIterator iterator(){		
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<T>{
		private int position;

		@Override
		public boolean hasNext() {
			return position < size;
		}

		@Override
		public T next() {
			if(hasNext()){
				return get(position++);
			}
			return null;
		}		
	}
	
}
