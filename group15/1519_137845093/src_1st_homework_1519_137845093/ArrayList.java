package com.coding.basic;

import java.util.NoSuchElementException;

public class ArrayList implements List {
  //元素个数
	private int size = 0;
  //初始长度设为10
	private Object[] elementData = new Object[10];

	public void add(Object o) {
		int len = size + 1;
		// 判断list的长度是否大于数组长度
		if (len > elementData.length) {
			// 创建新容器
			Object[] newElemData = new Object[elementData.length + 1];
			// 复制旧容器所有元素到新容器
			System.arraycopy(elementData, 0, newElemData, 0, elementData.length);
			elementData = newElemData;
		}
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		// 检查下标是否越界
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index:" + index + "越界；" );
		}
		// 插入元素到数组的末尾直接调用add方法
		if (index == size) {
			add(o);
		} else {
			// 创建新容器
			Object[] newElemData = new Object[elementData.length + 1];
			// 复制index以前的所有元素到新容器
			System.arraycopy(elementData, 0, newElemData, 0, index);
			newElemData[index] = o;
			// 复制index 及以后的元素到新容器
			System.arraycopy(elementData, index, newElemData, index + 1, size - index);

			elementData = newElemData;
			size++;
		}
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "越界；");
		}
		return elementData[index];
	}

	public Object remove(int index) {
		//下标大于数组长度的，抛出异常
		if (index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "越界；");
		}		
		//index不是最后一个元素的索引值才需要删除操作
		if(index != (size-1)){
			// 创建新容器
			Object[] newElemData = new Object[elementData.length];
			// 复制index以前的所有元素到新容器
			System.arraycopy(elementData, 0, newElemData, 0, index);
			// 复制index 以后的元素到新容器
			System.arraycopy(elementData, index+1, newElemData, index, size - index -1);			
		}
		Object removeElement = elementData[index];
		//减小数组的长度
		size--;
		return removeElement;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new MyItr(this);
	}

	private class MyItr implements Iterator {
		private int l = -1;
		private ArrayList array = null;

		private MyItr(ArrayList array) {
			this.array = array;
		}

		@Override
		public boolean hasNext() {
			return (l + 1) < array.size;
		}

		@Override
		public Object next() {
			l++;
			if (l >= array.size) {
				l = array.size - 1 ;
				throw new IndexOutOfBoundsException();
			}

			return array.get(l);
		}

		@Override
		public Object remove() {
			if (l < 0) {
				throw new NoSuchElementException();
			}
			Object val = array.remove(l);
			l--;
			return val;
		}

	}
}