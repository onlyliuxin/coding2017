package com.coding.basic;

import com.sun.media.sound.EmergencySoundbank;

public class ArrayList implements List {
	/**
	 * 列表中元素的个数
	 */
	private int size = 0;
	
	/**
	 * 初始化数组大小
	 */
	private int arraySize = 100;
	/**
	 * 初始化数组
	 */
	private Object[] elementData = new Object[arraySize];
	
	/**
	 * 添加方法
	 */
	public void add(Object o){
		if(size>=(arraySize*0.75)){
			Object [] target = new Object[(int) (arraySize*1.5)];
			System.arraycopy(elementData,0,target,0,arraySize);
			target[size-1] = o;
			size++;
		}else if(size<(arraySize*0.75)){
			elementData[size-1]=o;
			size++;
		}
	}
	/**
	 * 根据索引添加方法
	 */
	public void add(int index, Object o){
		if(size >= arraySize*0.75){
			Object [] target = new Object[(int) (arraySize*1.5)];
			System.arraycopy(elementData,0,target,0,arraySize);
			 for (int j = target.length;j>=index;j--){
	                target[j-1] = target[j-2];
	            }
	            target[index] = o;
	            size++;
		}else if(size < arraySize*0.75){
			 for (int j = elementData.length;j>=index;j--){
	                elementData[j-1] = elementData[j-2];
	            }
	            elementData[index] = o;
	            size++;
		}
	}
	/**
	 * 根据索引获取对象
	 */
	public Object get(int index){
		return elementData[index];
	}
	/**
	 * 根据索引移除对象
	 */
	public Object remove(int index){
		for (int i = index; i < elementData.length; i++) {
			elementData[i]=elementData[i+1];
			size++;
		}
		return elementData[index];
	}
	/**
	 * 获取数组大小
	 */
	public int size(){
		return this.size;
	}
	
	
}
