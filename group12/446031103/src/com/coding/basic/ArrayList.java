package com.coding.basic;

import java.util.Arrays;

/**
 * 
 * arrayList集合-数组
 * 
 * @ClassName ArrayList
 * @author msh
 * @date 2017年2月21日 下午3:49:24
 */
public class ArrayList implements List {
	// 记录ArrayList集合大小
	private int size = 0;
	// 初始化存储数组
	private Object[] elementData = new Object[100];
	/**
	 * 
	 * 向最后插入元素 
	 *
	 * @Method add 添加
	 * @param o 元素
	 * @see com.coding.basic.List#add(java.lang.Object)
	 */
	public void add(Object o){
		// 数组不够时增长
		growOrNot(size + 1);
		elementData[size] = o;
		++size;
	}
	/**
	 * 
	 * 向指定位置插入元素 
	 *
	 * @Method add 添加
	 * @param index 下标
	 * @param o 元素
	 * @see com.coding.basic.List#add(int, java.lang.Object)
	 */
	public void add(int index, Object o){
		validate(index);
		growOrNot(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		++size;
	}
	/**
	 * 
	 * 取得元素 
	 *
	 * @Method get 取得
	 * @param index 下标
	 * @return
	 * @see com.coding.basic.List#get(int)
	 */
	public Object get(int index){
		validate(index);
		return elementData[index];
	}
	/**
	 * 
	 * 删除元素 
	 *
	 * @Method remove 删除
	 * @param index 下标
	 * @return 删除的元素
	 * @see com.coding.basic.List#remove(int)
	 */
	public Object remove(int index){
		Object oldValue = elementData[index];
		validate(index);
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		elementData[size] = null;
		--size;
		return oldValue;
	}
	/**
	 * 
	 * 取得集合大小 
	 *
	 * @Method size 集合大小
	 * @return 集合大小
	 * @see com.coding.basic.List#size()
	 */
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	/**
	 * 
	 * 判断是否需要增长数组 
	 *
	 * @MethodName growOrNot
	 * @author msh
	 * @date 2017年2月21日 下午3:53:29
	 * @param minCapacity
	 */
	private void growOrNot(int minCapacity) {
		// 当增加长度大于数组长度时，增长
		if (minCapacity > elementData.length) {
			elementData = Arrays.copyOf(elementData, elementData.length * 2);
		}
	}
	/**
	 * 
	 * 验证 
	 *
	 * @MethodName validate 下标
	 * @author msh
	 * @date 2017年2月21日 下午3:54:21
	 * @param index
	 */
	private void validate(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}
}
