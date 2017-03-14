package com.github.ZhoufeifeiJAVA.coding2017.basic;

public class MyArrayList implements List {
	
	private int size = 0;
	 
	private Object[] elementData = new Object[10];
	  
	public void add(Object o){
		if(size == elementData.length){
			elementData = arrayGrow(elementData,size/2);
		}
		elementData[size] = o;
		size ++;
	}
	public void add(int index, Object o)throws RuntimeException{
		if(index > size)
			throw new RuntimeException("index is bigger than the size of MyArrayList");
		if(size == elementData.length){
			elementData = arrayGrow(elementData,size/2);
		}	
		if(index == size)
			add(o);
		else{
			for(int i=size;i>index;i--){
				elementData[i] = elementData[i-1];
			}
			elementData[index] = o;
		}	
		size ++;
	}
	private Object[] arrayGrow(Object[] src,int size){
		Object[] target = new Object[src.length+size];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	}
	public Object get(int index){
		if(index >= size)
			return null;
		else
			return elementData[index];
	}
	
	public Object remove(int index)throws IndexOutOfBoundsException{
		if(index>=size || index<0)
			throw new IndexOutOfBoundsException("index is bigger than the size of MyArrayList");
		Object removeObject = elementData[index];
		for(int i=index;i<size-1;i++)
			elementData[i] = elementData[i+1];				
		size --;	
		return removeObject;
	}
	
	public int size(){
		return size;
	}
	
	private class MyArrayListIterator implements Iterator{
		private int pointer;
		MyArrayListIterator(){
			pointer = 0;
		}
		public boolean hasNext() {
			if(pointer < size)
				return true;
			else
				return false;
		}
		public Object next() {
			pointer ++;
			return elementData[pointer-1];
		}
		
	}
	public Iterator iterator(){
		return new MyArrayListIterator();
	}
}
