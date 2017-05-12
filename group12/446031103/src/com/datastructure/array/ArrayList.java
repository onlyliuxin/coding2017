package com.datastructure.array;

import java.util.Arrays;

import com.datastructure.basic.Iterator;
import com.datastructure.basic.List;



/**
 * 
 * arrayList集合-数组
 * 
 * @ClassName ArrayList
 * @author msh
 * @date 2017年2月21日 下午3:49:24
 */
public class ArrayList implements List {
	private int size = 0;
	private Object[] elementData = new Object[0];
	/**
	 * 
	 * 向最后插入元素 
	 *
	 * @Method add 添加
	 * @param o 元素
	 * @see com.datastructure.basic.List#add(java.lang.Object)
	 */
	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size] = o;
		size++;
	}
	/**
	 * 
	 * 向指定位置插入元素 
	 *
	 * @Method add 添加
	 * @param index 下标
	 * @param o 元素
	 * @see com.datastructure.basic.List#add(int, java.lang.Object)
	 */
	public void add(int index, Object o){
		validate(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	/**
	 * 
	 * 取得元素 
	 *
	 * @Method get 取得
	 * @param index 下标
	 * @return
	 * @see com.datastructure.basic.List#get(int)
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
	 * @see com.datastructure.basic.List#remove(int)
	 */
	public Object remove(int index){		
		validate(index);
		Object oldValue = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		elementData[size] = null;
		size--;
		return oldValue;
	}
	/**
	 * 
	 * 取得集合大小 
	 *
	 * @Method size 集合大小
	 * @return 集合大小
	 * @see com.datastructure.basic.List#size()
	 */
	public int size(){
		return this.size;
	}
	/**
	 * 迭代
	 * @return
	 */
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	/**
	 * 判断是否需要数组增长
	 * @param minCapacity
	 */
	private void ensureCapacity(int minCapacity) {
		if(minCapacity>elementData.length){
			int newCapacity = Math.max(minCapacity, elementData.length*2);
			Object[] newElementData = new Object[newCapacity];
			System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
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
	/**
	 * 
	 * @author Administrator
	 *
	 */
	private class ArrayListIterator implements Iterator{
		private int position;
        private ArrayList list;
		@Override
		public boolean hasNext() {
			return position < list.size();
		}

		@Override
		public Object next() {
			 if (hasNext()) {
	                return list.get(position++);
	          }
			return null;
		}
		
	}
}
