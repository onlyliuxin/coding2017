package com.coding.datastructs;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;


public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = null;
	
	public ArrayList(){
		size=0;
		elementData=new Object[10];
	}
	
	public void add(Object o){
		size();
		elementData=grow(elementData,1);
		elementData[size] = o;
	}
	public void add(int index, Object o){
		 size();
		if (index >size || index < 0||index == size)
		    throw new IndexOutOfBoundsException(
			"Index: "+index+", Size: "+size);
		Object temp = elementData;
		elementData=grow(elementData,1);  // Increments modCount!!
		System.arraycopy(temp, index, elementData, index + 1,
				 size - index);
		elementData[index] = o;
	}
	
	public Object get(int index){
		size();
		if (index > size || index < 0 ||index == size)
		    throw new IndexOutOfBoundsException(
			"Index: "+index+", Size: "+size);
		return elementData[index];
	}
	
	public Object remove(int index){
		size();
		if (index > size || index < 0 ||index == size)
		    throw new IndexOutOfBoundsException(
			"Index: "+index+", Size: "+size);
		Object tempData = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index,
				 size - index);
		return tempData;
	}
	
	public int size(){
		for(int i=0;i<elementData.length;i++){
			if(null==elementData[i]){
				size=i;
				break;
			}
		}
		return size;
	}
	public static Object[] grow(Object[] src,int size){
		Object[] target = new Object[src.length+size];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	} 
	public Iterator iterator(){
		return new Itera();
	}
	private class Itera implements Iterator{
		int cursor; 
		int lastRet = -1; 

		
		public boolean hasNext() {
			size();
			// TODO Auto-generated method stub
			return cursor!=size;
		}
		public Object next() {
			// TODO Auto-generated method stub
			int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return  elementData[lastRet = i];
		}

		
}
	
	
	
}
