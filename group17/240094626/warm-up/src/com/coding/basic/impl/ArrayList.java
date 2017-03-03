package com.coding.basic.impl;

import com.coding.basic.Iterator;
import com.coding.basic.List;

/**
 * 
 * @描述: ArrayList简单实现
 * @作者:240094626
 * @创建日期:2017-2-20
 */
public class ArrayList implements List  {
	
	/**
	 * @comment:元素数组
	 */
	private Object data[] = null;
	
	/**
	 * @comment:数组元素个数
	 */
	private int size = 0;
	
	/**
	 * 无参构造函数，初始化容量为10的空列表
	 */
	public ArrayList(){
		this(10);
	}
	
	/**
	 * @param length
	 * 构造函数，初始化容量为length的空列表
	 */
	public ArrayList(int length){
		if(length < 0){
			throw new IllegalArgumentException("初始容量参数非法："+length);
		}
		data = new Object[length];
	}
	
	
	/**
	 * @createTime: 2017-2-21 下午1:32:28
	 * @param length
	 * @return:void
	 * @comment：列表结构扩展容量，每次增加原来的1/2容量
	 */
	private void grow(int length){ 
		int oldLength = data.length;
		if(length > oldLength){
			Object oldData[] = data;
			int newLength = oldLength*3/2 + 1;
			if(newLength < length){
				newLength = length;
			}
			data = new Object[newLength];
			System.arraycopy(oldData, 0, data, 0, oldLength);
		}
	}
	
	/**
	 * @createTime: 2017-2-21 下午1:32:05
	 * @param index
	 * @return:void
	 * @comment：检验下标参数是否超限
	 */
	private void check(int index) {
		if( index >= size){
			throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
		}
	}
	
	@Override
	public void add(Object o) {
		grow(size+1);
		data[size++]=o;
	}

	@Override
	public void add(int index, Object o) {
		if( index > size || index < 0){
			throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
		}
		grow(size+1);
		System.arraycopy(data, index, data, index+1, size-index);
		data[index] = o;
		size++;
		
	}

	@Override
	public Object get(int index) {
		check(index);
		return data[index];
	}

	

	@Override
	public Object remove(int index) {
		check(index);
		Object remove = data[index];
		System.arraycopy(data, index+1, data, index, size-index);
		data[--size] = null;
		return remove;
	}

	@Override
	public int size() {
		return size;
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<size ;i++){
			if(i > 0){
				sb.append(",");
			}
			sb.append(data[i]);
		}
		return String.format("ArrayList {data=[%s], size=%d}", sb.toString(),size);
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	/** 
	 * @描述: 简单实现迭代器
	 * @作者:240094626
	 * @创建日期:2017-2-21
	 */  
	private class ArrayListIterator implements Iterator{

		/**
		 * @column:index
		 * @comment:当前位置下标
		 */
		private int index;
		
		/**
		 * 无参构造，初始化迭代器的下标为0
		 */
		public ArrayListIterator(){
			index = 0;
		}
		
		@Override
		public boolean hasNext() {
			if(index < size){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			Object o = get(index++);
			return o;
		}
		
	}

}


