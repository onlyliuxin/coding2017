package com.sprint.basic.list;

import com.sprint.basic.exception.ConcurrentModificationException;
import com.sprint.basic.Iterator;
public class ArrayList implements List {
	
	private int size;
	private Object[] elementData;

	public ArrayList () {
		size = 0;
		elementData = new Object[100];
	}

	public boolean add(Object o) {
		add(size(), o);
		return true;
	}

	public boolean add(int index, Object o){
		if (size() == elementData.length)	
			ensureCapacity( size() * 2 + 1);
		if (index > size() || index < 0) { //index == size时相当于在尾后插入
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i-1]; 
		}
		elementData[index] = o; 
		size++;
		return true;
	}

	private void ensureCapacity(int newCapacity) {
		if (newCapacity < size())
			return;
		Object[] old = elementData;
		elementData = new Object[newCapacity];
		for (int i = 0; i < size(); i++) {
			elementData[i] = old[i];
		}
	}

	public Object get(int index){
		if (index >= size() || index < 0) { //获取时,index==size()越界
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}	
		return elementData[index];
	}

	private String outOfBoundsMsg(int index) {
		return "Index:" + index + ", Size:" + size; 
	}
	
	public Object remove(int index){
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
		Object old = elementData[index];
		for (int i = index; i < size(); i++) {
			elementData[i] = elementData[i+1];
		}
		size--;
		return old;
	}

	/*获取表内容量*/
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	public class ArrayListIterator implements Iterator {
		private final int ONLY_CAPACITY = size;
		private int index;
		public ArrayListIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			if (ONLY_CAPACITY != size)
				throw new ConcurrentModificationException("此对象没有进行修改同步");
			return index != size;	
		}

		@Override
		public Object next() {
			if (ONLY_CAPACITY != size)
				throw new ConcurrentModificationException("此对象没有进行修改同步");
			if (index >= ONLY_CAPACITY)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
			return elementData[index++];
		}
	}
}
