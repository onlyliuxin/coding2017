package com.xiaol.study;

/**
 * @Description TODO
 * @date 创建时间：2017年3月4日 下午11:03:26
 */
public class ArrayList implements List {

	// 记录有多少元素
	private int size = 0;

	// 存放元素的数组
	private Object[] elementData = new Object[10];

	// 添加元素
	public void add(Object o) {
		checkSizeAndGrow();
		elementData[size++] = o;
	}

	// 检查数组容量大小，不足就扩容
	private void checkSizeAndGrow() {
		if (size >= elementData.length) {
			Object[] oldData = elementData;
			elementData = new Object[size * 2];
			System.arraycopy(oldData, 0, elementData, 0, size);
		}
	}

	// 在指定位置添加元素
	public void add(int index, Object o) {
		checkIndex(index);
		checkSizeAndGrow();
		// 把index位置的元素往后移一位
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	// 检查插入元素的位置是否非法
	private void checkIndex(int index) {
		if (index < 0 || index > size) {
			throw new RuntimeException("参数非法");
		}
	}

	// 获取指定位置元素
	public Object get(int index) {
		checkIndex(index);
		return elementData[index];
	}

	// 移除指定位置元素
	public Object remove(int index) {
		checkIndex(index);
		Object returnVal = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		size--;
		return returnVal;
	}

	// 获取ArrayList元素数量
	public int size() {
		return size;
	}

	// 获取迭代器
	public Iterator iterator() {
		return new ArrayListIterator();
	}

	// 尽量实现迭代器
	private class ArrayListIterator implements Iterator {

		ArrayList list = null;

		@Override
		public boolean hasNext() {

			return false;
		}

		@Override
		public Object next() {
			return null;
		}

		public Object remove() {
			return null;
		}

	}

}
