package com.oneflyingleaf.util;

import java.util.Arrays;

public class ArrayList implements List {
	
	//非线程安全
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		
		checkLength(1);
		
		elementData[ size ++] = o;
	}
	
	
	public void add(int index, Object o){
		checkBound(index);
		
		if(index != size){
			checkLength(1);
		}
		
		int temp = index;
		
		//index == size  不移动，直接放 
		while(index < size){
			elementData[ ++ index ]  = elementData[index];
		}
		
		size ++ ;
		elementData[temp] = o;
	}
	
	public Object get(int index){
		checkBound(index);
		
		return elementData [index];
	}
	
    public Object remove(int index){
    	checkBound(index);
    	
    	Object ret = elementData[index];
    	while(index < size){
    		elementData[index] = elementData[ ++ index];
    	}
    	
    	-- size ;
		
    	return ret;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	/**
	 * 判断扩容
	 * @param length
	 */
	private void checkLength(int length){
		if( (size + length) > elementData.length){
			//默认扩大一倍
			Arrays.copyOf(elementData, elementData.length << 1);
		}
	}
	
	/**
	 * 校验是否超出
	 * @param index
	 */
	private void checkBound(int index){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("size : " + size +" , index : " + index);
		}
	}
	
	
	private class ArrayListIterator implements Iterator{
		
		private int count = -1;
		
		private ArrayListIterator(){
		}
		
		@Override
		public boolean hasNext() {
			return  (count+1) < size?true:false;
		}

		@Override
		public Object next() {
			count ++ ;
			return elementData[count];
		}
		
	}
}
