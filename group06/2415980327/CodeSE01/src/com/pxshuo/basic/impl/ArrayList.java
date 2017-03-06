package com.pxshuo.basic.impl;

import com.pxshuo.basic.Iterator;
import com.pxshuo.basic.List;

/**
 * 实现一个ArrayList 
 * @author Pxshuo
 *
 */

public class ArrayList implements List{

	private int size = -1;//数组的长度的下标
	private Object[] elements = new Object[10];//数组内容
	private int addSize = 10;//每次增加的长度
	
	@Override
	public void add(Object o) {
		elements = grow();
		size++;
		elements[size] = o;//size与index同一个概念
	}

	@Override
	public void add(int index, Object o) {
		if (index > size + 1) {
			return;
		}
		elements = grow();
		int moveNum = size - index + 1;//本次操作需要移动的元素的个数；
		size++;
		if (index >= elements.length - 1) {//按照位置来看
			elements = grow(elements, index - (elements.length - 1));
			size = index;//size与index同一个概念
		}
		
		/**
		 * 整体向后移一位
		 */
		if(moveNum > 0){
			System.arraycopy(elements, index, elements, index + 1, moveNum);	
		}
//		for(int i = size - 1; i >= index; i--)
//		{
//			elements[i] = elements[i-1];
//		}
		
		elements[index] = o;
	}

	@Override
	public Object get(int index) {
		return elements[index];
	}

	@Override
	public Object remove(int index) {
		if (index > size) {
			return null;
		}
		Object removeEle = elements[index];
		int moveNum = size - index;//本次操作需要移动的元素的个数；
		if (moveNum > 0) {
			System.arraycopy(elements, index + 1, elements, index, size - index + 1);
		}
		elements[size] = null;
		size--;
		return removeEle;
	}

	@Override
	public int size() {
		return size + 1;
	}
	
	/**
	 * 设置迭代器
	 * @return
	 */
	public Iterator iterator() {
		return new ArrayListIterator(this);
	}
	
	private class ArrayListIterator implements Iterator{

		ArrayList arrayList = null;
		int position = -1;
		
		public ArrayListIterator(ArrayList arrayList) {
			this.arrayList = arrayList;
		}
		
		@Override
		public boolean hasNext() {
			position ++;
			if (position >= arrayList.size()) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			return arrayList.elements[position];
		}
		
	}

	/**
	 * 自动控制是否增加数组长度
	 * @return 如果增加一条数据会造成数组溢出，则增加数组的长度，否则不进行改变。
	 */
	private Object[] grow(){
		if (size() >= elements.length) {
			return grow(elements, addSize);
		}
		else {
			return elements;
		}
		
	}
	
	/**
	 * 动态增加数组长度
	 * @param src
	 * @param addSize
	 * @return
	 */
	private Object[] grow(Object[] src,int addSize){
		Object[] target = new Object[src.length + addSize];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
		
		//return Arrays.copyOf(src, src.length + addSize);同理
	}
	
}
