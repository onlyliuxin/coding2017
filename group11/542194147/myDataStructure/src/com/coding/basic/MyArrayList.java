package com.coding.basic;

import java.util.Iterator;

public class MyArrayList implements MyList {

	/**
	 * 数组默认大小
	 */
	private static final int DEFAULT_SIZE = 10;
	/**
	 * 储存元素的数组
	 */
	private Object[] elementData =null;
	/**
	 * 数组大小指针;
	 */
	private int capacity;
	/**
	 * 当前游标
	 */
	private int current;
	
	public MyArrayList(int size){
		if(size<0){
			throw new RuntimeException("大小不能小于0");
		}else{
			this.elementData= new Object[size];
			this.current=0;
			this.capacity=size;
		}
	}
	
	public MyArrayList(){
		this(DEFAULT_SIZE);
	}
	
	@Override
	public void add(Object o) {
		isOverSize();//判断数组容量是否满足，不满足增加空间
		this.elementData[current]=o;
		this.current++;
	}

	@Override
	public void add(int index, Object o) {
		isOverSize();//判断数组容量是否满足，不满足增加空间
		isOutOfBoundIndex(index);//判断数组下标是否越界
		System.arraycopy(elementData, index, elementData, index+1, this.elementData.length-index);
		this.current++;
	}

	@Override
	public Object get(int index) {
		isOutOfBoundIndex(index);//判断数组下标是否越界
		return this.elementData[index];
	}

	@Override
	public Object remove(int index) {
		isOutOfBoundIndex(index);//判断数组下标是否越界
		Object o=this.elementData[index];
		if(this.elementData.length>index+1){
			System.arraycopy(elementData, index+1, elementData, index,this.elementData.length-index-1);
		}
		this.elementData[this.elementData.length-1]=null;
		return o;
	}

	public Iterator<?> iterator(){
		return new MyArrayListIterator();
	}
	
	@Override
	public int size() {
		return this.elementData.length;
	}

	/**
	 * 判断数组容量是否满足，不满足增加空间
	 */
	private void isOverSize() {
		if(this.current==this.capacity){
			this.capacity+=MyArrayList.DEFAULT_SIZE;
		}
		Object[]newElementData=new Object[this.capacity];
		for(int i=0;i<this.elementData.length;i++){
			newElementData[i]=this.elementData[i];
		}
		this.elementData=newElementData;
	}
	
	/**
	 * 判断数组下标是否越界
	 * @param index
	 */
	private void isOutOfBoundIndex(int index){
		if(index>this.capacity||index<0){
			throw new RuntimeException("数组下标越界");
		}
	}
	
	/**
	 * MyArrayList的迭代器
	 * @author 小摩托
	 *
	 */
	private class MyArrayListIterator implements Iterator<Object>{

		private int current=0;
		
		@Override
		public boolean hasNext() {
			return current<size();
		}

		@Override
		public Object next() {
			if(hasNext()==false){
				throw new RuntimeException("不存在对应元素");
			}else{
				return MyArrayList.this.get(current+1);
			}
		}

		@Override
		public void remove() {
			MyArrayList.this.remove(current);
			
		}
		
	}
}
