package com.ecust.test;
import java.util.*;
public class GArrayList<T> implements GList<T> {
	
	private int size;
	private Object[] dataArray= new Object[0];

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {		
		return this.size == 0;
	}

	@Override
	public boolean contains(Object o) {
		for(Object obj:dataArray) {
			if(Objects.equals(obj, o))
				return true;
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		System.arraycopy(dataArray, 0, array, 0, size);
		return array;
	}

	@Override
	public boolean add(T t) {
		ensureCapacity(size+1);
		dataArray[size] = t;
		size++;
		return true;
	}

	

	@Override
	public boolean remove(T t) {
		int index = indexof(t);
		if(index < 0) {
			return false;
		}
		System.arraycopy(dataArray, index+1, dataArray, index, size-1-index);
		dataArray[size-1] = null;
		size--;
		return true;
	}

	@Override
	public void clear() {
		dataArray = new Object[size];
		size = 0;
	}

	@Override
	public T get(int index) {
		if(index < -1 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return (T)dataArray[index];
	}

	@Override
	public T set(int index, T t) {
		if(index < -1 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		dataArray[index] = t;
		return t;
	}

	@Override
	public void add(int index, T t) {
		if(index < -1 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		ensureCapacity(size+1);
		System.arraycopy(dataArray, index, dataArray, index+1, size-index);
		dataArray[index] = t;
		size++;
	}

	@Override
	public T remove(int index) {
		if(index < -1 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T element = (T)dataArray[index];
		System.arraycopy(dataArray, index+1, dataArray, index, size-1-index);
		dataArray[size-1] = null;
		size--;
		return element;
	}

	@Override
	public int indexof(T t) {
		for(int i = 0;i<size;i++) {
			if(Objects.equals(t, dataArray[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public GIterator<T> iterator() {
		return new ArrayListIterator(this);		
	}
    private void ensureCapacity(int i) {
		if(i > dataArray.length) {
			int newlength = Math.max(i, dataArray.length*2);
			Object[] newDataArray = new Object[newlength];
			System.arraycopy(dataArray, 0, newDataArray, 0, dataArray.length);
			dataArray = newDataArray;
		}
	}
    private class ArrayListIterator implements GIterator<T> {
    	private int position;
    	private GArrayList<T> list;
    	
    	ArrayListIterator(GArrayList<T> list) {
    		this.list = list;
    	}

		@Override
		public boolean hasNext() {
			
			return position < list.size;
		}

		@Override
		public T next() {
			if(hasNext()) {
				return list.get(position++);
			}
			return null;
		}
    	
    }

}
