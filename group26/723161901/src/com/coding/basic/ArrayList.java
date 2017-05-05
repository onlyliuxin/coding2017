package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size;
	
	private Object[] elementData;
	
	private static Object[] EMPTY_DATA = {};
	
	public ArrayList(){
		elementData = EMPTY_DATA;
	}
	/**
	 * 固定容量初始化
	 * @param initialCapacity
	 */
	public ArrayList(int initialCapacity){
		if(negativeVerify(initialCapacity)){
			throw new IndexOutOfBoundsException(outOfBoundsMsg(initialCapacity));
		}
		elementData = new Object[initialCapacity];
		size = initialCapacity;
	}
	
	public void add(Object o){
		elementData = Arrays.copyOf(elementData, elementData.length+1);
		elementData[size++] = o;
	}
	
	public void add(int index,Object o){
		indexVerify(index);
		elementData = Arrays.copyOf(elementData, elementData.length+1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	
	public int size(){
		return size;
	}
	
	public Object get(int index){
		indexVerify(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		indexVerify(index);
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		size--;
		return elementData[index];
	}
	/**
	 * 正整数边界验证
	 * @param verifiedNo
	 * @return
	 */
	private boolean negativeVerify(int verifiedNo){
		return verifiedNo < 0 ? true : false;
	}
	
	private void indexVerify(int verifiedIndex){
		if(negativeVerify(verifiedIndex) && (size <= verifiedIndex)){
			throw new IndexOutOfBoundsException(outOfBoundsMsg(verifiedIndex));
		}
	}
	
    private String outOfBoundsMsg(int verifiedIndex) {
        return "位置: "+verifiedIndex+", 大小: "+size;
    }
    
    /**
     * 扩展
     * @param growSize
     */
    private void grow(int growSize){
    	elementData = Arrays.copyOf(elementData, size+growSize);
    }
    
		
}
