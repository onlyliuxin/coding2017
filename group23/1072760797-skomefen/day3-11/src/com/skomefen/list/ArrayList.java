package com.skomefen.list;

public class ArrayList implements List {

	
	private int size = 0;
		
	private Object[] elementData = new Object[100];
	
	private Iterator iterator ;
	
	public void add(Object o){
		
		if(size<elementData.length){
			elementData[size]=o;
			size++;
			return;
		}
		Object[] dest = new Object[size+100];
		insertofArray(size, o, dest);
		size++;
	}
	public void add(int index, Object o){
		
		if(index>size||index<0){
			throw new IndexOutOfBoundsException("index:"+index+"size:"+size);
		}
		
		if(size<elementData.length){
			Object[] dest = new Object[elementData.length];
			insertofArray(index, o, dest);
			size++;
			return;
		}
		Object[] dest = new Object[size+100];
		insertofArray(index, o, dest);
		size++;
	}
	private void insertofArray(int index, Object o, Object[] dest) {
		dest[index]=o;
		System.arraycopy(elementData, 0, dest, 0, index);
		if((elementData.length-index)!=0){	
			System.arraycopy(elementData, index, dest, index+1, elementData.length-1-index);
			if(elementData[elementData.length-1]!=null){
				dest[elementData.length]=elementData[elementData.length-1];
			}
		}
		
		elementData = dest;
	}
	
	public Object get(int index){
		if(index>size||index<0){
			throw new IndexOutOfBoundsException("index:"+index+"size:"+size);
		}
		
		return elementData[index];
		
	}
	
	public Object remove(int index){
		if(index>size||index<0){
			throw new IndexOutOfBoundsException("index:"+index+"size:"+size);
		}	
		Object revalue = elementData[index];
		Object[] dest = new Object[elementData.length];
		System.arraycopy(elementData, 0, dest, 0, index);
		System.arraycopy(elementData, index+1, dest, index, elementData.length-1-index);
		elementData = dest;
		size--;
		return revalue;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		iterator = new ArrayListIterator(this);
		return iterator;
	}
	
}
