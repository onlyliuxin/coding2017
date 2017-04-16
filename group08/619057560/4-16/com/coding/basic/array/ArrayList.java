package com.coding.basic.array;

import java.util.Arrays;
import java.util.NoSuchElementException;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if (size + 1 > elementData.length) {
			elementData = Arrays.copyOf(elementData, elementData.length * 3 / 2 + 1);
		}
		elementData[size++] = o;
	}
	public void add(int index, Object o){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (size + 1 > elementData.length) {
			elementData = Arrays.copyOf(elementData, elementData.length * 3 / 2 + 1);
		}
		
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Object old = elementData[index];
		size--;
		System.arraycopy(elementData, index+1, elementData, index, size-index);
		elementData[size] = null;
		
		return old;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Itr();
	}
	
	private class Itr implements Iterator {
		
		int cursor = 0;
		boolean hasCalledRemove = false;

		@Override
		public boolean hasNext() {
			return (cursor < size);
		}

		@Override
		public Object next() {
			if (cursor < 0 || cursor >= size) {
				throw new NoSuchElementException();
			}
			hasCalledRemove = false;
			return elementData[cursor++];
		}

		@Override
		public void remove() {
			if (hasCalledRemove || cursor <= 0 || cursor > size) {
				throw new IllegalStateException();
			}
			hasCalledRemove = true;
			System.arraycopy(elementData, cursor - 1, elementData, cursor, size-cursor);
			size--;
		}
		
	}
	
}
