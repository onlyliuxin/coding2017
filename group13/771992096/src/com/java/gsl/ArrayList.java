package com.java.gsl;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList implements List {
	
	private Object[] elementData = new Object[100];
	private int size = elementData.length;
	
	public void add(Object o){
		checkLength(size+1);
		elementData[size++] = o;
	}
	public void add(int index, Object o){
		if(index < 0 || index > size){
			System.out.println("输入有误");
			return;
		}
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if(index > size) {
			System.out.println("出界");
			return null;
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index < 0 || index > size){
			System.out.println("输入有误");
			return null;
		}
		Object o = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[size--] = null;
		return o;
	}
	
	public int size(){
		return elementData.length;
	}
	//判断是否越界 如果越界添加长度
	public void checkLength(int l){
		int oldSize = elementData.length;
		if(l > oldSize){ //大于原来的长度创建新的数组
			elementData = Arrays.copyOf(elementData, l+100);
		}
	}
	
	public Iterator iterator(){
		return new Iterator(){
			int cursor;
			public boolean hasNext() {
				
				return cursor != size ;
			}

			public Object next() {
				 int i = cursor;
		            if (i >= size)
		                throw new NoSuchElementException();
		            Object[] elementData_ = elementData;
		            if (i >= elementData_.length)
		                throw new ConcurrentModificationException();
		            cursor = i + 1;
		            return elementData_[i];
			}
			
		};
		
	}

	

	
	
}
