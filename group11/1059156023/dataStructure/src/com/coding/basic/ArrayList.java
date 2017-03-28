package com.coding.basic;

import java.util.List;

public class ArrayList implements List{
	private int size;
	
	//设置一个默认容量，当调用默认构造函数实例化数组后，需要扩容时可用
	private static final int DEFAULT_CAPACITY=10;
	
	//Integer.MAX_VALUE:2147483647，MAX_ARRAY_SIZE：2147483639
	private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;   
	
	private Object[] elementData;
	
	//定义一个默认为空的数组，供默认构造函数使用
	private static final Object[] EMPTY_ELEMENTDATA={};
	
	//定义默认构造函数,实例化为空数组
	public ArrayList(){
		this.elementData=EMPTY_ELEMENTDATA;
	}
	
	//定义一个有参的构造函数
	public ArrayList(int initialCapacity){
		if(initialCapacity<0)
			throw new IllegalArgumentException("Illegal Capacity:"+initialCapacity);
		this.elementData = new Object[initialCapacity];
	}
	
	//定义add(Object o)方法,默认在数组末尾添加
	public boolean add(Object o){
		//要添加一个数，所以用ensureCapacityInternal()判断size+1个的数，数组是否放得下
		ensureCapacityInternal(size+1);  
		elementData[size++]=o;
		return true;
	}

	private void ensureCapacityInternal(int minCapacity) {
		if(elementData == EMPTY_ELEMENTDATA)
			minCapacity = DEFAULT_CAPACITY;
		
		//如果需要扩容，则调用grow()
		if(minCapacity-elementData.length>0)
			grow(minCapacity);
	}

	private void grow(int minCapacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity+(oldCapacity>>1);
		
		//原始长度是0时，即原来是空数组时
		if(newCapacity-minCapacity<0)        
			newCapacity = minCapacity;
		
		//如果新的容量超过了数组最大容量，就调用hugeCapacity()把能给的最大容量给它
		if(newCapacity-MAX_ARRAY_SIZE>0)
			newCapacity = hugeCapacity(minCapacity);
			
		
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity<0) {
			throw new OutOfMemoryError();   //抛出内存溢出异常
		}
		//如果minCapacity比MAX_ARRAY_SIZE大，则返回int类型所能表示的最大值，否则返回MAX_ARRAY_SIZE
		return (minCapacity>MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

}
