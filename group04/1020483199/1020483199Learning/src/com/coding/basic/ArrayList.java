package com.coding.basic;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList implements List {
	
	
	private int size = 0;
	
	private transient Object[] elementData = new Object[100];
	/**
	 * 向数组中添加某个元素
	 */
	public void add(Object o){
		/**
		 * 数组扩容判断
		 */
		ensureSize(size+1);
		elementData[size++] = o;
	}
	/**
	 * 向指定位置数组中添加某个元素
	 */
	public void add(int index, Object o){
		if(index<0||index>size){
			throw new IndexOutOfBoundsException("数组越界");
		}
		ensureSize(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
	}
	/**
	 * 获取数组中某个位置元素
	 */
	public Object get(int index){
		if(index<0||index>elementData.length){
			return null;
		}else{
			return elementData[index];
		}
		
	}
	/**
	 * 移除数组中指定位置元素
	 */
	public Object remove(int index){
		if(index<0||index>elementData.length){
			return null;
		}else{
			int newLength = size-index-1;
			if (newLength>0)
			System.arraycopy(elementData, index+1, elementData, index, size-index-1);
			elementData[--size] = null;
			return elementData;
		}
	}
	/**
	 * 获取当前数组的大小
	 */
	public int size(){
		if(size>0){
			return this.size;
		}else{
			return 0;
		}
	}
	/**
	 * 利用arraylist实现迭代器
	 * @return
	 */
	public Iterator iterator(){
		
		return new ArrayListIterator();
	}
	private class ArrayListIterator implements Iterator{
		int cursor;
		int lastReset = -1;
		@Override
		public boolean hasNext() {
			return size!=cursor;
		}

		@Override
		public Object next() {
			//标记索引当前位置
			int i = cursor;
			if(i>size){
				throw new NoSuchElementException();
			}
			Object[] newData = elementData;
			if(i>newData.length){
				throw new ConcurrentModificationException();
			}
			cursor = i + 1;
			return newData[lastReset = i];
		}
		
	}
	
	
	/**
	 * @author sulei
	 * @param minCapcity
	 */
	public void ensureSize(int minCapcity){
		if(minCapcity>elementData.length){
			grow(minCapcity);
		}
	}
	
	/**
	 * @author sulei
	 * @param autoCapcity
	 */
	public void grow(int autoCapcity){
		int oldLength = elementData.length;
		if(autoCapcity>oldLength){
			Object[] oldData = elementData; 
			int newLength = (oldLength * 3)/2 + 1;
			if(autoCapcity>newLength){
				newLength=autoCapcity;
			}
			elementData = Arrays.copyOf(elementData, newLength);
		}
	}
}
