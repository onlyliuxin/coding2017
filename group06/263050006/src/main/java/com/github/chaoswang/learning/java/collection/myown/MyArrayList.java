package com.github.chaoswang.learning.java.collection.myown;

import java.util.Arrays;

public class MyArrayList<E> {
	private int size = 0;
	private int initialSize;
	private Object[] elements = null;
	
	public MyArrayList(int initialSize){
		this.initialSize = initialSize;
		elements = new Object[initialSize];
	}
	
	public void add(E element){
		//达到数组上限，按initialSize扩容50%
		if(++size == elements.length){
			elements = Arrays.copyOf(elements, size + (int)Math.round(initialSize * 0.5));
		}
		elements[size - 1] = element;
	}
	
	//慢
	public void add(int index, E element){
		//index=size时，相当于调用add方法
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		for (int i=size; i > index; i--){
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
	}
	
	//慢
	public E remove(int index){
		 E removed = (E)elements[index];
		 for(int i=index; i<size -1; i++){
			 elements[i] = elements[i+1];
		 }
		 elements[size -1] = null;
		 size--;
		 return removed;
	}
	
	//快
	public E get(int index){
		return (E)elements[index];
	}
	
	public int size(){
		return size;
	}
	
	//使用迭代器的好处在于隐藏内部实现，只对外暴露hasnext和next来遍历元素
	public MyIterator iterator(){
		return new ArrayListIterator();
	}
	
	//这个地方不能用static内部类，因为要和实例绑定(要使用OuterClass的东西）
	private class ArrayListIterator implements MyIterator{
		int position = 0;//迭代器指针
		
		public boolean hasNext() {
			return position == size - 1 ? false : true;
		}

		public Object next() {
			return elements[position++];
		}

		/**
		 * 从迭代器指向的 collection 中移除迭代器返回的最后一个元素
		 * 每次调用 next 只能调用一次此方法。
		 */
		public void remove() {
			MyArrayList.this.remove(position);
		}
	}
}
