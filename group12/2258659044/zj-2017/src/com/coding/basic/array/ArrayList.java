package com.coding.basic.array;

import java.util.NoSuchElementException;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList<E> implements List<E> {
	
	private int size = 0;	
	/*扩容因子*/
	private static final int GENE = 10;
	
	private Object[] elementData = new Object[10];	
	/*扩容引用*/
	private Object[] newElementData;
	
	public void add(E o){
		grow();
		elementData[size] = o;		
		size ++;
	}
	public void add(int index, E o){
		
		if(index<0||index>size){
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
	
	public E get(int index){
		
		rangeCheck(index);
		return elementData(index);
	}
	
	public E remove(int index){
		
		rangeCheck(index);
		E o = elementData(index);
		int i = this.size - index - 1;
		if (i > 0) {
		    System.arraycopy(this.elementData, index + 1, this.elementData, index, i);
		}
		this.elementData[(--this.size)] = null;	
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public Object[] toArray(){
		Object[] objArr = new Object[size];
		System.arraycopy(elementData, 0, objArr, 0, size);
		return objArr;
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
	
	private void rangeCheck(int index) {
		
		if (index >= this.size) {
			throw new IndexOutOfBoundsException("Index: "+index+",Size:"+size);
		}
	}
	
	@SuppressWarnings("unchecked")
	E elementData(int index) {  
	    return (E) elementData[index];  
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