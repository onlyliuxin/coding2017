package com.coding.basic.stack;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import com.coding.basic.ArrayList;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	private ArrayList minList = new ArrayList();
	private ArrayList elementData = new ArrayList();
	
	public void push(int data){
		if(minList.size()==0){
			minList.add(data);
		}else{
			int min = (int) minList.get(minList.size()-1);
			if(data<min){
				minList.add(data);
			}else{
				minList.add(min);
			}
		}
		elementData.add(data);
	}
	
	public int pop(){
		if(elementData.size()==0){
			throw new EmptyStackException();
		}
		int obj = (int) elementData.remove(elementData.size()-1);
		minList.remove(minList.size()-1);
		return obj;
	}
	
	public int findMin(){
		if(minList.size()==0){
			throw new NoSuchElementException();
		}
		return (int) minList.get(minList.size()-1);
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < elementData.size(); i++) {
			if(buff.length()!=0){
				buff.append(",");
			}
			buff.append(elementData.get(i));
		}
		return buff.toString();
	}
	
}
