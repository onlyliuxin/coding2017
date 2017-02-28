package com.guodong.datastructure;

import java.util.Arrays;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];
	
	/**
	 * 按下标顺序新增元素
	 *
	 * @Method add
	 * @param o
	 * @see com.guodong.datastructure.List#add(java.lang.Object)
	 */
	public void add(Object o) {
		ensureCapacityInternal(size + 1);
		elementData[size] = o;
		size++;
	}

	/**
	 * 在指定下标位置插入元素
	 *
	 * @Method add
	 * @param index
	 * @param o
	 * @see com.guodong.datastructure.List#add(int, java.lang.Object)
	 */
	public void add(int index, Object o) {
		checkRangeForAdd(index);
		
		ensureCapacityInternal(size + 1);
		
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		
		elementData[index] = o;
		size++;
	}

	/**
	 * 根据下标获取列表数据 
	 *
	 * @Method get
	 * @param index
	 * @return
	 * @see com.guodong.datastructure.List#get(int)
	 */
	public Object get(int index) {
		checkRangeForGetOrRemove(index);
		
		return elementData[index];
	}

	/**
	 * 根据下标移除元素，并返回移除的元素值
	 *
	 * @Method remove
	 * @param index
	 * @return
	 * @see com.guodong.datastructure.List#remove(int)
	 */
	public Object remove(int index) {
		checkRangeForGetOrRemove(index);
		
		Object oldValue = elementData[index];
		
		System.arraycopy(elementData, index + 1, elementData, index, size - index -1);
		
		size--;
		elementData[size] = null;
		
		return oldValue;
	}

	/**
	 * 获得列表长度
	 *
	 * @Method size
	 * @return
	 * @see com.guodong.datastructure.List#size()
	 */
	public int size() {
		return size;
	}

	/**
	 * 获取ArrayList的迭代器
	 *
	 * @MethodName iterator
	 * @author zhaogd
	 * @date 2017年2月21日 下午8:19:28
	 * @return
	 */
	public Iterator iterator() {
		return new ArrayListIterator();
	}

	/**
	 * 确保数组容量足够，如果不够则扩充数组
	 *
	 * @MethodName ensureCapacityInternal
	 * @author zhaogd
	 * @date 2017年2月21日 下午5:06:46
	 * @param minCapacity
	 */
	private void ensureCapacityInternal(int minCapacity) {
		if(minCapacity > elementData.length){
			grow(minCapacity);
		}
	}

	/**
	 * 数组扩充，每次扩充原数组一半的长度，然后把原数组拷贝到新数组
	 *
	 * @MethodName grow
	 * @author zhaogd
	 * @date 2017年2月21日 下午5:20:55
	 * @param minCapacity
	 */
	private void grow(int minCapacity) {
		minCapacity = elementData.length + elementData.length / 2;
		elementData = Arrays.copyOf(elementData, minCapacity);
	}
	
	/**
	 * 检查Add方法的下标范围是否合法
	 *
	 * @MethodName checkRangeForAdd
	 * @author zhaogd
	 * @date 2017年2月21日 下午7:32:55
	 * @param index
	 */
	private void checkRangeForAdd(int index) {
		if(index > size || index < 0){
			throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
		}
	}
	
	/**
	 * 检查Get或者Remove方法的下标范围是否合法
	 *
	 * @MethodName checkRangeForGetOrRemove
	 * @author zhaogd
	 * @date 2017年2月21日 下午7:33:21
	 * @param index
	 */
	private void checkRangeForGetOrRemove(int index) {
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
		}
	}
	
	
	private class ArrayListIterator implements Iterator{

		private int lastIndex = 0;
		
		@Override
		public boolean hasNext() {
			return lastIndex < size;
		}

		@Override
		public Object next() {
			return elementData[lastIndex++];
		}
	}
}
