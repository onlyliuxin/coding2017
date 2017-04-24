package com.github.ipk2015.coding2017.basic.array;

import java.util.Arrays;

import com.github.ipk2015.coding2017.basic.Iterator;
import com.github.ipk2015.coding2017.basic.List;
import com.github.ipk2015.coding2017.basic.ListUtils;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		add(size,o);
	}
	/*
	 * 分两种情况，index的范围为0到size,超出则抛出异常
	 * */
	public void add(int index, Object o){
		ListUtils.checkIndexInRange(index,size);
		if(size==elementData.length){
			elementData=Arrays.copyOf(elementData, size+1);
		}
		if(index<size){
			for(int i=size;i>index;i--){
				elementData[i]=elementData[i-1];
			}
		}
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		ListUtils.checkIndexInRange(index,size-1);
		return elementData[index];
	}
	
	public Object remove(int index){
		ListUtils.checkIndexInRange(index,size-1);
		Object object=elementData[index];
		for(int i=index;i<size-1;i++){
			elementData[i]=elementData[i+1];
		}
		elementData[size-1]=null;
		size--;
		return object;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		
		return new Iterator(){
		    private int currentPos=0;	
			@Override
			public boolean hasNext() {
				if(size==0){
					return false;
				}else{
					if(currentPos<size){
						return true;
					}else{
						return false;
					}
				}
			}

			@Override
			public Object next() {
				Object object= get(currentPos);
				currentPos++;
				return object;
			}
			
		};
	}
}
