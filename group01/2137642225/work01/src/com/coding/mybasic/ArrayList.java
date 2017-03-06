package com.coding.mybasic;

public class ArrayList implements List{
	
	private static final int DEF_CAPACITY = 10;
	private int size;
	private Object[] elementData;

	public ArrayList(){
		elementData = new Object[DEF_CAPACITY];
	}
	
	public ArrayList(int initCapacity) {
		if(initCapacity <= 0){
			throw new RuntimeException("初始化长度必须大于0");
		}
		elementData = new Object[initCapacity];
	}

	@Override
	public void add(Object element) {
		checkArrayOutOfRange();
		elementData[size++] = element;
	}


	@Override
	public void add(int index, Object element) {
		// 末尾插入
		if(index == size){
			add(element);
			return;
		}
		// index 在 0到size 之间,index之后元素要后移
		checkIndex(index);
		checkArrayOutOfRange();
		moveBackwardElement(index);
		elementData[index] = element;
		size++;
	}


	@Override
	public Object get(int index) {
		checkIndex(index);
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		checkIndex(index);
		Object temp = elementData[index];
		moveForwardElement(index);
		elementData[size--] = null;
		return temp;
	}
	
	

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Iterator iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator{
		private int i = 0;
		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public Object next() {
			checkIndex(i);
			return elementData[i++];
		}
		
	}
	
	/**
	 * 数组增长
	 * @param newCapacity 新数组容量
	 */ 
	private void grow(int newCapacity) {
		Object[] dest = new Object[newCapacity];
		System.arraycopy(elementData, 0, dest , 0, elementData.length);
		elementData = dest;
	}
	
	/**
	 * 检查index index >=0 且  < size
	 * @param index 
	 * @throws Exception
	 */
	private void checkIndex(int index) {
		if(index < 0){
			throw new RuntimeException("index 必须大于0");
		}
		// 越界
		if(index >= size){
			throw new RuntimeException("index 必须小于size:" + size);
		}
	}
	
	/**
	 * 检查数组容量是否已满，已满则扩容
	 */
	private void checkArrayOutOfRange() {
		if(size >= elementData.length){
			// 扩容 默认新容量是原来容量的2倍
			grow(elementData.length * 2);
		}
	}
	
	/**
	 * 后移元素，从index开始
	 * @param index
	 */
	private void moveBackwardElement(int index) {
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
	}
	/**
	 * 前移元素,从index开始
	 * @param index
	 */
	private void moveForwardElement(int index) {
		for (int i = index; i < size; i++) {
			elementData[i] = elementData[i + 1];
		}
	}

}
