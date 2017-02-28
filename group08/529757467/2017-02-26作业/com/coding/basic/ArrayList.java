package com.coding.basic;

public class ArrayList implements List {
	// size不仅是ArrayList中实际存值得体现，直接add时，也是在elementData[size]处进行
	private int size = 0;
	// 数组是连续且有序的
	private Object[] elementData;

	private void resize(int newSize) {
		Object[] newArray = new Object[newSize];
		System.arraycopy(elementData, 0, newArray, 0, size);
		elementData = newArray;
	}

	public ArrayList() {
		elementData = new Object[10];
	}

	// 添加指定元素至列表尾
	public void add(Object o) {
		if (size == elementData.length) {
			resize(size * 2);
		}
		elementData[size++] = o;
	}

	// 将指定元素插入列表中的指定位置。移动当前位置的元素（如果有的话）和右边的后续元素（向索引添加一个元素）
	public void add(int index, Object o) {
		rangeCheck(index);
		if (size == elementData.length / 4) {
			resize(elementData.length / 2);
		}
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	
	/*
	 * 获取指定位置元素
	 * (non-Javadoc)
	 * @see com.coding.basic.List#get(int)
	 */
	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}
	
	/*
	 * 删除指定位置元素
	 * (non-Javadoc)
	 * @see com.coding.basic.List#remove(int)
	 */
	public Object remove(int index) {
		rangeCheck(index);
		Object oldValue = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		size--;
		return oldValue;
	}

	public int size() {
		return this.size;
	}

	public Iterator iterator() {
		return new ArrayListIterator();
	}

	// 检测索引值，当前有size个元素，占了elementData[0]至elementData[size-1],add时，只能在已有[0,size-1]处插入，或者在列表尾size处add
	// 所以index在[0,size之间]
	private void rangeCheck(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}

	class ArrayListIterator implements Iterator {
		int i = 0;

		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public Object next() {
			return elementData[i++];
		}

	}

}
