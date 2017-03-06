/**
 * 
 */
package com.coding.basic;

/**
 * @author patchouli
 *
 */
public class ArrayList implements List {
	
	/**
	 * 数组列表初始默认容量100.
	 */
	private static final int DEFAULT_CAPACITY=100;	
	
	/**
	 * 数组列表中元素的数量。
	 */
	private int size = 0;
	
	/**
	 * 数组列表当前容量
	 */
	private int capacity;
	
	/**
	 * 存放元素的数组。
	 */
	private Object[] elementData;
	
	/**
	 * 默认构造函数，构造一个初始容量为100的数组列表
	 */
	public ArrayList() {
		capacity=DEFAULT_CAPACITY;
		elementData=new Object[DEFAULT_CAPACITY];
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see nusub.coding2017.basic.List#add(java.lang.Object)
	 */
	@Override
	public void add(Object o) {
		expand();
		elementData[size]=o;
		size=size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nusub.coding2017.basic.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, Object o) throws ListIndexException {
		if (index==size) {
			add(o);
			return;
		}
		if (0<=index&&index<size) {
			expand();
			for (int i = size; i>index; i--) {
				elementData[i]=elementData[i-1];
			}
			elementData[index]=o;
			return;
		}
		throw new ListIndexException("index不在[0,size]之间");
	}

	/**
	 * index在[0,size)之间返回通过数组下标访问的方式获取位置index处的元素，index超出这个范围抛出ListIndexException。
	 * @param index 元素的位置
	 * @return 获取的对象
	 * @throws ListIndexException 
	 */
	@Override
	public Object get(int index) throws ListIndexException {
		if (0<=index&&index<size) {
			return elementData[index];
		}	
		throw new ListIndexException("index不在[0,size)之间");
	}

	/**
	 * 通过访问数组下标删除[0,size)之间的元素，被删除的元素之后的元素向前移动。如果index超出这个范围，抛出ListIndexException。
	 * @param index 要删除大额元素的位置
	 * @return 被删除的对象
	 * @throws ListIndexException 
	 */
	@Override
	public Object remove(int index) throws ListIndexException {
		if (0<=index&&index<size) {
			throw new ListIndexException("index不在[0,size)之间");
		}
		Object object=elementData[index];
		for(int i=index;i<size;i++){
			elementData[i]=elementData[i+1];
		}
		shrink();
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nusub.coding2017.basic.List#size()
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * 空间不足时扩容，容量变为原来的2倍，在添加元素前调用。
	 */
	private void expand(){
		if (size<DEFAULT_CAPACITY) {
			return;
		}
		capacity=capacity*2;
		Object[] oldElementData=elementData;
		elementData=new Object[capacity];
		for (int i = 0; i < size; i++) {
			elementData[i]=oldElementData[i];
		}
	}
	
	/**
	 * 装填因子小于25%时收缩，容量变为原来的50%，但不小于初始容量。在移除元素后使用。
	 */
	private void shrink(){
		if (capacity<DEFAULT_CAPACITY*2) {
			return;
		}
		if (size*4<capacity) {
			capacity=capacity/2;
			Object[] oldElement=elementData;
			elementData=new Object[capacity];
			for (int i = 0; i < size; i++) {
				elementData[i]=oldElement[i];
			}
		}
	}

}
