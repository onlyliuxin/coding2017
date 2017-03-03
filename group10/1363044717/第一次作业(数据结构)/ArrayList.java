package com.coding.basic;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
	
	private int size = 0;
	
	private Object[] elementData;

	//默认容量
	private static final int DEFAULT_CAPACITY = 10;

	public ArrayList(int capacity){
		if(capacity >= 0){
			elementData = new Object[capacity];
		}else {
			throw new IllegalArgumentException("Illegal Capacity: " +
					capacity);
		}

	}
	public ArrayList(){
		this(DEFAULT_CAPACITY);
	}

	/**
	 * 保证集合容量
	 * @param minCapacity
	 */
	private void ensureCapacity(int minCapacity){
		int oldCapacity = elementData.length;
		if(minCapacity > oldCapacity){
			//扩容
			int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
			if(minCapacity - newCapacity > 0){
				newCapacity = minCapacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
	private void checkIndexRange(int index)
	{
		if(index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index out of bounds, index : " + index);
		}
	}
	public void add(E o){
		ensureCapacity(size+1);
		elementData[size++] = o;
	}
	public void add(int index, E o){
		checkIndexRange(index);//检查下标
		ensureCapacity(size+1);//保证数组容量
		System.arraycopy(elementData,index,elementData,index + 1,size-index);//数组复制,把index后的元素全部向后移一位
		elementData[index] = o;//插入元素值
		size++;//元素size加一
	}

	@Override
	public E get(int index) {
		checkIndexRange(index);//检查下标
		return (E)elementData[index];
	}

	@Override
	public E remove(int index) {
		E e = this.get(index);
		int numMoved = size - index - 1;
		if(numMoved > 0)
		{
			System.arraycopy(elementData, index+1, elementData, index, numMoved);//数组复制,把index后的元素全部向前移一位
		}
		elementData[--size] = null;//最后一位赋值为null，size-1
		return e;
	}


	public int size(){
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator iterator(){
		return new ArrayListIterator(this);
	}

	private class ArrayListIterator<E> implements Iterator{

		private Object [] array;
		private int endIndex = 0;
		private int index = 0;

		public ArrayListIterator(ArrayList list){
			this.array=list.elementData;
			this.endIndex = list.size();
		}
		@Override
		public boolean hasNext() {
			return this.index < this.endIndex;
		}

		@Override
		public E next() {
			if(!this.hasNext()) {
				throw new NoSuchElementException();//没有元素了
			} else {
				return (E)Array.get(this.array, this.index++);
			}
		}
	}
}
