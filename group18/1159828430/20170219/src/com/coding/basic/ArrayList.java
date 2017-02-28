package com.coding.basic;

import java.util.Arrays;

/**
 * @author Hipple
 * @Time：2017年2月20日 下午8:53:31
 * @version 1.0
 */
public class ArrayList implements List {
	
	//元素数量
	private int size = 0;
	
	//默认容量
	private final int defaultCapacity = 10;
	
	//存储元素的容器
	private static Object[] elementData;
	
	//无参构造器
	public ArrayList(){
		elementData = new Object[defaultCapacity];
	}
	
	//指定容量的构造器
	public ArrayList(int capacity){
		if (capacity < 0) {
			//非法参数
			throw new IllegalArgumentException("Illegal Capacity: "+ capacity);
		}
		elementData = new Object[capacity];
	}
	
	//添加元素
	public boolean add(Object o){
		ensureCapacityInternal(size + 1);
		elementData[size++] = o;
		return true;
	}
	
	//添加元素到指定位置
	public void add(int index, Object o){
		rangeCheck(index);
		//将当前位置及后续元素后移一位
		ensureCapacityInternal(size + 1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	//根据下表获取值
	public Object get(int index){
		rangeCheck(index); 
		return elementData[index];
	}
	
	//删除元素
	public Object remove(int index){
		rangeCheck(index);
		Object oldValue = elementData[index];
		int numMoved = size - index - 1;  
	    if (numMoved > 0) {
	    	//要删除的元素不是最后一个时，将当前元素及后续元素左移一位
	        System.arraycopy(elementData, index+1, elementData, index, numMoved); 
	    }
	    elementData[--size] = null;//自动回收
		return oldValue;
	}
	
	//删除元素
	public boolean remove(Object o) {
		// 由于ArrayList中允许存放null，因此下面通过两种情况来分别处理。
		if (o == null) {
			for (int index = 0; index < size; index++){
				if (elementData[index] == null) {
					fastRemove(index);
					return true;
				}
			}
		} else {
			for (int index = 0; index < size; index++){
				if (o.equals(elementData[index])) {
					fastRemove(index);
					return true;
				}
			}
		}
		return false;
	}
	
	//返回现有元素数量
	public int size(){
		return size;
	}
	
	//是否为空
	public boolean isEmpty(){
		return size == 0;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	//动态增加ArrayList大小
    private void ensureCapacityInternal(int minCapacity) {
    	//当前数组无法再存放时将数组长度增加至原长度的1.5倍
        if (minCapacity - elementData.length > 0) {
        	int newCapacity = (elementData.length * 3)/2;
        	elementData = Arrays.copyOf(elementData, newCapacity);
        }
            
    }
    
    //检查是否下标越界
    private void rangeCheck(int index){
    	if (index < 0 || index > this.size) {
    		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	}
    }
    
    //删除元素，与remove的 差别就是没有下标检查
	private void fastRemove(int index) {
		int numMoved = size - index - 1;
		if (numMoved > 0){
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		}
		elementData[--size] = null; 
	}
    
}
class testArrayList{
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i+1);
        }
        arrayList.add(5,15);
        arrayList.remove(11);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("value is "+arrayList.get(i));
        }
    }
}
