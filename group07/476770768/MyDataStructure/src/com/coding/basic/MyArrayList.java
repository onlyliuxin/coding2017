package com.coding.basic;

import java.util.Arrays;

public class MyArrayList implements MyList{

	private int size = 0;
	
	private Object[] elementData = new Object[5];
	
	@Override
	/**
	 * add an element to the end
	 */
	public void add(Object o) {
		int index = size;
		if(isFull()){
			extendLength();
		}
		elementData[index] = o;
		size++;
	}

	@Override
	/**
	 * add an element to certain index
	 */
	public void add(int index, Object o) {
		checkBounds(index);
		if(isFull()){
			extendLength();
		}
		for(int i=size; i>=index; i--){
			elementData[i+1] = elementData[i];
		}
		elementData[index] = o;
		size++;
	}

	@Override
	/**
	 * get an element
	 */
	public Object get(int index) {
		checkBoundsForGet(index);
		if(index >= size){
			return null;
		}
		return elementData[index];
	}

	@Override
	/**
	 * remove an element
	 */
	public Object remove(int index) {
		checkBounds(index);
		Object res = elementData[index];
		for(int i=index+1; i<=size; i++){
			elementData[i-1] = elementData[i];
		}
		size--;
		return res;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * extends the length
	 */
	public void extendLength(){
		elementData = Arrays.copyOf(elementData, elementData.length * 2);
		//System.out.println("add extend "+elementData.length);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public boolean isFull(){
		int len = elementData.length;
		if(size >= len-1){
			return true;
		}
		return false;
	}
	
	public void checkBounds(int index){
		if(index > size || index < 0){
			//System.out.println("From MyArrayList: Index out of bounds");
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
	}
	
	/**
	 * for get()
	 * @param index
	 */
	public void checkBoundsForGet(int index){
		if(index >= elementData.length || index < 0){
			//System.out.println("From MyArrayList: Index out of bounds");
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
	}
	
	public String OutOfBoundsMsg(int index){
		return "Index: "+index+", Size: "+size;
	}

	@Override
	public String toString() {
		String s = "";
		for(int i=0; i<size; i++){
			s += elementData[i] + " ";
		}
		return s;
	}
	
	public MyIterator iterator() {
		return new ArrayListIterator(this);
	}
	
	private class ArrayListIterator implements MyIterator{
		
		private MyArrayList eleIterator;
		int pos;//current position
		int len;//size of elementData
				
		private ArrayListIterator(MyArrayList mal){
			eleIterator = mal;
			pos = 0;
			len = mal.size();
		}
		
		@Override
		public boolean hasNext() {
			return pos < len;
		}

		@Override
		public Object next() {
			return eleIterator.get(pos++);
		}
		
	}
}
