package com.coding.basic.stack;

import java.util.Arrays;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	int[] datas = new int[10];
	int size;
	int minIndex;
	
	public void push(int data){
		checkAndresize();
		datas[size] = data;
		if(datas[minIndex] > data){
			minIndex = size;
		}
		size++;
	}
	
	public int pop(){
		checkOutBound();
		return datas[size-1];
	}
	public int findMin(){
		checkOutBound();
		return datas[minIndex];
	}
	
	private void checkAndresize() {
		if(size == datas.length){
			datas = Arrays.copyOf(datas, datas.length*2);
		}
	}
	private void checkOutBound(){
		if(size <= 0){
			throw new IndexOutOfBoundsException();
		}
	}
}