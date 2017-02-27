package com.coding.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {
	private int size = 0; //表内现有元素个数
	private Object[] elementData = new Object[2];  //数据结构--数组
	
	public void add(Object o){  //在后面追加
		if(size == elementData.length) elementData = grow(elementData, 2* elementData.length);  //先扩容
		elementData[size++] = o;
	}

	public void add(int index, Object o){  //在指定位置插入
		if(size == elementData.length) elementData = grow(elementData, 2* elementData.length);  //先扩容
		if(index > size || index < 0) throw new ArrayIndexOutOfBoundsException();   //保证最后一个元素后面也可以插
		System.arraycopy(elementData,index, elementData,index+1,size-index);
		elementData[index] = o;
		size ++;
	}
	
	public Object get(int index){  //返回指定索引的元素
		if(index > size - 1 || index < 0) throw new ArrayIndexOutOfBoundsException();
		return elementData[index];
	}
	
	public Object remove(int index){  //删除指定索引处的元素
		if(index > size - 1 || index < 0) throw new ArrayIndexOutOfBoundsException();
		Object o = elementData[index];
		System.arraycopy(elementData,index+1, elementData,index,size-index-1);
		elementData[size-1] = null;
		size --;
		return o;
	}
	
	public int size(){  //number of elements
		return size;
	}
	
	public Iterator iterator(){ //迭代器
		return new ListIterator();
	}

	private class ListIterator implements Iterator{ //实例内部类
		private int i = 0;

		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public Object next() {
			if(size() == 0) throw new NoSuchElementException("Stack Underflow");
			return elementData[i++];
		}
	}

	//数组扩容
	private Object[] grow(Object[] src, int newLength){
		assert src.length < newLength;  //断言
		return Arrays.copyOf(src,newLength);
	}
	
}
