package com.hans;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class ArrayList implements List {
	
	private int size = 0;//ArrayList中已经存储的元素个数，也是下一个存入的元素的下标。
	
	private Object[] elementData = null;
	
	public ArrayList() {
		elementData = new Object[3];
	}
	
	public ArrayList(int capacity){
		elementData = new Object[capacity];
	}
	
//	private static final final default
	
	/**
	 * 将参数o添加到ArrayList中最后一个元素的后面
	 * @param o 添加的元素
	 */
	public void add(Object o){
		checkSize();
//		add(size++, o);
		elementData[size++] = o;
	}
	
	/**
	 * 将参数o添加到指定的index位置
	 * index应满速 index >= 0 && index <= size() 为 true
	 * @param index 添加的位置
	 * @param o 添加的元素
	 */
	public void add(int index, Object o){
		if(index > size || index < 0){
			//size代表即将存入的元素的下标，所以index等于size也可以。
			throw new ArrayIndexOutOfBoundsException("Index:" + index);
		}
		checkSize();
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++; 
	}
	
	/**
	 * 获取输入的index位置处元素
	 * index应满足 index >= 0 && index < size() 为 true
	 */
	public Object get(int index){
		checkIndex(index);
		return elementData[index];
	}
	
	/**
	 * 删除输入的index位置处的元素
	 * index应满足 index >= 0 && index < size() 为 true
	 * @return 被删除的元素
	 */
	public Object remove(int index){
		checkIndex(index);
		Object obj = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		size--;
		return obj;
	}
	
	/**
	 * 获取当前ArrayList中已经存储的元素的个数
	 */
	public int size(){
		return this.size;
	}
	
	/**
	 * 获取当前ArrayList的迭代器
	 * @return 当前ArrayList的迭代器
	 */
	public Iterator iterator(){
		return new Iterator(){
			private int count = 0;
			
			@Override
			public boolean hasNext() {
				return size > count;
			}

			@Override
			public Object next() {
				if(count == size){
					throw new RuntimeErrorException(null, "没有更多的元素！");
				}
				return get(count++);
			}

			@Override
			public void remove() {
				ArrayList.this.remove(count - 1);
			}
			
		};
	}
	
	/**
	 * 用来检查get和remove操作中输入的下标是否越界
	 * @param index 输入的下标
	 */
	private void checkIndex(int index){
		if(index >= size || index < 0){
			//size代表即将存入的元素的下标，所以index下标上还没有存储内容，无法进行get和remove操作
			throw new ArrayIndexOutOfBoundsException("Index:" + index);
		}
	}
	
	/**
	 * 检查ArrayList是否将要存满。
	 * 在ArrayList将要存满时对其进行扩容，每次扩容将使其容量增加20。
	 */
	private  void checkSize(){
		if(size < elementData.length) return;
		elementData = Arrays.copyOf(elementData, elementData.length + 20/*(int)(elementData.length * 1.2)*/);
	}
	
}
