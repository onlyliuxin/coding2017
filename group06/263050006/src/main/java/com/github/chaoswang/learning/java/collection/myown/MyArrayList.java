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
		//�ﵽ�������ޣ���initialSize����50%
		if(++size == elements.length){
			elements = Arrays.copyOf(elements, size + (int)Math.round(initialSize * 0.5));
		}
		elements[size - 1] = element;
	}
	
	//��
	public void add(int index, E element){
		//index=sizeʱ���൱�ڵ���add����
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		for (int i=size; i > index; i--){
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
	}
	
	//��
	public E remove(int index){
		 E removed = (E)elements[index];
		 for(int i=index; i<size -1; i++){
			 elements[i] = elements[i+1];
		 }
		 elements[size -1] = null;
		 size--;
		 return removed;
	}
	
	//��
	public E get(int index){
		return (E)elements[index];
	}
	
	public int size(){
		return size;
	}
	
	//ʹ�õ������ĺô����������ڲ�ʵ�֣�ֻ���Ⱪ¶hasnext��next������Ԫ��
	public MyIterator iterator(){
		return new ArrayListIterator();
	}
	
	//����ط�������static�ڲ��࣬��ΪҪ��ʵ����(Ҫʹ��OuterClass�Ķ�����
	private class ArrayListIterator implements MyIterator{
		int position = 0;//������ָ��
		
		public boolean hasNext() {
			return position == size - 1 ? false : true;
		}

		public Object next() {
			return elements[position++];
		}

		/**
		 * �ӵ�����ָ��� collection ���Ƴ����������ص����һ��Ԫ��
		 * ÿ�ε��� next ֻ�ܵ���һ�δ˷�����
		 */
		public void remove() {
			MyArrayList.this.remove(position);
		}
	}
}
