package com.coding.basic;


import java.util.Arrays;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList implements List {
	
	private int size = 0;
	private static final Object[] EMPTY_ELEMENTDATA={}; 
	private static final int DEFAULT_CAPACITY=10;
	private transient Object[] elementData;
	
	public ArrayList(){
		this.elementData=EMPTY_ELEMENTDATA;
	}

	public ArrayList(int initialCapacity) {
		if (initialCapacity > 0) {
			this.elementData = new Object[initialCapacity];
		} else {
			this.elementData = EMPTY_ELEMENTDATA;
		}
	}
	
	private void ensureCapacity(int minCapacity){
		if(elementData==EMPTY_ELEMENTDATA){
			minCapacity=DEFAULT_CAPACITY;
		}
		if(minCapacity>elementData.length){
			grow(minCapacity);
		}
	}
	
	private void grow(int minCapacity) {
		int oldCapacity=elementData.length;
		int newCapacity=oldCapacity+(oldCapacity>>1);
		if(newCapacity<minCapacity){
			newCapacity=minCapacity;
		}
		if(newCapacity>Integer.MAX_VALUE){
			newCapacity=Integer.MAX_VALUE;
		}
		elementData=Arrays.copyOf(elementData, newCapacity);
		
	}
	
	private void rangeCheckForAdd(int index){
		if(index<0&&index>size){
			throw new IndexOutOfBoundsException(outOfBsg(index));
		}
	}
	
	private void rangeCheck(int index){
		if(index<0&&index>=size){
			throw new IndexOutOfBoundsException(outOfBsg(index));
		}
	}
	private String outOfBsg(int index){
		return "index:"+index+";size:"+size;
	}

	public void add(Object o){
		ensureCapacity(size+1);
		elementData[size++]=o;
	}
	public void add(int index, Object o){
		rangeCheckForAdd(index);
		ensureCapacity(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		rangeCheck(index);
		int newNum=size-index-1;
		if(newNum>0){
			System.arraycopy(elementData, index+1, elementData, index, newNum);
		}
		elementData[--size]=null;
		return elementData;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	public Iterator iterator(){
		return new Itr();
	}
	
	private class Itr implements Iterator{

		int cursor;
		@Override
		public boolean hasNext() {
			return cursor!=size;
		}

		@Override
		public Object next() {
			int i = cursor;
            if (i >= size){
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length){
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return elementData[i];
		}
		
	}		
}
