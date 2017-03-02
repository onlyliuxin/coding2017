package com.github.congcongcong250.coding2017.basic;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[10];
	
	public void add(Object o){
		
		//check size limit
		if(size + 1 > elementData.length){
			int newlength = elementData.length * 3 / 2 + 1;
			elementData = Arrays.copyOf(elementData, newlength);
		}
		
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		//index Check
		checkIndex(index);
		
		//check size limit
		if(size + 1 > elementData.length){
			int newlength = elementData.length * 3 / 2 + 1;
			elementData = Arrays.copyOf(elementData, newlength);
		}
		
		for(int i = ++size; i >= index; i-- ){
			elementData[i] = elementData[i-1];
		}
		
		elementData[index] = o;
		
		
	}
	
	public Object get(int index){
		//index Check
		checkIndex(index);
		
		return elementData[index];
	}
	
	public Object remove(int index){
		//index Check
		checkIndex(index);
		
		Object old = elementData[index];
		for(int i = index; i < size-1 ; i++ ){
			elementData[i] = elementData[i+1];
		}
		elementData[--size] = null;
		return old;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Itr();
	}
	
	public void clear(){
		elementData = new Object[10];
		size = 0;
	}
	
	private void checkIndex(int index){
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index:"+index+" Size:"+size);
		}
	}
	
	
	private class Itr implements Iterator{
		//index for next element to visit
		private int cursor = 0;
		
		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public Object next() {
			if(cursor >= size){
				throw new NoSuchElementException();
			}
			return elementData[cursor++];
		}

		@Override
		public void remove() {
			//Check bound
			if(cursor == 0){
				throw new NoSuchElementException();
			}
			
			ArrayList.this.remove(--cursor);
			
		}
	}
	
	
	
}
