package com.coding;

import java.util.NoSuchElementException;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	private void addLength(){
		int len = elementData.length;
		Object[] temp = new Object[len+50];
		for(int i=0;i<len;i++){
			temp[i] = elementData[i];
		}
		elementData = temp;
	}	
	
	public void add(Object o){
		if(size()<elementData.length){
			elementData[size] = o;
			size++;
		}else{
			addLength();
			elementData[size] = 0;
			size ++;
		}
	}
	public void add(int index, Object o){
		if(index<0||index>size()){
			throw new IndexOutOfBoundsException();
		}
		if(size==elementData.length){
			addLength();
		}
		for(int i=size;i>index;i--){
			elementData[i] = elementData[i-1];
		}
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if(index<0||index>=size()){
			throw new IndexOutOfBoundsException();
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index<0||index>=size()){
			throw new IndexOutOfBoundsException();
		}
		Object temp = elementData[index];
		for (int i = index; i < size-1; i++) {
			elementData[i] = elementData[i+1];
		}
		elementData[size-1] = null;
		size--;
		return temp;
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator{
		
		private Object[] array;
		private int index;
		
		public ArrayListIterator(){
			array = new Object[size];
			index = 0;
			for (int i = 0; i < size; i++) {
				array[i] = elementData[i];
			}
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(index>=0&&index<array.length){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(index<0||index>=array.length){
				throw new NoSuchElementException();
			}
			Object temp = array[index];
			index ++;
			return temp;
		}
		
	}
	
	
}
