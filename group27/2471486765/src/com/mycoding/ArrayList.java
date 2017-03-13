package com.mycoding;

import java.util.NoSuchElementException;

public class ArrayList implements List {
	
	private int size = 0;
	private Object[] elementData = new Object[10];
	
	
	//增加元素
	public void add(Object o) {
		if (size()>=elementData.length) {
			elementData = ArrayList.grow(elementData,elementData.length);
		}
		elementData[size++] = o;		
	}
	
	//在指定位置上增加元素
	public void add(int index, Object o) {	
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}			
		else if(index == 0 || index > 0 && index < size()) {
			elementData = ArrayList.grow(elementData,elementData.length);
			for(int i=size()-1;i>=index;i--) {
			elementData[i+1] = elementData[i];
			}
			elementData[index] = o;
			size++;		
		}		
		else if(index == size()) {
			elementData = ArrayList.grow(elementData,elementData.length);
			elementData[size++] = o;			
		}	
	}
	
	//返回此列表中指定位置上的元素
	public Object get(int index) {
		if (index < 0 || index >= size()) {
			 throw new IndexOutOfBoundsException();		
		} else {
			return elementData[index];
		}	
	}
	
	//删除指定位置的元素
	public Object remove(int index) {
		if (index < 0 || index >= size()) {
			 throw new IndexOutOfBoundsException();			
		}	
		if(index == 0 || index > 0 && index < size()) {
			for(int i=index;i<size()-1;i++){
				elementData[i] = elementData[i+1];
			}
			size--;		
		}		
		return elementData;		
	}
	
	public int size() {
		return size;
	}

	//扩容
	public  static Object[] grow(Object[] src,int size) {
		Object[] target = new Object[2*size];
		System.arraycopy(src, 0, target, 0, size);
		return target;
	}

	public Iterator iterator() {
		return new ArrayListIterator(this);
		
	}
	
	private class ArrayListIterator implements Iterator {
		@SuppressWarnings("unused")
		ArrayList array = null;
		int cursor=0;
		private ArrayListIterator(ArrayList array) {
			this.array = array;
		}
		
		@Override
		public boolean hasNext() {			
			return cursor < size;
		}
		
		@Override
		public Object next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}		
			return elementData[cursor++];
		}	
	}
	
	@Override
	public String toString() {		
		StringBuffer str = new StringBuffer();
		str.append("[");		
		for (int i = 0; i < size(); i++) {
			str.append(elementData[i]+",");
		}
		str.deleteCharAt(str.length()-1);	
		str.append("]");
		return str.toString();
	}
}
