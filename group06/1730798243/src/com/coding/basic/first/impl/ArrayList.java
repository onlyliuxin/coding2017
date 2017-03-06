package com.coding.basic.first.impl;

import com.coding.basic.first.List;

/**
 * 实现一个ArrayList 
 * @author zap
 *
 */

public class ArrayList implements List{

	private int size;
	private Object[] elementData = null;
	private static final int DEFAULT_SIZE=100;
	
	public ArrayList() {
		this(DEFAULT_SIZE);
	}
	
	public ArrayList(int size) {
		if(size < 0) {
			System.out.println("size 必须大于0");
		} else {
			this.elementData = new Object[size];
		}
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(Object o) {
		judgeSize(size+1);
		elementData[size++] = o;
	}

	@Override
	public void add(int index, Object o) {
		judgeIndexRangge(index);
		System.arraycopy(elementData, index, elementData, index + 1,
                size - index);//整体往后挪移--当前往后挪
		elementData[index] = o;
		size++;
		
	}

	@Override
	public Object get(int index) {
		judgeIndexRangge(index);
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		judgeIndexRangge(index);
		Object e =  elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index,
                size - index - 1);//整体往前挪移--后一位往前挪
		size--;
		return e;
	}
	
	private void judgeIndexRangge(int index){
		 
		 if (index > size || index < 0)
	            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	 }
	 
	 private String outOfBoundsMsg(int index) {
	        return "Index: "+index+", Size: "+size;
	   }

	 
	 private void judgeSize(int size) { 
		 if(size > elementData.length){
			 Object[] newarr = new Object[elementData.length + DEFAULT_SIZE];  
			 System.arraycopy(elementData, 0, newarr, 0, elementData.length); 
			 this.elementData = newarr;
		 }
	  }  
	

	
}
