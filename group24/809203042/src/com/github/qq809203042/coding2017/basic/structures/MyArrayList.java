package com.github.qq809203042.coding2017.basic.structures;

import java.util.Arrays;

/*
 * 根据api中arraylist的方法描述，实现一个自己的arraylist
 * 基于数组实现
 * 
 */
public class MyArrayList implements MyList {
	// 定义一个私有数组，初始长度为10
	private Object[] elementData = new Object[10];
	// 定义变量记录ArrayList的长度
	private int size = 0;

	// 获取指定索引上的元素的值
	@Override
	public Object get(int index) {
		if (index >= 0 && index < size) {
			return elementData[index];
		} else {
			throw new IndexOutOfBoundsException("查询的索引不存在");
		}
	}
//	向列表尾部添加元素
	@Override
	public boolean add(Object obj) {
		if (size >= elementData.length) {// 若size大于等于现有数组长度，则将数组扩容后再进行操作
			elementData = Arrays.copyOf(elementData, elementData.length * 2);
		}
		elementData[size] = obj;
		size++;
		return true;
//		什么时候会返回false，如何返回false？
	}
//	向列表指定位置添加元素
	@Override
	public boolean add(Object obj,int index) {
		if (size >= elementData.length) {// 若size大于等于现有数组长度，则将数组扩容后再进行操作
			elementData = Arrays.copyOf(elementData, elementData.length * 2);
		}
//		将从index开始的所有元素向后移动一位
		moveBackward(elementData,index,size-1,1);
//		将元素添加到指定位置
		elementData[index] = obj;
		size++;
		return true;
	}

//	删除指定位置的元素
	@Override
	public Object remove(int index) {
		if(size == 0){
			throw new IndexOutOfBoundsException("列表为空");
		}
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("想要删除的元素索引不存在");
		}
		Object removeElement = elementData[index];
		moveForward(elementData,index+1,size-1,1);
//		最后一位置为null；！！
		elementData[size-1] = null;
		size--;
		return removeElement;
	}

	@Override
	public int size() {
		
		return size;
	}
//	判断列表是否为空
	@Override
	public boolean isEmpty() {
		
		return size == 0;
	}
//	将数组从startPos位置到endPos位置的元素向后移动step步长
	private void moveBackward(Object[] elementData, int startPos, int endPos, int step) {
		for(int i = endPos + step; i >= startPos + step; i--){
			elementData[i] = elementData[i-step];
		}
		
	}
//	将数组从startPos位置到endPos位置的元素向前移动step步长
	private void moveForward(Object[] elementData, int startPos, int endPos, int step) {
		for(int i = startPos - step; i <= endPos - step; i++){
			elementData[i] = elementData[i+step];
		}
		
	}
	@Override
	public String toString() {
//		return Arrays.toString(elementData);
		Object[] myArrayList = Arrays.copyOf(elementData, size);
		return Arrays.toString(myArrayList);
		
	}
	
	
}
