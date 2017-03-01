package com.coding.basic;

import java.util.NoSuchElementException;

public class ArrayList implements List {
	
	private int size = 0;	
	/*扩容因子*/
	private static final int GENE = 10;
	
	private Object[] elementData = new Object[10];	
	/*扩容引用*/
	private Object[] newElementData;
	
	public void add(Object o){
		grow();
		elementData[size] = o;		
		size ++;
	}
	public void add(int index, Object o){
		
		if(index>size){
			throw new IndexOutOfBoundsException("Index: "+index+",Size:"+size);
		}
		grow(); 
		if(index<size){//长度足够需要移动
			newElementData = new Object[elementData.length];
			System.arraycopy(elementData, 0, newElementData, 0, index);
			System.arraycopy(elementData, index, newElementData, index+1, size-index);
			elementData = newElementData;
		}
		elementData[index] = o;	
		size ++;
	}
	
	public Object get(int index){
		
		if(index>size){
			throw new IndexOutOfBoundsException("Index: "+index+",Size:"+size);
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		
		Object o = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size-(index+1));		
		size --;
		return o;
	}
	
	public int size(){
		return size;
	}
			
	/**
	 * 扩容，扩容因子为10
	 */
	private void grow(){
		
		if(size>=elementData.length){//长度不够需要扩容
			newElementData = new Object[size+GENE];
			System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
			elementData = newElementData;		
		}		
	}
	
	
    public Iterator iterator(){
		
		return new Itr();
	}
    
    private class Itr implements Iterator{

    	int cursor;   	
		@Override
		public boolean hasNext() {
			return cursor != ArrayList.this.size;
		}

		@Override
		public Object next() {
			
			int i = this.cursor;
			if (i >= ArrayList.this.size){
				throw new NoSuchElementException();
			}			
			this.cursor = (i + 1);
			return ArrayList.this.elementData[i];
		}
    	
    }
}
