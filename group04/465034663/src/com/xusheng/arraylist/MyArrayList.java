package com.xusheng.arraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T>{

	private T[] item;
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private int size;
	
	public int size(){
		return size;
	}
	
	private void doClear(){
		this.size = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	/**
	 * 此方法用于确定list的容量
	 * @param newCapacity
	 */
	public void ensureCapacity(int newCapacity){
		if(newCapacity < size){
			return;
		}
		
		T[] old = item;
		item = (T[]) new Object[newCapacity];
		for(int i=0;i<size();i++){
			item[i] = old[i];
		}
	}
	
	public MyArrayList(){
		doClear();
	}
	
	public MyArrayList(int capacity){
		doClear();
		ensureCapacity(capacity);
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public void add(int index,T element){
		if(index<0 || index > size){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(item.length == size){
			ensureCapacity(size*2+1);
		}
		for(int i=size;i>index;i--){
			item[i] = item[i-1];
		}
		item[index] = element;
		size++;
	}
	
	public boolean add(T element){
		add(size,element);
		return true;
	}
	
	public T get(int index){
		if(index<0 || index>=size){
			throw new ArrayIndexOutOfBoundsException();
		}
		return item[index];
	}

	public T set(int index,T element){
		if(index<0 || index>=size){
			throw new ArrayIndexOutOfBoundsException();
		}
		T old = item[index];
		item[index] = element;
		return old;
	}
	
	public T remove(int index){
		if(index<0 || index>=size){
			throw new ArrayIndexOutOfBoundsException();
		}
		T moved = item[index];
		for(int i=size-1;i>index;i++){
			item[i-1] = item[i];
		}
		size--;
		return moved;
	}
	
	public void trimToSize(){
		ensureCapacity(size);
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T>{
		
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex<size;
		}

		@Override
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			return item[currentIndex++];
		}
		
		public void remove(){
			MyArrayList.this.remove(--currentIndex);
		}
		
	}
}
