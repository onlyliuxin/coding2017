package com.coding.basic;

import java.util.*;

public class MyArrayList implements MyList{
	//定义Object类型数组
	//定义数组元素个数
	private int size=0;	
	private Object [] elementData =new Object[10];
	
	public void add(Object o) {	
		ensureCapacity(size+1);
		elementData[size] = o;
		size++;
	}
	
	//添加指定位置的元
	public void add (int index,Object element){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException("数组角标越界");
		ensureCapacity(size+1);
		//添加指定位置元素
		//将该位置后的有元素右
		System.arraycopy(elementData,index,elementData,index+1,size-index);
		elementData[index] =element;
		size++;
	}

	//可调整数组的容量
	public void ensureCapacity (int mincapacity){
		int oldlen =elementData.length;
		if(mincapacity > oldlen){
			int newlen =(oldlen * 3)/2 + 1;
				if(mincapacity > newlen)
					newlen =mincapacity;
			elementData =Arrays.copyOf(elementData,newlen);
		}
	}


	//获取指定位置的元
	public Object get(int index){
		if(index < 0 || index >size-1){
			throw new IndexOutOfBoundsException("数组角标越界");
		}		
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index >=size || index < 0){
			throw new IndexOutOfBoundsException("数组角标越界");
		}	
		Object oldelement =elementData[index];
		int numMoved = size-index-1;
			if(numMoved > 0){
				System.arraycopy(elementData,index+1,elementData,index,numMoved);
			}	
			size--;
		return oldelement;
	}
	
	public void clear(){
		elementData = null;
	}

	public boolean isEmpty (){
		return size == 0;
	}
	
	public int size (){
		return size;
	}
}
