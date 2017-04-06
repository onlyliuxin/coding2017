package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private int modifyNum = 0;
	
	private Object[] elementData = new Object[100];
	public void add(Object o){
		elementData[size]=o;
		size++;
	}
	public void add(int index, Object o){
		for(int i=size;i>index;i--)
		{
			elementData[i]=elementData[i-1];
		}
		elementData[index]=o;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		Object oj=elementData[index];
		for(int i=index;i<size;i++)
		{
			elementData[i]=elementData[i+1];
		}
		elementData[size--]=null;
		return oj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	public ArrayListIterator getMyIterator(){
		return new ArrayListIterator();
	}
	
	public class ArrayListIterator implements Iterator{
		
		int pos = 0;
		
		@Override
		public boolean hasNext() {
			return pos!=size;
		}

		@Override
		public Object next() {
			return elementData[pos++];
		}
		
		public void remove(){
			
		}
		
	}
	
}
