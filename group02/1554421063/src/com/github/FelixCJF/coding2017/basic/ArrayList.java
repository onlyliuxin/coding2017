package com.github.FelixCJF.coding2017.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		//容量增加
		ensureCapacity(size + 1);
		//添加
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		//容量增加
		ensureCapacity(size + 1);
		//临时变量
		Object[] elementData3 = new Object[size + 1];
		//将index前数据复制
		for (int i = 0; i < index + 1 ; i++) {
			elementData3[i] = elementData[i];
		}
		//插入的数据
		elementData3 [index + 1] = o;
		//插入数据之后的后半段复制
		for (int i = index + 2 ; i < elementData3.length; i++) {
			elementData3[i] = elementData[i-1];
		}
		elementData = elementData3;
		size++;
	}
	
	public Object get(int index){
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		Object oldValue = elementData[index];
		Object[] elementData4 = new Object[size - 1];
		for (int i = 0; i < index; i++) {
			elementData4[i] = elementData[i];
		}
		for (int i = index; i < elementData4.length; i++) {
			elementData4[i] = elementData[i + 1];
		}
		elementData = elementData4;
		size--;
		return oldValue;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	//内部类，实现Iterator
	private class ArrayListIterator implements Iterator{
		
		private int currentIndex = 0; //当前索引

		public boolean hasNext() {
			if (currentIndex >= size) {
				return false;
			}
			return true;
		}

		public Object next() {
			Object object  = elementData[currentIndex];
			currentIndex ++ ;
			return object;
		}
	}
	public void ensureCapacity(int minCapacity) {
		  int oldCapacity = elementData.length;
		  if (minCapacity > oldCapacity) {
		    int newCapacity = (oldCapacity * 3) / 2 + 1;
		    if (newCapacity < minCapacity)
		      newCapacity = minCapacity;
		    elementData = Arrays.copyOf(elementData, newCapacity);
		  }
	}
	
}
