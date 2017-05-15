package com.coding.basic.stack;

import java.util.ArrayList;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回该数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	private ArrayList<int[]> elementData = new ArrayList<int[]>();
	
	public Boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
	
	public void push(int data){
		int[] temp = new int[3];//data min max
		if(isEmpty()){
			temp[0] = data;
			temp[1] = data;
			temp[2] = data;
			elementData.add(temp);
			return;
		}
		
		int min = this.elementData.get(size()-1)[1];
		int max = this.elementData.get(size()-1)[2];
		
		temp[0] = data;
		
		if(data < min){
			temp[1] = data;
		}else{
			temp[1] = min;
		}
		
		if(data > max){
			temp[2] = data;
		}else{
			temp[2] = max;
		}
		
		elementData.add(temp);
	}
	
	public int pop(){
		return this.elementData.remove(size()-1)[0];
	}
	
	public int peek(){
		return this.elementData.get(size()-1)[0];
	}
	
	public int findMin(){
		return this.elementData.get(size()-1)[1];
	}
	
	public int findMax(){
		return this.elementData.get(size()-1)[2];
	}
}
