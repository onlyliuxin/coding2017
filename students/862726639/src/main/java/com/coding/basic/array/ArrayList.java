package com.coding.basic.array;

import com.coding.basic.Iterator;
import com.coding.basic.List;
//实现arraylist写呢
public class ArrayList implements List {
	
	private  int size = 0;
	
	private  Object[] elementData = new Object[10];
	
	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}
	private void ensureCapacity(int i) {
		System.out.println(i);
		if(i < size()){
			System.out.println(i);
			Object[] newElementdate = new Object[size()+10];
			System.arraycopy(elementData, 0, newElementdate, 0, size());
			elementData = newElementdate;
		}
		
	}
	public void add(int index, Object o){
		if(index>size()){
			throw new IndexOutOfBoundsException("数组越界");
		}
		Object[] newElementdate = new Object[size()+1];
		System.arraycopy(elementData, 0, newElementdate, 0, index + 1);
		newElementdate[index] = o;
		System.arraycopy(elementData, index, newElementdate, index + 1, size() - 1 -index);
		elementData = newElementdate;
	}
	
	public Object get(int index){
		if (size() < index) {//判断是否存在
			return null;
		}
		return elementData[index];
	}
	//返回删除对象
	public Object remove(int index){
		if (index > size()) {//不存在
			throw new IndexOutOfBoundsException("不存在");
		} else{
			Object[] newElementdate = new Object[size()- 1];
			System.arraycopy(elementData, 0, newElementdate, 0, index + 1);
			System.arraycopy(elementData, index + 1, newElementdate, index, size() - 1 -index);
			elementData = newElementdate;
			return elementData[index];
		} 
	}
	
	public int size(){
		if (elementData[0]==null) {
			return -1;
		}
		return elementData.length;
	}
	
	public Iterator iterator(){
		return new IteratorImpl();
	}
	class IteratorImpl implements Iterator{
		private int i = 0;//初始值
		@Override
		public boolean hasNext() {
			return i <= size();
		}

		@Override
		public Object next() {
			return elementData[i++];
		}

		@Override
		public Object remove() {
			for (Object object : elementData) {
				object = null;
			}
			
			return null;
		}
		
	}
}
