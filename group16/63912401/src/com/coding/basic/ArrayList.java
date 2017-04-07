package com.coding.basic;

import java.util.Arrays;

/** 
 * ArrayList
 * @author greenhills
 * @version 创建时间：2017年2月19日 下午10:54:02 
 * @param
 * 
 */
public class ArrayList implements List {
	/**
	 * 默认容量
	 */
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * 数据存放区
	 */
	private Object[] elementData;
	
	/**
	 * 真实的数据数量
	 */
	private int size = 0;
	
	/**
	 * 无参构造函数
	 */
	public ArrayList(){
		this.elementData=new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * 带初始大小的构造函数
	 * @param beginSize
	 */
	public ArrayList(int beginSize){
		if(beginSize<0) 
			this.elementData=new Object[DEFAULT_CAPACITY];
		else
			this.elementData=new Object[beginSize];
	}
	
	/**
	 * 在后面追加数据
	 */
	@Override
	public void add(Object o){
		autoGrow(size+1);
		this.elementData[size++] = o; //在尾部追加数据
	}
	
	/**
	 * 把数据加入指定索引处
	 */
	@Override
	public void add(int index, Object o){
		rangeCheck(index);
		autoGrow(size+1);
		//把index处的所有数据往后移
		//System.arraycopy(elementData, index, elementData, index+1, size-index);
		
		for(int i=size;i>index;i--){
			elementData[i] = elementData[i-1];
		}
		
		this.elementData[index] = o; //使数据连续加入
		size++;
	}
	
	/**
	 * 获取指定索引处的数据
	 */
	@Override
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	/**
	 * 获取末尾数据
	 */
	public Object getLast(){
		return elementData[this.size-1];
	}
	
	/**
	 * 移除索引处数据
	 */
	@Override
	public Object remove(int index){
		rangeCheck(index);
		
		Object removed = elementData[index];
		int num=size - index - 1;  //移动数量
		if(num>0) {
			System.arraycopy(elementData, index+1, elementData, index,num);
		}
		elementData[--size] = null; //清除最后一个数据位
		return removed;
	}
	
	/**
	 * 移除末尾数据
	 */
	public Object removeLast(){
		return remove(this.size-1);
	}
	
	/**
	 * 获取数据量
	 */
	@Override
	public int size(){
		return this.size;
	}
	
	/**
	 * 获取存储数据的容量大小
	 */
	@Override
	public int capacity() {
		return this.elementData.length;
	}
	
	/**
	 * 判断是否为空
	 */
	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	/**
	 * 空间容量自增长
	 * @param minCapacity 增长后最小容量
	 */
	private void autoGrow(int minCapacity){
		int oldCapacity = elementData.length;
		if (minCapacity >= oldCapacity) {
		    int newCapacity = oldCapacity<<1;  //空间翻倍
	    	if (newCapacity < minCapacity){
	    		newCapacity = minCapacity;
	    	}
	        elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	/**
	 * 判断是否为有效索引
	 * @param @param index
	 * @param @return
	 */
	private void rangeCheck(int index) {
        if (!isEffectiveIndex(index))
            throw new IndexOutOfBoundsException("Index: "+index+" Out Of Bounds, 有效数据索引范围:0~"+(this.size-1));
    }
	
	/**
	 * 判断是否为有效索引
	 * @param @param index
	 * @param @return
	 */
	private boolean isEffectiveIndex(int index){
		return index >-1 && index < this.size;
	}
	
	/**
	 * 返回遍历数据对象
	 * @param @return
	 * @author greenhills
	 * 2017年2月25日 下午9:55:31
	 */
	public Iterator iterator(){
		return new Its();
	}
	
	/**
	 * 实现Iterator的内部实现类
	 * Its
	 * @author greenhills
	 * 2017年2月25日 下午9:54:54
	 */
	private class Its implements Iterator {
		private int index=0;
		
		public Its(){
			//this.len = size;  //逆向遍历
		}
		
		@Override
		public boolean hasNext() {
//			return this.len > 0;  //逆向遍历
			return this.index < size; //正向遍历
		}

		@Override
		public Object next() {
//			return get(--this.len); //逆向遍历
//			return elementData[--this.len];//逆向遍历
			return get(this.index++); //正向遍历
		}
	}
}
