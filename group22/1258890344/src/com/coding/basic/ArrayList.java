package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if(this.size==elementData.length){
			elementData=Arrays.copyOf(elementData, size+1);
			elementData[size]=o;
		}else{
			elementData[size]=o;	
		}
		size++;
		
	}
	public void add(int index, Object o){
		if(index<0||index>=this.size){
			throw new ArrayIndexOutOfBoundsException("数组越界异常");
		}else{
			if(index<this.size){
				System.arraycopy(elementData, index, elementData, index+1, size-index);
				elementData[index]=o;
				size++;
			}else{
				elementData=Arrays.copyOf(elementData, index+1);
				elementData[index]=o;
				size=index+1;
			}
		}
		
		
		
		
	}
	
	public Object get(int index){
		if(index<0||index>elementData.length){
			throw new ArrayIndexOutOfBoundsException("数组越界异常");
		}
		return elementData[index];
		
	}
	
	public Object remove(int index){
		if(index<0||index>=elementData.length){
			throw new ArrayIndexOutOfBoundsException("数组越界异常");
		}else{
			Object deletedElement=elementData[index];
			if(index<size){
				System.arraycopy(elementData, index+1, elementData, index, size-index-1);
				size--;
				return deletedElement;
			}else{
				System.arraycopy(elementData, index+1, elementData, index, size-index-1);
				return deletedElement;
			}
			
		}
		
		
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
