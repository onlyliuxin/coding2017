package com.coding.basic.array;

import java.util.Arrays;

import com.coding.basic.Iterator;
import com.coding.basic.List;

/**
 * 自己实现arrayList
 * @author mazan
 *
 */
public class ArrayList implements List {
	/**
	 * list的容量
	 */
	private int size = 0;
	
	/**
	 * list的元素，初始容量100
	 * 每次执行前检查size
	 */
	private Object[] elementData = new Object[1];
	
	
	/**
	 * 新增
	 */
	public void add(Object o) {
//		//add之前的容量判断,数组是否已满
//		int currentSize = this.elementData.length;
//		if (this.size >= currentSize) {
//			//扩容
//			this.elementData = Arrays.copyOf(this.elementData, currentSize * 3/2 + 1);
//		}
//		//先赋值index=size(因为从0开始),size再加1
//		this.elementData[this.size++] = o;
		
		//相当于在最后面的位置加入新元素
		this.add(this.size, o);
		
	}
	
	/**
	 * 指定位置新增
	 */
	public void add(int index, Object o) {
		
		judgeIndexAdd(index);
		
		//add之前的容量判断,数组是否已满
		int currentSize = this.elementData.length;
		if (this.size >= currentSize) {
			//扩容
			this.elementData = Arrays.copyOf(this.elementData, currentSize * 3/2 + 1);
		}
		
		
		//index之后的元素后移
		//["a","b"],add(1,"c") --> ["a", "c", "b"]
		//i=2,i>1,i-- --> i=[2],
		
		//["a","b"],add(2,"c") --> ["a", "b", "c"] -->add(c)
		//i=2,i>2,i-- --> i=[]
		for(int i = this.size; i > index; i--) {
			this.elementData[i] = this.elementData[i - 1];
		}
		
		//index插入
		this.elementData[index] = o;
		//size+1
		this.size++;
		//index之前的内容不变
		
	}

	/**
	 * 获取指定位置的元素
	 */
	public Object get(int index) {
		
		judgeIndexRemove(index);
		return this.elementData[index];
	}

	/**
	 * 移除指定位置元素
	 */
	public Object remove(int index) {
		
		judgeIndexRemove(index);
		
		//index之后的元素-1
		
		//index之后的元素迁移
		//["a", "b", "c"],remove(0) --> ["b", "c"] ==>> i=0,i<2,i++ --> i=[0,1]
		//["a", "b", "c"],remove(1) --> ["a", "c"] ==>> i=1,i<2,i++ --> i=[2]
		//["a", "b", "c"],remove(2) --> ["a", "b"] ==>> i=2,i<2,i++ --> i=[]
		
		for(int i = index; i < this.size - 1 ; i++) {
			this.elementData[i] = this.elementData[i + 1];
		}
		
		//先把最后一个元素置为null,再size-1
		this.elementData[this.size--] = null;
		
		
		return null;
	}

	/**
	 * 获取列表size
	 */
	public int size() {
		return this.size;
	}

	/**
	 * 迭代
	 * @return
	 */
	public Iterator iterator() {
		
		final int arrSize = this.size;
		final Object[] arr = this.elementData;
		
		Iterator iter;
		iter = new Iterator() {
			//当前索引
			int iterIndex = 0;
			
			@Override
			public Object next() {
				return arr[iterIndex++];
			}
			
			@Override
			public boolean hasNext() {
				return arrSize > iterIndex ;
			}
		};
		
		return iter;
	}

	
	//----------------------------------//
	/**
	 * 判断index是否越界
	 * @param index
	 */
	private void judgeIndexAdd(int index) {
		
		if (index < 0) {
			throw new IndexOutOfBoundsException("index can not less than 0 ");
		}
		
		if (index > this.size) {
			throw new IndexOutOfBoundsException("index can not more than the size");
		}
	}
	
	/**
	 * 判断index是否越界
	 * @param index
	 */
	private void judgeIndexRemove(int index) {
		
		if (index < 0) {
			throw new IndexOutOfBoundsException("index can not less than 0 ");
		}
		//只能remove比当前容量小的索引位置
		if (index >= this.size) {
			throw new IndexOutOfBoundsException("index can not more than the size");
		}
	}
}
