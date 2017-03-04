package com.coding.basic;

/** 
 * @ClassName: ArrayList 
 * @Description: 自增长数组
 * @author: tangxp
 * @date: 2017年2月23日 下午10:43:03  
 */
public class ArrayList implements List {
	
	private final int  step = 10;
	private Object elementData[] = new Object[100];
	private int size = 0 ;
	
	
	/**
	 * @Title: add
	 * @Description: TODO
	 * @param o , elements of this ArrayList
	 * @see com.coding.basic.List#add(java.lang.Object) 
	 */
	public void add(Object o) {
		add(size,o);
	}

	
	/**
	 * @Title: add
	 * @Description: TODO
	 * @param index
	 * @param o 
	 * @see com.coding.basic.List#add(int, java.lang.Object) 
	 */
	public void add(int index, Object o) {
		if(index < 0 || index> size) {
			throw new IllegalArgumentException("下标越界");
		}
		
		if(null == o) {
			throw new IllegalArgumentException("元素不能为空");
		}
		
		if(this.checkOutOfBounds()) {
			this.autoGrow(this.step);
		}
		
		int i = size;
		while(i>index) {
			elementData[i] = elementData[--i];
		}
		addDriect(i,  o);
	}

	
	public Object get(int index) {
		if(index < 0 || index>= size) {
			throw new IllegalArgumentException("下标越界");
		}
		
		return elementData[index];
	}

	public Object remove(int index) {
		if(index < 0 || index>= size) {
			throw new IllegalArgumentException("下标越界");
		}
		
		Object o = elementData[index];
		while (index<size-1) {
			elementData[index] = elementData[++index];
		}
		elementData[size] = null;
		this.size--;
		return o;
	}

	public int size() {
		return this.size;
	}
	
	
	/**
	 * @param growSize
	 * 扩展elementData数组growSize大小
	 */
	private void autoGrow(int growSize) {
		if (elementData.length>this.size) {
			return;
		}
		Object newElementData[] = new Object[elementData.length+growSize];
		System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
		elementData = newElementData;
		return;
	}
	
	private void addDriect(int index, Object o) {
		elementData[index] = o;
		this.size++;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(int i =0;i<this.size;i++) {
			sb.append(elementData[i].toString());
			sb.append(","); 
		}
		sb.replace(sb.length()-1, sb.length(), "]");
		return sb.toString();
	}
	
	private boolean checkOutOfBounds()  {
		if (elementData.length>this.size) {
			return false;
		}else {
			return true;
		}
	}
}
