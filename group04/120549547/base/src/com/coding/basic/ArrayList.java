package com.coding.basic;
import java.util.Arrays;

public class ArrayList implements List {
	/*默认数组容量*/
	public static final int DEFAULT_CAPCATITY = 10;
	
	/*数组元素个数*/
	private int size;

	private Object[] elementData;
	
	public ArrayList(){
		this(DEFAULT_CAPCATITY);
	}
	
	public ArrayList(int capacity){
		if (capacity<0 || capacity>Integer.MAX_VALUE) 
			throw new IllegalArgumentException("非法容量： " + capacity);
		elementData = new Object[capacity];
	}
	
	
	public void add(Object o){
		/*判断是否需要扩容*/
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		checkIndex(index);
		/*判断是否需要扩容*/
		ensureCapacity(size + 1);
		/*将index->size的元素依次向右移一个位置*/
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		/*插入o的值*/
		elementData[index] = o;
		size++;
	}


	public Object get(int index){
		
		checkIndex(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		
		checkIndex(index);
		Object value = elementData[index];
		/*将index+1 -> size的元素依次向左移一个位置*/
		System.arraycopy(elementData,index+1, elementData, index, size-index-1);
		elementData[--size]=null;   //释放最后一个元素,同时size减一；
		return value;
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return null;
	}
	/* 确认容量是否足够，也可以直接调用手动扩容*/
	public void ensureCapacity(int minCapacity){
		int oldCapacity = elementData.length;
		/*容量不足需要扩容*/
		if(oldCapacity < minCapacity){
			int newCapacity = oldCapacity * 2; //扩大2倍
			if (newCapacity < minCapacity){
				newCapacity = minCapacity;  //扩容后仍不足,取最大值
			}
			System.out.println("数据扩容至： " + newCapacity);
			elementData = Arrays.copyOf(elementData, newCapacity);	
		}
		
	}

	private void checkIndex(int index){
	if (index<0 || index>= size)
		throw new IndexOutOfBoundsException ("index非法: " + index);
	}
	@Override
	public String toString(){
		
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<size; i++){
			sb.append("["+ i +"]"+ elementData[i] + "->  ");
		}
		return sb.toString();
	}
}
